import java.io.*;
import java.net.*;

//NOTE: Run the controller in Eclipse's terminal and the DStores on a separate cmd
//java Controller 12345 5 10000 10000
public class Controller {

	public static void main(String[] args) throws IOException {
		try {
			int port = Integer.parseInt(args[0]);
			int repFactor = Integer.parseInt(args[1]);
			int timeout = Integer.parseInt(args[2]);
			int rebalancePeriod = Integer.parseInt(args[3]);
			int connectedDStores = 0;
			
			DataInputStream in = null;
			DataOutputStream out = null;

			//System.out.println(port + " " + repFactor + " " + timeout + " " + rebalancePeriod);

			ServerSocket ss = new ServerSocket(port);
			ss.setSoTimeout(timeout);
			System.out.println(ss.getSoTimeout()+" milliseconds until timeout!");
			
			ControllerLogger.init(Logger.LoggingType.ON_FILE_AND_TERMINAL);

			for(;;){
				try {
					//in = new DataInputStream(System.in);
					System.out.println("waiting for connection");
					Socket client = ss.accept();
					System.out.println("connected");
					BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
					connectedDStores++;
					System.out.println(connectedDStores + " DStores connected");
					System.out.println(client.getClass() + " connected on port " + client.getPort());
					//String line1 = "";
					String command;

					while((command = reader.readLine()) != null) {
						//1 out of 3 messages is received
						System.out.println(command + " received");
						ControllerLogger.getInstance().messageReceived(client, command);
						if(command.equals("off")) {
							System.out.println("disconnecting...");
							client.close();
							connectedDStores--;
							System.out.println(connectedDStores + " DStores connected");
						}
					}
					client.close();

					/*
					while (!line1.equals("Over")) {
						try {
							line1 = in.readUTF();
							out.writeUTF(line1);
							client.close();
							connectedDStores--;
							System.out.println(connectedDStores + " DStores connected");
						}
						catch(IOException e) {
							e.printStackTrace();;
						}
					}

					in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
					String line2 = in.readUTF();
					if(line2.equals("disconnect")) {
						client.close();
						connectedDStores--;
						System.out.println(connectedDStores + " DStores connected");
					}*/

					//if you close it only one Sender will send stuff
					//ss.close();
				}catch(Exception e){System.out.println("error: "+e.getMessage());}
			}
		}catch(Exception e){System.out.println("error "+e);}
	}
}
