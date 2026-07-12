# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-patricereneeemery.git;protocol=https;branch=master \
           file://0001-Trim-Makefile-to-build-only-scull-and-misc-modules-f.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "bfcb7b0bc5ec8a5db1812246b2f7688dd53048a0"

S = "${WORKDIR}/git"

inherit module

MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
