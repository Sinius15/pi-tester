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
    time.sleep(1);
    print "set to high"

for i in pinList:
    GPIO.output(i, GPIO.LOW)
    time.sleep(1);
    print "est to low"


print "Good bye!"

GPIO.cleanup()
