package com.sinius15.test;

import java.util.Arrays;
import java.util.List;

import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.event.UsbDeviceDataEvent;
import javax.usb.event.UsbDeviceErrorEvent;
import javax.usb.event.UsbDeviceEvent;
import javax.usb.event.UsbDeviceListener;

public class USBTester implements UsbDeviceListener {
	
	public USBTester() throws SecurityException, UsbException {
		UsbDevice device = findDevice(UsbHostManager.getUsbServices().getRootUsbHub());
		if (device == null) {
			System.err.println("Not Found");
			System.exit(1);
			return;
		}
		
		device.addUsbDeviceListener(this);
		
	}
	
	private static final short VENDOR_ID = 0x0424;
	
	@SuppressWarnings("unchecked")
	public UsbDevice findDevice(UsbHub hub) {
		UsbDevice launcher = null;
		
		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				launcher = findDevice((UsbHub) device);
				if (launcher != null)
					return launcher;
			} else {
				UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
				if (desc.idVendor() == VENDOR_ID)
					return device;
			}
		}
		return null;
	}
	
	@Override
	public void dataEventOccurred(UsbDeviceDataEvent e) {
		System.out.println(Arrays.toString(e.getUsbControlIrp().getData())); 
	}
	
	@Override
	public void errorEventOccurred(UsbDeviceErrorEvent e) {
		
	}
	
	@Override
	public void usbDeviceDetached(UsbDeviceEvent e) {
		
	}
	
	public static void main(String[] args) throws UsbException {
		new USBTester();
	}
}
