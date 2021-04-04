import java.io.*;
import java.net.*;

class TCPSender{
	public static void main(String [] args) {
		try {
			Socket socket = new Socket("192.168.43.214",4322);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			System.out.println("connected on port " + socket.getPort());
			for(int i=0;i<10;i++) {
				out.println("TCP message "+i);
				out.flush();
				System.out.println("TCP message "+i+" sent");
				Thread.sleep(1000);
			}
		}catch(Exception e) {System.out.println("error "+e);}
	}
}
