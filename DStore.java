import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//java DStore 3000 12345 5000 testfolder

public class DStore {

public static void main(String[] args) throws IOException {
		
		int port = Integer.parseInt(args[0]);
		int cPort = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		String folder = (args[3]);
		
		DataOutputStream out = null; 
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ServerSocket ss = new ServerSocket(port);
		Socket socket = new Socket(InetAddress.getLocalHost(), cPort);
		DstoreLogger.init(Logger.LoggingType.ON_FILE_AND_TERMINAL, port); 
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		System.out.println("connected on port " + socket.getPort());
		socket.setSoTimeout(5000);

		//makes a folder to store files
		File file = new File(System.getProperty("user.dir") + "/" + folder);
		boolean bool = file.mkdir();
		
		if(bool)
			System.out.println(folder + " created!");
		else
			//to do: make it so that the controller gets the commands from the dstore
			System.out.println(folder + " might already exist.");
		
		String text;
		//reads commands until it reads "disconnect"
		while((text = reader.readLine())!=null) {
			//one of 3 messages is written in console again
			writer.println(text);
			writer.flush();
			if(text.equals("off")) {
				socket.close();
				System.out.println("Socket closed.");
			}
		}

		/*try {
			Thread.sleep(timeout);
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("disconnect");
			socket.close();
			System.out.println("Socket closed.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
	}
}
