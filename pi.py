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
    print "1"
	GPIO.output(i, GPIO.HIGH)
    print "2"

# time to sleep between operations in the main loop
print "3"
SleepTime = 1
count = 1
print "4"
# main loop
print "5"
try:
  for i in pinList:
      print "6"
      GPIO.output(i, GPIO.LOW)
      print "7"
      time.sleep(SleepTime);
      print "8"


  while (count > 0):
    print "9"
    pinList.reverse()
    print "1"

    for i in pinList:
        print "2"
        GPIO.output(i, GPIO.HIGH)
        time.sleep(SleepTime);
        print "3"

  print "Good bye!"

# End program cleanly with keyboard
except KeyboardInterrupt:
  print "  Quit"

  # Reset GPIO settings
  GPIO.cleanup()
