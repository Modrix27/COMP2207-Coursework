import java.io.*;
import java.net.*;
//command example: java FTClient "192.168.43.56" get boi.txt abc.txt
//this gets the data inside abc.txt and writes it in boi.txt
//command example: java FTClient "192.168.43.56" put boi.txt abc.txt
//this gets the data from boi.txt and writes it in abc.txt (it creates that file if it doesn't exist)
public class FTClient {
	public static void main(String[] args) throws IOException {
		System.out.println(args[0]+" "+args[1]+" "+args[2]+" "+args[3]);
		if(args[1].equals("put")){
			File inputFile = new File(args[2]);
			FileInputStream in = new FileInputStream(inputFile);
			try{
				Socket socket = new Socket(args[0],4323);
				OutputStream out = socket.getOutputStream();
				out.write(("put"+" "+args[3]+" ").getBytes());
				byte[] buf = new byte[1000]; int buflen;
				while ((buflen=in.read(buf)) != -1){
					System.out.println("*");
					out.write(buf,0,buflen);
				}
				out.close();
			}catch(Exception e){System.out.println("error"+e);}
			System.out.println();
			in.close();
		} else
			if(args[1].equals("get")){
				File outputFile = new File(args[2]);
				FileOutputStream outf = new FileOutputStream(outputFile);
				try{
					Socket socket = new Socket(args[0],4323);
					OutputStream out = socket.getOutputStream();
					InputStream in = socket.getInputStream();
					out.write(("get"+" "+args[3]+" ").getBytes());
					byte[] buf = new byte[1000]; int buflen;
					while ((buflen=in.read(buf)) != -1){
						System.out.println("*");
						outf.write(buf,0,buflen);
					}
					out.close();
					in.close();
				}catch(Exception e){System.out.println("error"+e);}
				System.out.println();
				outf.close();
			} else
				System.out.println("unrecognised command");
	}
}