/**
 * com.Bo2A.serial.WriteDataBasculaEmulacion
 * 
 * Descripcion 
 * TODO
 * 
 * @Autor bo2
 * @Date 20 oct. 2020
 */
package com.Bo2A.serial;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * @author bo2
 *
 */
public class WriteDataBasculaEmulacion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String puerto = "COM1";
		int inicio = 0, fin = 25000;
		int sizeNumber = 7;	// num.Bytes emitidos; el primero es el signo
		long pausa = 1000;
		if ( args.length >= 1 )
			puerto = args[0];
		if ( args.length >= 2 )
			inicio = Integer.parseInt(args[1]);
		if ( args.length >= 3 )
			fin = Integer.parseInt(args[2]);
		if ( args.length >= 4 )
			pausa = Long.parseLong(args[3]);
		if ( args.length >= 5 )
			sizeNumber = Integer.parseInt(args[4]);
		
		System.out.println("puerto: " + puerto);
		System.out.println("params: " +
				"SerialPort.BAUDRATE_9600 " +
				"SerialPort.DATABITS_8 " +
				"SerialPort.STOPBITS_1 " +
				"SerialPort.PARITY_NONE");
		System.out.println("inicio: " + inicio + "; fin: " + fin + "; pausa: " + pausa);
		if ( puerto.length() == 0 )
			System.out.println("Usar: java WriteDataBasculaEmulacion <puerto> [inicio] [fin] [pausa] ");
		
		else {
			SerialPort serialPort = new SerialPort(puerto);
		    try {
		        serialPort.openPort();//Open serial port
		        serialPort.setParams(SerialPort.BAUDRATE_9600, 
		                             SerialPort.DATABITS_8,
		                             SerialPort.STOPBITS_1,
		                             SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
		        
		        System.out.println("*INICIO*");
		        int incremento = fin > inicio ? 1 : -1;
		        
		        for ( int i = inicio, resto = inicio-fin; resto != 0; i+=incremento, resto+=incremento ) {
		        	String numeroString = String.valueOf(i<0?i*-1:i);
		        	if ( numeroString.length() > sizeNumber ) 
		        		numeroString = numeroString.substring(0, sizeNumber);
		        	while ( numeroString.length() < sizeNumber ) 
		        		numeroString = " " + numeroString;
		        	String enviado = (i>=0?"+":"-")+numeroString;
		        	serialPort.writeBytes(enviado.getBytes());
		        	System.out.println(enviado);
		        	Thread.sleep(pausa);
		        }
		        serialPort.closePort();
		        System.out.println("*FIN*");
		    }
		    catch (SerialPortException ex) {
		        System.out.println(ex);
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
