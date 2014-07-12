package com.sinius15.test;

import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

public class USBTester {
	
	public static void main(String[] args) {
		
		// Initialize the libusb context
		int result = LibUsb.init(null);
		
		if (result < 0) { 
			throw new LibUsbException("Unable to initialize libusb", result);
		}   
		
		// Read the USB device list
		DeviceList list = new DeviceList();
		result = LibUsb.getDeviceList(null, list);
		if (result < 0) {
			throw new LibUsbException("Unable to get device list", result);
		}
		
		try {
			// Iterate over all devices and list them
			for (Device device : list) {
				int address = LibUsb.getDeviceAddress(device);
				int busNumber = LibUsb.getBusNumber(device);
				DeviceDescriptor descriptor = new DeviceDescriptor();
				result = LibUsb.getDeviceDescriptor(device, descriptor);
				if (result < 0) {
					throw new LibUsbException("Unable to read device descriptor", result);
				}
				System.out.println("-------------------------");
				System.out.println("new Device Found!");
				System.out.println("idVendor:\t" + descriptor.idVendor());
				System.out.println("busNumber:\t" + busNumber);
				System.out.println("address:\t" + address);
				System.out.println("idProduct:\t" + descriptor.idProduct());
				System.out.println("name:" + descriptor.dump());
			}
		} finally {
			// Ensure the allocated device list is freed
			LibUsb.freeDeviceList(list, true);
		}
		
		// Deinitialize the libusb context
		LibUsb.exit(null);
	}
}
