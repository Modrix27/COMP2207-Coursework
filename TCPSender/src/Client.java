import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

public static void main(String[] args) throws IOException {
		
		int cPort = Integer.parseInt(args[0]);
		int timeout = Integer.parseInt(args[1]);
		
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		System.out.println("connected on port " + socket.getPort());

	}
}
