import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;
public class Server {
	static ServerSocket serverSocket ;
	public static void main(String arg[])
	{
		try
		{
		
			serverSocket = new ServerSocket (6005);
			System.out.println("the server is booted up");
	
   
   while (true)
   {
	   Socket clientSocket = serverSocket.accept();
	   System.out.println("A new client [" + clientSocket + "]is connected to the server");
	   Thread client = new ClientConnection (clientSocket);
	   client.start();
   }
		}
		catch(Exception e)
		{ System.out.println("problem with socket server");
		}
		}
	static class ClientConnection extends Thread
	{
		final private Socket clientSocket;
		public ClientConnection ( Socket clientSocket) 
		{ this.clientSocket = clientSocket;
		}
		public void run() 
		{
			try {
	   DataInputStream input = new DataInputStream(clientSocket.getInputStream());
	   DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
	   Scanner scanner = new Scanner (System.in);
	   output.writeUTF("connected");
	   
	   while(true)
	   {
		   output.writeUTF("send your request or 'close' to terminate the connection");
		   String request = input.readUTF();
		   System.out.println(" Client:["+ clientSocket+"]"+request);
		   if(request.equals("close"))
		   {
			   System.out.println("closing the connection with this client["+ clientSocket+"]");
			   System.out.println("the connection with this client["+ clientSocket+ "] is closed");
			   clientSocket.close();
			   break;
		   }
		   String reply = scanner.nextLine();
		   output.writeUTF(reply);
		   
	   }
	   scanner.close();
		 input.close();
		 output.close();
			} catch (IOException e)
			{ System.out.println("connection wuth this client ["+ clientSocket+"]is closed");
			}
			}
		}
 	}