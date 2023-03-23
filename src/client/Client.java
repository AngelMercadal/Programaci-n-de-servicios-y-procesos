package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	
	private int puerto=9876;                                                //puerto que se utiliza
	 
	private String host="localhost";                                       //dirección a la cual nos vamos a conectar
	
	
	
	public Client() {                                                      //constructor vacio ya que ya tenemos los atributos establecidos
		
		
	}
		
	
	
	public void conectar() {	                                           //método que va a realizar conexión y peticiones
		
		int nTareas;
		
		Scanner sc=new Scanner(System.in); 
		
		try {                                                                    //bloque try catch para controlar la conexión
			
			Socket client= new Socket(host,puerto);                                 //creamos y conectamos socket cliente
			
			
			DataInputStream input= new DataInputStream(client.getInputStream());                      //abrimos flujo de entrada
			DataOutputStream output= new DataOutputStream(client.getOutputStream());                  //abrimos flujo de salida
			
			System.out.println(input.readUTF());                        //muestra por pantalla el mensaje que recibe del servidor (nombre)
			output.writeUTF(sc.nextLine());                            //contesta el mensaje y es enviado
			
			System.out.println(input.readUTF());                      //muestra por pantalla el mensaje que recibe del servidor(nº de tareas)
			nTareas=sc.nextInt();
			sc.nextLine();
			output.writeUTF(String.valueOf(nTareas));                //contesta el mensaje y es enviado
			
		
			
			
			for (int i = 0; i < nTareas; i++) {	                        //bucle para asignar a cada tarea un estado y descripción
			
		     System.out.println(input.readUTF());                       //leer pregunta
			 output.writeUTF(sc.nextLine());                            //asignar estado
			 
			 System.out.println(input.readUTF());                      //leer pregunta
			 output.writeUTF(sc.nextLine());                            //asignar descripción
		
			
			}
			
			
			System.out.println(input.readUTF());                       //leer mensaje (servidor informa de que va a enviar tareas)
			
			
			for (int i = 0; i < nTareas; i++) {	                             //bucle para leer cada tarea
				
		
			System.out.println(input.readUTF());	}
			
			
			
			                                                                      //cerramos todos los flujos
			sc.close();
			input.close();
			output.close();
			client.close();
			
			
			
			
		} catch (IOException e) {                                                //excepción de no acceso controlada
			e.printStackTrace();
			
		}
		
	}

}
