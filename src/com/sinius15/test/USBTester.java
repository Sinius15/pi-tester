package com.sinius15.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;

public class USBTester {
	
	/** The vendor ID of the missile launcher. */
	private static final short VENDOR_ID = 0x1130;
	
	/** The product ID of the missile launcher. */
	private static final short PRODUCT_ID = 0x0202;
	
	public static UsbDevice findMissileLauncher(UsbHub hub) {
		UsbDevice launcher = null;
		
		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				launcher = findMissileLauncher((UsbHub) device);
				if (launcher != null)
					return launcher;
			} else {
				UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
				if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID)
					return device;
			}
		}
		return null;
	}

	public static void sendMessage(UsbDevice device, byte[] message) throws UsbException {
		UsbControlIrp irp = device.createUsbControlIrp(
						(byte) (UsbConst.REQUESTTYPE_TYPE_CLASS | UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE),
						(byte) 0x09, (short) 2, (short) 1);
		irp.setData(message);
		device.syncSubmit(irp);
	}
	

	public static void main(String[] args) throws UsbException {
		// Search for the missile launcher USB device and stop when not found
		UsbDevice device = findMissileLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
		if (device == null) {
			System.err.println("Missile launcher not found.");
			System.exit(1);
			return;
		}
		
		// Claim the interface
		UsbConfiguration configuration = device.getUsbConfiguration((byte) 1);
		UsbInterface iface = configuration.getUsbInterface((byte) 1);
		iface.claim(new UsbInterfacePolicy() {
			@Override
			public boolean forceClaim(UsbInterface usbInterface) {
				return true;
			}
		});
	}
}
