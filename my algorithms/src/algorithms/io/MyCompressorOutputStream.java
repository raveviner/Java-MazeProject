package algorithms.io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * <i>MyCompressorOutputStream</i> is a class designed to compress a 1 dimensional array of bytes and save it
 * into a file. It uses decorator pattern. It can receive any {@link OutputStream} in its constructor
 * and use its write method.
 *
 */
public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	//C'TOR can receive any OutputStream and initialize the data member 
	public MyCompressorOutputStream(OutputStream os) {
		out=os;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
		
	}

	/**
	 *<i>write(byte[] bytes)</i> receives an array of bytes. The fist cell indicates the next
	 *number of variables that should not be compressed.
	 */
	@Override
	public void write(byte[] bytes) throws IOException {
		for(int j=0;j<((int)bytes[0]);j++){
			write((int)bytes[j]);
		}
				
		int counter=0;
		int a=(int)bytes[(int)bytes[0]];
		/*compression starts here. it counts the number of times a number repeats itself, then it 
		 save the number and the repetition number.*/
		for(int i=10;i<bytes.length;i++){
			if(a==(int)bytes[i]){
				counter++;
				/*byte can't save a greater number than 255(2^8), therefore if a number is shown
				more than 255 times, we split the counting into 2 parts.*/
				if(counter==255){  
					write(a);
					write(counter);
					counter=0;
				}
			}
			else{
				write(a);
				write(counter);
				a=(int)bytes[i];
				counter=1;
			}
			
		}
		write(a);
		write(counter);
	}
	


}
