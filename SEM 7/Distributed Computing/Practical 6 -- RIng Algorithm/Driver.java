import java.io.*;
import java.net.*;
import java.util.*;

public class Driver
{
	//Escritores
	PrintWriter w1;
	PrintWriter w2;
	PrintWriter w3;

	//Para mayor comodidad en el acceso a los canales; contendrá nuestros escritores arriba
	ArrayList<PrintWriter> outputStreams = new ArrayList<PrintWriter>();

	//Los lectores que se pasarán a un hilo de ejecución independiente de cada
	BufferedReader r1;
	BufferedReader r2;
	BufferedReader r3;

	int nodeNum;

	// Nuestro objeto de algoritmo de exclusion mutua
	RicartAgrawala me;

	int numberOfWrites;
	int writeLimit = 5; // número de veces para tratar de CS
	int csDelay = 100; //espere retraso entre CS intenta en ms

	/** Iniciar el conductor, con un número de canales especificados. **/
	public Driver(String args[])
	{
		System.out.println("\n\n");
		final boolean desireToHarmHumansOrThroughInactionAllowHumansToComeToHarm = false; //Sólo en caso de

		nodeNum = Integer.parseInt(args[0]);

		numberOfWrites = 0;

		// Configure los sockets con nuestros nodos pares
		try
		{
			ServerSocket ss1;
			ServerSocket ss2;
			ServerSocket ss3;
			Socket s1;
			Socket s2;
			Socket s3;

			if(nodeNum == 1)
			{
				//Clear the file
				BufferedWriter clearWrite = new BufferedWriter(new FileWriter("CriticalSectionOutput.txt"));
				clearWrite.write("\n");
				clearWrite.close();

				System.out.println("Nodo 1 aqui:");
				ss1 = new ServerSocket(4461); //ServerSocket para net02
				ss2 = new ServerSocket(4462); //ServerSocket para net03
				ss3 = new ServerSocket(4463); //ServerSocket para net04
				s1 = ss1.accept();
				s2 = ss2.accept();
				s3 = ss3.accept();
			}
			else if(nodeNum == 2)
			{
				System.out.println("Nodo 2 aqui:");
				s1 = new Socket("192.168.1.36", 4461); //ClientSocket para net01
				ss2 = new ServerSocket(4462); //ServerSocket para net03
				ss3 = new ServerSocket(4463); //ServerSocket para net04

				s2 = ss2.accept();
				s3 = ss3.accept();
			}
			else if(nodeNum == 3)
			{
				System.out.println("Nodo 3 aqui:");
				s1 = new Socket("192.168.1.36", 4462); //ClientSocket para net01
				s2 = new Socket("192.168.1.76", 4462); //ClientSocket para net02
				ss3 = new ServerSocket(4463); //ServerSocket para net04

				s3 = ss3.accept();
			}
			else
			{
				System.out.println("Nodo 4 aqui:");
				s1 = new Socket("192.168.1.36", 4463);
				s2 = new Socket("192.168.1.76", 4463);
				s3 = new Socket("192.168.1.82", 4463);
			}

			System.out.println("Creados todos los sockets");

			//Con las tomas hechas, crear nuestros lectores y escritores
			w1 = new PrintWriter(s1.getOutputStream(), true);
			w2 = new PrintWriter(s2.getOutputStream(), true);
			w3 = new PrintWriter(s3.getOutputStream(), true);
			r1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			r2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
			r3 = new BufferedReader(new InputStreamReader(s3.getInputStream()));


			//Vamos a guardar nuestros escritores en una lista
			outputStreams.add(w1);
			outputStreams.add(w2);
			outputStreams.add(w3);

			// Crear el objeto ME con prioridad 'nodeNum "y el número de secuencia inicial 0
			me = new RicartAgrawala(nodeNum, 0, this);
			me.w[0] = w1;
			me.w[1] = w2;
			me.w[2] = w3;


			//Y vamos a empezar algunos hilos para leer nuestras tomas
			Thread t1 = new Thread(new ChannelHandler(s1));
			t1.start();

			Thread t2 = new Thread(new ChannelHandler(s2));
			t2.start();

			Thread t3 = new Thread(new ChannelHandler(s3));
			t3.start();

		}
		catch(Exception ex){ ex.printStackTrace();}


		while(numberOfWrites < writeLimit)
		{
			try{
				System.out.println("Solicitar seccion critica ...");
				requestCS();
				numberOfWrites++;
				//Random num = new Random();
				Thread.sleep(1000);
				//Thread.sleep(csDelay);
			}
			catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
	}

	/** Invocación de la sección crítica*/
	public static boolean criticalSection(int nodeNum, int numberOfWrites)
	{
		System.out.println("Nodo " + nodeNum + " entrando a sección crítica ");
		try
		{
			BufferedWriter criticalSection = new BufferedWriter(new FileWriter("CriticalSectionOutput.txt", true));

			criticalSection.write(nodeNum + " acceso a la sección crítica comenzado");
			criticalSection.newLine();
			Thread.sleep(1000);
			//criticalSection.write(nodeName + " has now accessed it's critical section " + numberOfWrites + " times.");
			criticalSection.write(nodeNum + " acceso a la sección crítica terminado");
			criticalSection.newLine();
			criticalSection.newLine();
			criticalSection.flush(); //flush stream
			criticalSection.close(); //close write
		}
		catch(Exception e){ System.out.println("Oh No! Algo ha ido terriblemente mal");}
		return true;
	}


	/**
	* Método del interfaz entre driver y el RicartAgrawala
	*/
	public void requestCS()
	{

		me.invocation();

		//Después vuelve invocación, que podemos llamar de forma segura CS
		criticalSection(nodeNum, numberOfWrites);

		//Una vez que hayamos terminado con CS, CS liberación
		me.releaseCS();
	}

	/**
	* Transmite un mensaje a todos los escritores en los OutputStreams ArrayList.
    * Tenga en cuenta esto debe probablemente nunca utilizar como RicartAgrawala es unicast
	*/
	public void broadcast(String message)
	{
		for(int i = 0; i < outputStreams.size(); i++)
		{
			try
			{
				PrintWriter writer = outputStreams.get(i);
				writer.println(message);
				writer.flush();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}



	/**
	* Teniendo en cuenta un socket, que continuamente se lee de la
        * Toma y pasa la información clave para el objeto ME.
	*/
	class ChannelHandler implements Runnable
	{
		BufferedReader reader;
		PrintWriter writer;
		Socket sock;

		public ChannelHandler(Socket s)
		{
			try
			{
				sock = s;
				InputStreamReader iReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(iReader);

			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

		/** Continuamente se ejecuta y lee todos los mensajes entrantes, pasando mensajes a ME */

		public void run()
		{
			String message;

			try
			{
				//Mientras este lector está abierto, tendrá acción en el momento que llegue un mensaje.
				while(( message = reader.readLine() ) != null)
				{
					System.out.println("Nodo " + nodeNum + " Mensaje recibido: " + message);

					//Tokenize nuestro mensaje para determinar el paso RicartAgrawala

					String tokens[] = message.split(",");
					String messageType = tokens[0];

					if(messageType.equals("PETICION"))
					{
						/*Estamos recibiendo petición (j, k), donde j es un SS y k un nodo #.
						 Esta llamada se decide aplazar o acuse de recibo con una respuesta. */

						me.receiveRequest(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					}
					else if(messageType.equals("RESPUESTA"))
					{
						/* Recibió una respuesta. Vamos a decrementamos nuestras respuestas pendientes  */
						me.receiveReply();
					}
				}

			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		new Driver(args);
	}

}