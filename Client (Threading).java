import java.net.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
public class Client {
public static void main(String args[]) 
{
	try
	{
		       InetAddress ip = InetAddress.getByName("localhost");
				Socket clientSocket = new Socket (ip,6005);
				System.out.println("connecting to the server....");
				 DataInputStream input = new DataInputStream(clientSocket.getInputStream());
				 DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
				 Scanner scanner = new Scanner (System.in);
				 String connectionconfirm = input.readUTF();
				 System.out.println("server:" + connectionconfirm);
				 while (true)
				 {
					 String serverAsk = input.readUTF();
					 System.out.println("server:" + serverAsk);
					 String request = scanner.nextLine();
					 output.writeUTF(request);
					 if(request.equals("close"))
					 {
						 System.out.println("closing the connection with the server");
						 clientSocket.close();
						 System.out.println("the connection with the server is closed");
						 break;
					 }
					 String reply = input.readUTF();
					 System.out.println("server: "+ reply);
				 }
				 scanner.close();
				 input.close();
				 output.close();
				 
	}			
	catch(IOException e)
	{
		System.out.println("connection with the server is terminated");
	}
  }
}