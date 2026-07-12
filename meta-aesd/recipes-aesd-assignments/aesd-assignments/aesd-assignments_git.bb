SUMMARY = "AESD assignment 6 socket server"
DESCRIPTION = "AESD assignment 6 socket server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-6-patricereneeemery.git;protocol=https;branch=assignment6"
SRC_URI += "file://aesdsocket-start.sh"

SRCREV = "bdbd73f356a20137a01454dfdafdb300ae0e179f"

# Repo root
S = "${WORKDIR}/git"

do_configure() {
    :
}

# Build using Makefile inside assignment6/
do_compile() {
    oe_runmake -C assignment6
}

do_install() {
    # Install aesdsocket binary
    install -d ${D}${bindir}
    install -m 0755 ${S}/assignment6/aesdsocket ${D}${bindir}/aesdsocket

    # Install systemd service
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/assignment6/aesdsocket.service ${D}${systemd_unitdir}/system/aesdsocket.service

    # Install init.d script (used in Assignment 7)
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket-start.sh ${D}${sysconfdir}/init.d/aesdsocket-start
}

FILES:${PN} = "\
    ${bindir}/aesdsocket \
    ${systemd_unitdir}/system/aesdsocket.service \
    ${sysconfdir}/init.d/aesdsocket-start \
"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "aesdsocket.service"
