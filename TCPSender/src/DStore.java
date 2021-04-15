import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DStore {

public static void main(String[] args) throws IOException {
		
		int port = Integer.parseInt(args[0]);
		int cPort = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		String folder = (args[3]);
		DataOutputStream out = null; 
				
		ServerSocket ss = new ServerSocket(port);
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		socket.setSoTimeout(5000);
		System.out.println("connected on port " + socket.getPort());
		File file = new File(System.getProperty("user.dir") + "/" + folder);
		boolean bool = file.mkdir();
		if(bool)
			System.out.println(folder + " created!");
		else
			System.out.println(folder + " might already exist.");
		try {
			Thread.sleep(timeout);
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("disconnect");
			socket.close();
			System.out.println("Socket closed.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
