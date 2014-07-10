package com.sinius15.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Tester {
	
	public static void main(String[] args) {
		
		GpioController gpio = GpioFactory.getInstance();
		
		GpioPinDigitalOutput[] outs = new GpioPinDigitalOutput[] {
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "gpio 0", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "gpio 1", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "gpio 2", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "gpio 3", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "gpio 4", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "gpio 5", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "gpio 6", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "gpio 7", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "gpio 8", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "gpio 9", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "gpio 10", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "gpio 11", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "gpio 12", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "gpio 13", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "gpio 14", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "gpio 15", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "gpio 16", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "gpio 17", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "gpio 18", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "gpio 19", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20, "gpio 20", PinState.LOW),
		
		};
		for (GpioPinDigitalOutput o : outs) {
			o.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
			System.out.println("turned on: " + o.getName());
			o.setState(PinState.HIGH);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			o.setState(PinState.LOW);
		}
		
		gpio.shutdown();
		
	}
	
}
