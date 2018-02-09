import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static final int portNumber = 4444;
	static ServerSocket serverSocket = null;
	static Socket socket;
	static BufferedReader br;
	static String message;
	static char letter;
	static HangmanModel hModel = new HangmanModel();

	public static void main(String[] args) {
		//@SuppressWarnings("unused")
		//TCPServer hangmanGame = new TCPServer();
		//****** *****
		new Thread() {
			@Override
			public void run() {
				try {
					int clientID = 0;
					// starting server
					System.out.println("Server starting at port number: " + portNumber);
					serverSocket = new ServerSocket(portNumber);
					while (true) {
						// client connecting
						System.out.println("Waiting for clients to connect...");
						socket = serverSocket.accept();
						
						clientID++;
						System.out.println("Client "+ clientID +" has connected.");

						new Thread() {
							@Override
							public void run() {
								try {
									hModel.readFile();
									hModel.setOdabrani();
									hModel.setCode();

									System.out.println("Sending data to client...");
									// Send message to the client
									BufferedWriter bw = new BufferedWriter(
											new OutputStreamWriter(socket.getOutputStream()));
									bw.write(hModel.getOdabrani());
									bw.newLine();
									bw.write(hModel.getCode());
									bw.newLine();
									bw.flush();

									// Receive message from client
									br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

									message = br.readLine();
									System.out.println(message);

									while ((message = br.readLine()) != null) {
										letter = message.charAt(0);
										System.out.println("Letter from the client: " + message);
									}
									System.out.println("Server has ended");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}