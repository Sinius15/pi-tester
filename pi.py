#!/usr/bin/python
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

# init list with pin numbers

pinList = [4, 17, 22]

# loop through pins and set mode and state to 'low'

for i in pinList: 
	GPIO.setup(i, GPIO.OUT) 
	GPIO.output(i, GPIO.HIGH)

# time to sleep between operations in the main loop

SleepTime = 1
SleepTimeL = 10
count = 1

# main loop

try:
  GPIO.output(2, GPIO.LOW)
  time.sleep(SleepTime);
  for i in pinList:
      GPIO.output(2, GPIO.LOW)
      time.sleep(SleepTime);


  while (count > 0):
    pinList.reverse()

    for i in pinList:
        GPIO.output(i, GPIO.HIGH)
        time.sleep(SleepTime);

  print "Good bye!"

# End program cleanly with keyboard
except KeyboardInterrupt:
  print "  Quit"

  # Reset GPIO settings
  GPIO.cleanup()
