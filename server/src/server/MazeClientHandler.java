package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
 * MazeClientHandler handle clients that use maze 3d game.
 *
 */
public class MazeClientHandler implements ClientHandler, Observer {

	private ServerModel model;
	private ObjectOutputStream out;
	
	//CTOR initializes model
	public MazeClientHandler(ServerModel model) {
		this.model=model;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
			out = new ObjectOutputStream(outToClient);
			String line;
			
			while (!(line = in.readLine()).equals("exit")) {
				String[] command = line.split(" ");
				if(line.equals("load solutions")){
					model.loadSolutions();
				}
				if(line.startsWith("save")){
					model.saveSolution(command[2]);
				}

			}
			in.close();
			out.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//notification from ServerModel come here
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == model) {
			try {
				sendToClient((HashMap<Maze3d, Solution<Position>>) arg1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * sendToClient receives an Object and passes to the client.
	 * @param o
	 * @throws IOException
	 */
	private void sendToClient(Object o) throws IOException {
		out.writeObject(o);
		out.flush();
	}
}
