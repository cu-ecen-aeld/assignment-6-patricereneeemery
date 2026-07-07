SUMMARY = "AESD assignment 6 socket server"
DESCRIPTION = "AESD assignment 6 socket server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Git source for your assignment 6 branch
#SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-patricereneeemery.git;protocol=ssh;branch=main"
#SRC_URI += "file://aesdsocket-start.sh"
#SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-patricereneeemery.git;protocol=ssh;branch=assignment6 \
#           file://aesdsocket-start.sh"
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-patricereneeemery.git;protocol=ssh;branch=assignment6"
#SRC_URI = "git://github.com/cu-ecen-aeld/assignment-6-patricereneeemery.git;branch=assignment6"
#SRC_URI = "git://github.com/cu-ecen-aeld/assignment-6-patricereneeemery.git;protocol=https;branch=assignment6"
SRC_URI += "file://aesdsocket-start.sh"

# Versioning
PV = "1.0+git${SRCPV}"
#SRCREV = "4ef7ea00e56ba45f10d30e56dfeae3c21828a778"
#SRCREV = "bd0204e5ea420fd323301a329e964389edce93b"
#SRCREV = "7bd0204e5ea420fd323301a329e964389edce93b"
#SRCREV = "708b9eac2d4344cff0fe705885f758ffdc1a1794"
SRCREV = "386752bfe52f1ce911414d98c6a01a34fff2a2c0"
#SRCREV = "1d6a76e0756cdae315772f149c6d0641b267c8b5"
#SRCREV = "4ef7ea00e56ba45f10d30e56dfeae3c21828a778"

# Build directory inside the repo
S = "${WORKDIR}/git/assignment6"

# No configure step needed
do_configure() {
    :
}

# Build using your Makefile
do_compile() {
    oe_runmake
}

# Install the binary and init script
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket-start.sh ${D}${sysconfdir}/init.d/aesdsocket-start
}

# Package only the installed files
FILES:${PN} = "${bindir}/aesdsocket \
               ${sysconfdir}/init.d/aesdsocket-start"
