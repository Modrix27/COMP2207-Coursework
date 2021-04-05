import java.io.*;
import java.net.*;
class TCPReceiver{
	public static void main(String [] args){
		try{
			ServerSocket ss = new ServerSocket(4322);
			ss.setSoTimeout(5000);
			System.out.println(ss.getSoTimeout()  + " milliseconds until timeout!");
			for(;;){
				try{
					Socket client = ss.accept();
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String line;
					while((line = in.readLine()) != null)
						System.out.println(line+" received");
					client.close();
					ss.close();
				}catch(SocketTimeoutException s){System.out.println("Socket timed out!");
				break;}
				catch(Exception e){System.out.println("error: "+e.getMessage());}
			}
		}catch(Exception e){System.out.println("error "+e);}
	}
}
