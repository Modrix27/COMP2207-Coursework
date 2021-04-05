import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogWriter {
	
	public void write(){
		File outputFile = new File("log.txt");
		FileOutputStream out;
		try {
			out = new FileOutputStream(outputFile);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
