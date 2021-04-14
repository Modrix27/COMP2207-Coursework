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
				
		ServerSocket ss = new ServerSocket(port);
		//ss.setSoTimeout(timeout);
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		System.out.println("connected on port " + socket.getPort());
		File file = new File(System.getProperty("user.dir") + "/" + folder);
		boolean bool = file.mkdir();
		if(bool)
			System.out.println(folder + " created!");
		else
			System.out.println(folder + " might already exist.");
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		
		for(int i=0;i<10;i++) {
			out.println("TCP message "+i);
			out.flush();
			System.out.println("TCP message "+i+" sent");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("error: "+e);
			}
		}
	}
}
