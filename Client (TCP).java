import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		try {
			// Get the IP address of the local host and send it the socket with the server port number.
			InetAddress ip = InetAddress.getByName("localhost"); //127.0.0.1 (same device)
			Socket clientSocket = new Socket(ip, 4000); //to call server

			System.out.println("Connecting to the server....");

			// Take input from the server by using the client socket.
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());

			// Print output to the server by using the client socket.
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

			// Take input from console.
			Scanner scanner = new Scanner(System.in);

			// Read from the server that the client is now connected and print it.
			String connectConfirm = input.readUTF();
			System.out.println("Server: " + connectConfirm);

			// Start communication with server.
			while (true) {
				// Read the message that was asked by the server for the request and print it.
				String serverAsk = input.readUTF();
				System.out.println("Server: " + serverAsk);

				// Write the requested message and send it to the server.
				String request = scanner.nextLine();
				output.writeUTF(request);

				// Close the connection with the server.
				if (request.equals("close")) {
					System.out.println("Closing connection to server....");
					clientSocket.close();
					System.out.println("Connection is closed.");
					break;
				}

				// Read the server reply and print it.
				String reply = input.readUTF();
				System.out.println("Server: " + reply);
			}

			// Close the objects.
			scanner.close();
			input.close();
			output.close();
		} catch (IOException e) {
			System.out.println("Connection with server is terminated.");
		}

	}
}
