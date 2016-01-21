package algorithms.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * <i>MyDecompressorOutputStream</i> is a class designed to decompress a 1 dimensional array of bytes and extract it
 * into an existing array. It uses decorator pattern. It can receive any IutputStream in its constructor
 * and use its read method.
 *
 */
public class MyDecompressorInputStream extends InputStream {

	private InputStream in;

	public MyDecompressorInputStream(InputStream is) {
		in = is;
	}
	
/**
 * <i>read(byte[] arg0)</i> is a method that decompress a file into a byte array (arg0). The first 
 * byte in the file indicates the next number of bytes that should be read normally. After that 
 * the method uses a decompress mechanism to decompress the rest of the file.
 */
	@Override
	public int read(byte[] arg0) throws IOException {
		arg0[0] = (byte) read();
		for(int j=1;j<((int)arg0[0]);j++){
			arg0[j] = (byte) read();
		}
		
		int j=0;
		int flag=0;
		int i=arg0[0];
		/*decompression starts here. One byte is read normally and the next bytes indicates 
		 the repetition time of that last byte.*/
		while(i<arg0.length){
			if(flag==0){
				j=read();
				arg0[i]=(byte)j;
				flag=1;
				i++;
			}
			else if(flag==1){
				j=read();
				int k=1;
				while(k<j){
					arg0[i]=arg0[i-1];
					i++;
					k++;
					
				}
				flag=0;
			}
			
			
		}		

		return 0;
	}

	@Override
	public int read() throws IOException {
		return in.read();

	}

}
