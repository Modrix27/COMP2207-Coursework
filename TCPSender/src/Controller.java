import java.io.*;
import java.net.*;

public class Controller {

	public static void main(String[] args) throws IOException {
		
		int port = Integer.parseInt(args[0]);
		int repFactor = Integer.parseInt(args[1]);
		int timeout = Integer.parseInt(args[2]);
		int rebalancePeriod = Integer.parseInt(args[3]);
		
		LogWriter log = new LogWriter();
		log.write();
		
		System.out.println(port + " " + repFactor + " " + timeout + " " + rebalancePeriod);
		
		ServerSocket ss = new ServerSocket(port);
		ss.setSoTimeout(timeout);
		System.out.println(ss.getSoTimeout()+" milliseconds until timeout!");
	}
}
