package com.sinius15.test;

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
import javax.usb.event.UsbDeviceDataEvent;
import javax.usb.event.UsbDeviceErrorEvent;
import javax.usb.event.UsbDeviceEvent;
import javax.usb.event.UsbDeviceListener;

public class USBTester implements UsbDeviceListener {
	
	public USBTester(){
		UsbDevice device = findMissileLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
		if (device == null) {
			System.err.println("Not Found");
			System.exit(1);
			return;
		}
		
		device.addUsbDeviceListener(this);

	}
	
	private static final short VENDOR_ID = 0x0424;
	
	@SuppressWarnings("unchecked")
	public static UsbDevice findMissileLauncher(UsbHub hub) {
		UsbDevice launcher = null;
		
		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				launcher = findMissileLauncher((UsbHub) device);
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

	public static void sendMessage(UsbDevice device, byte[] message) throws UsbException {
		UsbControlIrp irp = device.createUsbControlIrp(
						(byte) (UsbConst.REQUESTTYPE_TYPE_CLASS | UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE),
						(byte) 0x09, (short) 2, (short) 1);
		irp.setData(message);
		device.syncSubmit(irp);
	}
	

	public static void main(String[] args) throws UsbException {
		
	}

	@Override
	public void dataEventOccurred(UsbDeviceDataEvent arg0) {}

	@Override
	public void errorEventOccurred(UsbDeviceErrorEvent arg0) {}

	@Override
	public void usbDeviceDetached(UsbDeviceEvent arg0) {}
}
