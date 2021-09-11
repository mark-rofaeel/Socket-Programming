//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.net.*;

public class Client {

	public static void main(String[] args) {

	try {
		//create an object of Datagram
		
		DatagramSocket clientsocket = new DatagramSocket();
		InetAddress IP = InetAddress.getByName("localhost");
		byte requestByte[]=null; //from server
		byte responseByte[]=new byte[4096]; //to server
		
		//Take input from console
		Scanner scanner= new Scanner (System.in);
		
		while (true) {
		System.out.println("Send request to server or 'close' to close the connection");
		String in = scanner.nextLine();
		
		if (in.equalsIgnoreCase("close")) {
			System.out.println("Connection is closed");
			clientsocket.close();
			break;
		}
		//send to server
		
		requestByte = in.getBytes();
		
		DatagramPacket clientpacket = new DatagramPacket (requestByte, requestByte.length,IP,4000);
		clientsocket.send(clientpacket);
		
		// receive from server
		
		DatagramPacket serverpacket = new DatagramPacket(responseByte, responseByte.length);
		clientsocket.receive(serverpacket);
		String response = new String (serverpacket.getData());
		System.out.println("Server: " + response.trim());
		
		}
		scanner.close();
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	{
		}
}
}
