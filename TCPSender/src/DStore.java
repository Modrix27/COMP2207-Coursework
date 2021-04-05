import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DStore {

public static void main(String[] args) throws IOException {
		
		//connects only if port = cPort for some reason
		int port = Integer.parseInt(args[0]);
		int cPort = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		String folder = (args[3]);
		
		System.out.println(port + " " + cPort + " " + timeout + " " + folder);
		
		ServerSocket ss = new ServerSocket(port);
		ss.setSoTimeout(timeout);
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		System.out.println("connected on port " + socket.getPort());
	}
}
