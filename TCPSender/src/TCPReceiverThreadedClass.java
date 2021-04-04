import java.io.*;
import java.net.*;
class TCPReceiverThreadedClass{
	public static void main(String [] args){
		try{
			ServerSocket ss = new ServerSocket(4322);
			ss.setSoTimeout(5000);
			System.out.println(ss.getSoTimeout()+" milliseconds until timeout!");
			for(;;){
				try{
					Socket client = ss.accept();
					new Thread(new ServiceThread(client)).start();
				}catch(SocketTimeoutException s){System.out.println("Socket timed out!");
				break;}
				catch(Exception e){System.out.println("error "+e);}
			}
		}catch(Exception e){System.out.println("error "+e);}
	}
	static class ServiceThread implements Runnable{
		Socket client;
		ServiceThread(Socket c){client=c;}
		public void run(){
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String line;
				while((line = in.readLine()) != null)
					System.out.println(line+" received"+" from "+client.getInetAddress()+", port "+client.getPort());
				client.close(); 
			}catch(Exception e){}
		}
	}
}