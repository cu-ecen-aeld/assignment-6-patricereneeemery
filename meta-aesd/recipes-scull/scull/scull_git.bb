# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=477dfa54ede28e2f361e7db05941d7a7"

#SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-patricereneeemery.git;protocol=https;branch=main \
#           file://0001-Trim-Makefile-to-build-only-scull-and-misc-modules-f.patch \
#           "
SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-patricereneeemery.git;protocol=https;branch=main"

# Modify these as desired
PV = "1.0+git${SRCPV}"
#SRCREV = "bfcb7b0bc5ec8a5db1812246b2f7688dd53048a0"
#SRCREV = "429efb29300bc0a475db4f0a98f94cf42daebf90"
#SRCREV = "0615e28911b1dac36e97086729ab4728c8b5460a"
SRCREV = "b42cf06c5ead42ae203521cf135589269dee4bbe"

S = "${WORKDIR}/git/scull"

inherit module

#MODULES_INSTALL_TARGET = "install"
#EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
