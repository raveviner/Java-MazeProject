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

public class MazeClientHandler implements ClientHandler, Observer {

	private ServerModel model;
	private ObjectOutputStream out;
	

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

	private void sendToClient(Object o) throws IOException {
		out.writeObject(o);
		out.flush();
	}
}
