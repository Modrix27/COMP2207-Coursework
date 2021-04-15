import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		int cPort = Integer.parseInt(args[0]);
		int timeout = Integer.parseInt(args[1]);
		
		Scanner s = new Scanner(System.in);
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		System.out.println("connected on port " + socket.getPort());
		DataInputStream input = null;
		DataOutputStream out = null;
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		
		input = new DataInputStream(System.in);
		out = new DataOutputStream(socket.getOutputStream());
		String line = "";
		while (!line.equals("Over")) {
			try {
				line = s.nextLine();
				//writer.println(line);
				System.out.println(line);
				out.writeUTF(line);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}	
	}
}

