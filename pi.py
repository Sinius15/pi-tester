#!/usr/bin/python
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

# init list with pin numbers

pinList = [4, 17, 22]

# loop through pins and set mode and state to 'low'
print "setting up 4"
GPIO.setup(4, GPIO.OUT) 
print "setting up 17"
GPIO.setup(17, GPIO.OUT)
print "setting up 22" 
GPIO.setup(22, GPIO.OUT)
print "done setting up!" 

for i in pinList: 
	GPIO.output(i, GPIO.HIGH)

# time to sleep between operations in the main loop

SleepTime = 1
count = 1

# main loop

try:
  for i in pinList:
      GPIO.output(i, GPIO.LOW)
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
