/**
 * 
 */
package com.Bo2A.serial;

import jssc.SerialPortList;

/**
 * @author bo2
 *
 */
public class ListPorts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] portNames = SerialPortList.getPortNames();
		System.out.println("Lista de puertos en el sistema:");
		for ( String port : portNames ) {
			System.out.println(port);
		}
		if ( portNames == null || portNames.length == 0 ) 
			System.out.println("(ninguno detectado)");

		System.out.println("");
		System.out.println("MODO DE USO");
		System.out.println("===========");
		System.out.println("Lista de puertos");
		System.out.println("----------------");
		System.out.println("java -jar bo2_serial.jar");
		System.out.println("");
		System.out.println("Lectura de un puerto");
		System.out.println("--------------------");
		System.out.println("java -cp bo2_serial.jar com.Bo2A.serial.ReadData <puerto=defecto COM1>");
		System.out.println("");
		System.out.println("Escritura de un puerto");
		System.out.println("----------------------");
		System.out.println("java -cp bo2_serial.jar com.Bo2A.serial.WriteData <puerto=defecto COM1>");
		System.out.println("");
		System.out.println("Esperar eventos");
		System.out.println("---------------");
		System.out.println("java -cp bo2_serial.jar com.Bo2A.serial.EventListener <puerto=defecto COM1>");
		
	}

}
