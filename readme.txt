Instructions

- building the image  
- fixing the missing kernel  
- booting QEMU  
- validating Assignment 6 runtime behavior  
- socket tests  
- threading tests  
- autotest expectations  

 
Written for **Assignment 6**, **qemuarm64**, and your exact Yocto setup.


---

# **README — Assignment 6 Yocto + QEMU + Runtime Tests**

## **1. Build the Assignment 6 Yocto Image**

From the top of your Assignment 6 repo:

```bash
./build.sh
```

You should see:

```
Tasks Summary: ... all succeeded
```

If any task fails, fix the error before continuing.

---

## **2. Fix Missing Kernel Image (if needed)**

If QEMU reports:

```
KERNEL not found
```

and your deploy directory contains only the rootfs:

```bash
ls -l build/tmp/deploy/images/qemuarm64
```

then force Yocto to rebuild the kernel:

```bash
bitbake virtual/kernel -c clean
bitbake core-image-aesd -c clean
bitbake core-image-aesd
```

After the build completes, verify:

```bash
ls -l build/tmp/deploy/images/qemuarm64
```

You should now see:

```
Image
Image-qemuarm64.bin
core-image-aesd-qemuarm64.qemuboot.conf
core-image-aesd-qemuarm64.rootfs.ext4
```

---

## **3. Boot QEMU**

Use your repo’s script:

```bash
./runqemu.sh
```

Or Yocto directly:

```bash
runqemu qemuarm64 core-image-aesd
```

Expected behavior:

- Kernel boots  
- BusyBox shell appears  
- Prompt: `/ #` or `login: root`  

If login prompt appears, enter:

```
root
```

(no password)

---

## **4. Validate Assignment 6 Inside QEMU**

### **4.1 Verify aesdsocket is installed**

Inside QEMU:

```bash
ls -l /usr/bin/aesdsocket
```

Expected: ELF binary.

### **4.2 Verify aesdsocket auto‑starts**

```bash
ps aux | grep aesdsocket
```

Expected:

```
root   <pid>   ...   /usr/bin/aesdsocket
```

### **4.3 Verify printk messages**

```bash
dmesg | grep aesd
```

or:

```bash
cat /var/log/messages | grep aesd
```

Expected: your Assignment 6 printk messages.

---

## **5. Socket Tests (Host → QEMU)**

Run these on the **host**, not inside QEMU.

### **5.1 Basic write/read**

```bash
echo "test" | nc 127.0.0.1 9000
```

Expected:

```
test
```

### **5.2 Multiple writes**

```bash
echo "line1" | nc 127.0.0.1 9000
echo "line2" | nc 127.0.0.1 9000
nc 127.0.0.1 9000
```

Expected:

```
line1
line2
```

### **5.3 Timestamp test**

```bash
echo "timestamp" | nc 127.0.0.1 9000
```

Expected: a timestamped entry in `/var/log/messages` inside QEMU.

---

## **6. Threading Tests (Inside QEMU)**

Assignment 6 requires:

- main thread  
- accept thread  
- worker thread  
- mutex protection  
- clean shutdown  

Inside QEMU:

### **6.1 Verify threads exist**

```bash
ps -T | grep aesdsocket
```

Expected: multiple threads under the same PID.

### **6.2 Verify mutex behavior**

Send multiple simultaneous writes from host:

```bash
for i in {1..10}; do echo "thread-$i" | nc 127.0.0.1 9000 & done
wait
```

Inside QEMU:

```bash
nc 127.0.0.1 9000
```

Expected: all lines appear in correct order, no corruption.

### **6.3 Verify clean shutdown**

Inside QEMU:

```bash
poweroff
```

Expected:

- shutdown message from your script  
- aesdsocket exits cleanly  
- no zombie threads  

If `poweroff` fails:

```
Ctrl + A, then X
```

---

## **7. Autotest Validation (Inside QEMU)**

Your image includes the autotest script:

```bash
ls -l /full-test.sh
```

Run it:

```bash
/full-test.sh
```

Expected:

```
ALL TESTS PASSED
```

If any test fails, fix the corresponding part of your server or init script.

---

## **8. Archive Your Working Assignment 6 Environment**

Once QEMU validation passes:

```bash
cd ~
tar -czf assignment6-yocto-backup.tar.gz assignment-6-patricereneeemery
```

This creates a complete, restorable snapshot of your working Yocto build.


