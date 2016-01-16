package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

public class MazeClientHandler implements ClientHandler, Observer {

	ServerModel model;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
			PrintWriter out = new PrintWriter(outToClient);
			String line;
			while (!(line = in.readLine()).equals("exit")) {
				String[] command = line.split(" ");
				if (line.startsWith("generate")) {
					model.generate3DMaze(command[3], Integer.parseInt(command[4]), Integer.parseInt(command[5]),
							Integer.parseInt(command[6]));
				}

			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0==model){
			
		}
	}
}
