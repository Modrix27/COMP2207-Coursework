import java.io.*;
import java.net.*;

//NOTE: Run the controller in Eclipse's terminal and the DStores on a separate cmd
public class Controller {

	public static void main(String[] args) throws IOException {

		int port = Integer.parseInt(args[0]);
		int repFactor = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		int rebalancePeriod = Integer.parseInt(args[3]);
		int connectedDStores = 0;
		DataInputStream in = null;
		DataOutputStream out = null;

		LogWriter log = new LogWriter();
		log.write();

		//System.out.println(port + " " + repFactor + " " + timeout + " " + rebalancePeriod);

		ServerSocket ss = new ServerSocket(port);
		ss.setSoTimeout(timeout);
		System.out.println(ss.getSoTimeout()+" milliseconds until timeout!");

		for(;;){
			in = new DataInputStream(System.in);
			Socket client = ss.accept();
			connectedDStores++;
			System.out.println(connectedDStores + " DStores connected");
			System.out.println(client.getClass() + " connected on port " + client.getPort());
			String line1 = "";
			while (!line1.equals("Over")) {
				try {
					line1 = in.readUTF();
					out.writeUTF(line1);
				}
				catch(IOException e) {
					e.printStackTrace();;
				}
			}
			client.close();
			
			in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			String line2 = in.readUTF();
			if(line2.equals("disconnect")) {
				client.close();
				connectedDStores--;
				System.out.println(connectedDStores + " DStores connected");
			}

			//if you close it only one Sender will send stuff
			//ss.close();
		}
	}
}
