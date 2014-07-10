package com.sinius15.pi;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioControllerImpl;

public class Tester {
	
	public static void main(String[] args) {
		
		GpioControllerImpl gpio = (GpioControllerImpl) GpioFactory.getInstance();
		
		GpioPinDigitalOutput[] outs = new GpioPinDigitalOutput[] {
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "gpio 0"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "gpio 1"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "gpio 2"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "gpio 3"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "gpio 4"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "gpio 5"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "gpio 6"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "gpio 7"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "gpio 8"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "gpio 9"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "gpio 10"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "gpio 11"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "gpio 12"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "gpio 13"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "gpio 14"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "gpio 15"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "gpio 16"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "gpio 17"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "gpio 18"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "gpio 19"),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20, "gpio 20"),
		
		};
		for (GpioPinDigitalOutput o : outs) {
			o.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		}
		
		for (GpioPinDigitalOutput o : outs) {
			System.out.println("turned on: " + o.getName());
			o.setState(true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			o.setState(false);
		}
		
		gpio.shutdown();
		
	}
	
}
