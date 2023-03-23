package server;

import java.io.*;
import java.net.*;



public class Server {

	private int puerto=9876;                            //puerto que se utiliza
	
	
	
	public Server() {                          //constructor vacío ya que ya dejamos establecido el puerto
		
	}

	
	
	
public void iniciarServidor() {
	
	int nTareas;
	
	String estado;
	
	String descripcion;
	
	try {
		ServerSocket server= new ServerSocket(puerto);                                  //creamos socket servidor estableciendo puerto
		
		Socket client=server.accept();                                                      //servidor queda a la escucha para aceptar conexión
		
		System.out.println("Conexión establecida.");                                            //confirmación de conexión
				
		DataInputStream input= new DataInputStream(client.getInputStream());                        //abrimos flujo de entrada
		DataOutputStream output= new DataOutputStream(client.getOutputStream());                   //abrimos flujo de salida
		
		output.writeUTF("La conexión se ha establecido.¿Cuál es tu nombre?");                //confirmamos conexión y preguntamos nombre
		System.out.println("El nombre del cliente es: "+input.readUTF());                       //vemos respuesta
		
		
		output.writeUTF("¿Cuántas tareas hace falta realizar?");                  //preguntamos nº de tareas  
		
		nTareas= Integer.parseInt(input.readUTF());                                 //guardamos nº de tareas en variable
		
		System.out.println("El número de tareas es: "+nTareas);
		
		                                                                                       
		
		Tarea[]array  =new Tarea[nTareas];                                   //creamos array de objetos Tarea 
		
		for (int i = 0; i < nTareas; i++) {                                 //recorremos el array asignando los atributos dados por el cliente
		 
			output.writeUTF("Nº de tarea:"+(i+1)+". Detalla el estado");   
			
			estado= input.readUTF();
			
			output.writeUTF("Detalla la descripción para esta tarea");
			
			descripcion= input.readUTF();
			
			
			
			array[i]=new Tarea(descripcion,estado);                                 //llamamos al constructor con los atributos dados 
		}
		
		
		output.writeUTF("Se va a proceder a enviar las tareas:\n");                     //enviamos mensaje
		
		
		for (int i = 0; i < nTareas; i++) {                                        //bucle para leer cada uno de los elementos del array
			                                                                    
			
		output.writeUTF(array[i].toString());
		
		
		
		
		}
		
		
		input.close();                                                      //cerramos flujos
		output.close();
		client.close();
		server.close();
		
		
	
	} catch (IOException e) {                                                        //controlamos error de conexión
		
		
		e.printStackTrace();
		System.out.println("Error de conexión");
	}
	
	
		
}



	
}



