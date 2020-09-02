/**
 * 
 */
package com.Bo2A.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * @author bo2
 *
 */
public class EventListener {

	static SerialPort serialPort;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String puerto = "COM1";
		if ( args.length >= 1 )
			puerto = args[0];
		System.out.println("puerto: " + puerto);
		System.out.println("params: " +
				"SerialPort.BAUDRATE_9600 " +
				"SerialPort.DATABITS_8 " +
				"SerialPort.STOPBITS_1 " +
				"SerialPort.PARITY_NONE");
		
	    serialPort = new SerialPort(puerto); 
	    try {
	        serialPort.openPort();//Open port
	        serialPort.setParams(SerialPort.BAUDRATE_9600, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);//Set params
	        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
	        serialPort.setEventsMask(mask);//Set mask
	        serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}

	/*
	 * In this class must implement the method serialEvent, through it we learn about 
	 * events that happened to our port. But we will not report on all events but only 
	 * those that we put in the mask. In this case the arrival of the data and change the 
	 * status lines CTS and DSR
	 */
	static class SerialPortReader implements SerialPortEventListener {

	    public void serialEvent(SerialPortEvent event) {
	        if(event.isRXCHAR()){//If data is available
	            if(event.getEventValue() == 10){//Check bytes count in the input buffer
	                //Read data, if 10 bytes available 
	                try {
	                    byte buffer[] = serialPort.readBytes(10);
	                    System.out.println("leido: " + new String(buffer));
	                }
	                catch (SerialPortException ex) {
	                    System.out.println(ex);
	                }
	            }
	        }
	        else if(event.isCTS()){//If CTS line has changed state
	            if(event.getEventValue() == 1){//If line is ON
	                System.out.println("CTS - ON");
	            }
	            else {
	                System.out.println("CTS - OFF");
	            }
	        }
	        else if(event.isDSR()){///If DSR line has changed state
	            if(event.getEventValue() == 1){//If line is ON
	                System.out.println("DSR - ON");
	            }
	            else {
	                System.out.println("DSR - OFF");
	            }
	        }
	    }
	}


}
