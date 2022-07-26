#!/bin/ash

latest=$(find /media/input  -type f \( -name "*.jpg" -o -name "*.JPG" \) -printf "%T@ %p\n" | sort -n | cut -d' ' -f 2- | tail -n 1)
if [ -n "${latest}" ]
then
  echo "[UPDATE WEATHER CAM] Found latest JPG file: ${latest}"
  cp -f "${latest}" /media/output/current.jpg
else
  echo "[UPDATE WEATHER CAM] No JPG files found"
fi
echo "[UPDATE WEATHER CAM] Clearing media input directory"
rm -rf /media/input/*
