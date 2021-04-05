import java.io.*;
import java.net.*;

//NOTE: Run the controller in Eclipse's terminal and the DStores on a separate cmd
public class Controller {

	public static void main(String[] args) throws IOException {
		
		int port = Integer.parseInt(args[0]);
		int repFactor = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		int rebalancePeriod = Integer.parseInt(args[3]);
		
		LogWriter log = new LogWriter();
		log.write();
		
		//System.out.println(port + " " + repFactor + " " + timeout + " " + rebalancePeriod);
		
		ServerSocket ss = new ServerSocket(port);
		//ss.setSoTimeout(timeout);
		//System.out.println(ss.getSoTimeout()+" milliseconds until timeout!");
		
		for(;;){
			Socket client = ss.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String line;
			while((line = in.readLine()) != null)
				System.out.println(line+" received");
			client.close();
			//if you close it only one Sender will send stuff
			//ss.close();
		}
	}
}
