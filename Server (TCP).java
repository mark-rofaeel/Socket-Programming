import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	// Create static object from ServerSocket.
	static ServerSocket serverSocket; // Waiting to hear the connection and make socket

	public static void main(String[] args) {
		try {
			// Initialize Server Socket with port number as parameter.
			serverSocket = new ServerSocket(4000);
			System.out.println("Server is booted up and is waiting for clients to connect.");

			while (true) {
				// Accept any Client wants to connect to the server.
				Socket clientSocket = serverSocket.accept();

				System.out.println("A new client is connected to the server.");

				// Takes input from the client socket.
				DataInputStream input = new DataInputStream(clientSocket.getInputStream());

				// Print output to the client socket.
				DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

				// Take input from console.
				Scanner scanner = new Scanner(System.in);

				// Send to the client that it is now connected.
				output.writeUTF("connected.");

				// Start communication with client.
				while (true) {
					output.writeUTF("Send your request or 'close' to close the connection.");

					// Read the request from the client and output it to Server's console.
					String request = input.readUTF();
					System.out.println("Client: " + request);

					// Close the connection with this client.
					if (request.equals("close")) {
						System.out.println("Closing connection with this client....");
						clientSocket.close();
						System.out.println("Connection with this client is closed.");
						break;
					}

					// Write the reply of the server and send it to the client.
					String reply = scanner.nextLine();
					output.writeUTF(reply);
				}

				// Close the objects.
				scanner.close();
				input.close();
				output.close();
			}
		} catch (IOException e) {
			System.out.println("Problem with Server Socket.");
		}
	}
}