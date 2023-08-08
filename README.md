# Weather producer server setup

## Prerequisites

- RaspberryPi with RaspberryPi-OS 64bit (or similar) installed
- WH1080 SE weather station (or similar station compatible with WeeWx software)

## RaspberryPi Docker Installation

1. Update

        sudo apt update
        sudo apt upgrade

2. Follow [docker installation instructions](https://docs.docker.com/engine/install/debian/)
3. Update dhcpcd.conf

        sudo nano /etc/dhcpcd.conf

   Add entry:

        denyinterfaces veth*

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

## Setup working directory

1. Make working directory for weather producer server

        sudo mkdir /opt/weatherstation

2. Copy contents from weatherapp-producer.git (Directory /server) to the working directory, update permissions

        cd /opt/weatherstation
        sudo chmod +x *.sh

3. Create data directory in working directory and grant permissions

        sudo mkdir /opt/weatherstation/data
        sudo chmod -R 777 /opt/weatherstation/data

## Configure application

1. Edit the .env file inside the working directory

        sudo nano /opt/weatherstation/.env

   Update contents:

        CONSUMER_URL=<add URL of weather consumer api>
        CONSUMER_USERNAME=<add username for weather consumer api>
        CONSUMER_PASSWORD=<add password for weather consumer api>
   
3. Run docker compose to setup weewx

        cd /opt/weatherstation
        sudo docker-compose run weewx

4. Update properties of weewx.conf

        sudo nano /opt/weatherstation/data/weewx.conf

   Edit properties:

        [StdConvert]
            target_unit = METRIC
   	
        [StdArchive]
            archive_interval = 600

## Start

1. Go the working directory

        cd /opt/weatherstation

2. Start

        sudo ./start.sh


