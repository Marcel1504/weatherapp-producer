# Weather producer server setup

## Prerequisites

- RaspberryPi with RaspberryPi-OS 64bit (or similar) installed
- WH1080 SE weather station (or similar station compatible with WeeWx software)

## RaspberryPi Docker Installation

1. Update

        sudo apt update
        sudo apt upgrade

2. Follow [docker installation instructions](https://docs.docker.com/engine/install/debian/)

## WH1080 USB access permissions

The access permissions of WH1080 USB device must be updated to allow access inside docker containers

1. Plug WH1080 USB into RaspberryPi
2. Find out Bus and Device number of WH1080

        sudo lsusb

   Example output:

   > Bus 002 Device 003: ID 1941:8021 Dream Link WH1080 Weather Station / USB Missile Launcher

3. Find out vendor ID and product ID using Bus and Device Number

        sudo udevadm info -a -n /dev/bus/usb/002/003

4. Create File 99-weather-station.rules

        sudo nano /etc/udev/rules.d/99-weather-station.rules

5. Add vendor ID and product ID and set permission mode to 666

        SUBSYSTEM=="usb", ATTRS{idVendor}=="1941", ATTRS{idProduct}=="8021", MODE="0666", GROUP="plugdev", SYMLINK+="weather-station"

