/**
 * 
 */
package com.Bo2A.serial;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * @author bo2
 *
 */
public class ReadData {

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
		
		SerialPort serialPort = new SerialPort(puerto);
	    try {
	        serialPort.openPort();//Open serial port
	        serialPort.setParams(SerialPort.BAUDRATE_9600, 
	        		SerialPort.DATABITS_8, 
	        		SerialPort.STOPBITS_1, 
	        		SerialPort.PARITY_NONE);//Set params.
	        
	        byte[] buffer = null;
	        do {
	        	buffer = serialPort.readBytes(10);//Read 10 bytes from serial port
	        	System.out.println("leido: " + new String(buffer));
	        } while ( buffer != null );	        
	        serialPort.closePort();//Close serial port
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}

}
