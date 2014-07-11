package com.sinius15.pi;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioControllerImpl;

public class Tester {
	
	public static void main(String[] args) throws InterruptedException {
		
		GpioControllerImpl gpio = (GpioControllerImpl) GpioFactory.getInstance();
		
		GpioPinDigitalOutput[] outs = new GpioPinDigitalOutput[] {
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "gpio 1", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "gpio 2", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "gpio 3", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "gpio 4", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "gpio 5", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "gpio 10", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "gpio 7", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "gpio 8", PinState.LOW),
		
		};
		for (GpioPinDigitalOutput o : outs) 
			o.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		
		gpio.setState(true, outs);
		Thread.sleep(500);
		
		gpio.setState(false, outs);
		Thread.sleep(500);
		
		gpio.setState(true, outs);
		Thread.sleep(500);
		
		gpio.setState(false, outs);
		Thread.sleep(500);
		
		gpio.setState(true, outs);
		Thread.sleep(500);
		
		gpio.setState(false, outs);
		Thread.sleep(500);
		
		for(int i = 0; i <= outs.length; i++){
			try{
				outs[i].setState(true);
			}catch (Exception e) {}
			try{
				outs[i-1].setState(false);
			}catch (Exception e) {}
			Thread.sleep(500);
		}
		
		gpio.shutdown();
		
	}
	
	
}
