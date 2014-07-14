package com.sinius15.pi.server;

import java.io.Closeable;
import java.io.IOException;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioControllerImpl;

public class WireManager implements Closeable{
	
	private GpioPinDigitalOutput[] outs;
	private GpioControllerImpl gpio;
	
	public WireManager(){
		gpio = (GpioControllerImpl) GpioFactory.getInstance();
		
		outs = new GpioPinDigitalOutput[] {
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "gpio 1", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "gpio 2", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "gpio 3", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "gpio 4", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "gpio 5", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "gpio 06", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "gpio 7", PinState.LOW),
				gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "gpio 8", PinState.LOW),
		};
	}
	
	public void setWireState(int i, boolean state){
		if(i < 1 || i > 8)
			return;
		outs[i-1].setState(state);
		onChange();
	}
	
	public void toggleState(int i){
		if(i < 1 || i > 8)
			return;
		outs[i-1].toggle();
		onChange();
	}
	
	public void allOn(){
		gpio.setState(true, outs);
		onChange();
	}
	public void allOff(){
		gpio.setState(false, outs);
		onChange();
	}
	public boolean toggle(int i) {
		if(i < 1 || i > 8)
			return false;
		outs[i-1].toggle();
		onChange();
		return (gpio.getState(outs[i-1]) == PinState.HIGH ? true: false);
	}
	public boolean getState(int i) {
		if(i < 1 || i > 8)
			return false;
		onChange();
		return (gpio.getState(outs[i-1]) == PinState.HIGH ? true: false);
	}
	@Override
	public void close() throws IOException {
		gpio.shutdown();
	}
	
	private void onChange(){
		
	}

	
	
	
}
