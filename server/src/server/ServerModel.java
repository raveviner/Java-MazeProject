package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
/**
 * ServerModel is a type of a Model and an Observable class. MazeClientHandler operate this class,
 * and a notification is sent whenever this class finishes its calculations.
 *
 */
public class ServerModel extends Observable implements Model {

	
	//shmap is initialized when loadSolutions is called
	private HashMap<Maze3d, Solution<Position>> shmap;
	
	/**
	 * saveSolutions save into a zip file hash maps contaings solutions.
	 */
	@Override
	public void saveSolution(String fileName) throws FileNotFoundException, IOException {
		//OutputStream out = new GZIPOutputStream(new FileOutputStream("solutions.gzip"));
		ObjectOutputStream o = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutions.gzip")));
		o.writeObject(shmap);
		o.flush();
		o.close();
		
		

	}
	/**
	 * loadSolutions loads from a zip file all the solutions into a data member.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadSolutions() throws ClassNotFoundException, IOException{
	
		File myFile = new File("solutions.gzip");
		try
		{
			if(!myFile.createNewFile())
			{
				ObjectInputStream mazeZip = new ObjectInputStream(new GZIPInputStream(new FileInputStream(myFile)));

				
				this.shmap = (HashMap<Maze3d, Solution<Position>>) mazeZip.readObject();
				
				mazeZip.close();
			} 
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		setChanged();
		notifyObservers(this.shmap);
	}

	@Override
	public void dir(String path) {
		
		
	}

	@Override
	public void generate3DMaze(String name, int x, int y, int z) {
	
		
	}

	@Override
	public void display(String name) {
		
		
	}

	@Override
	public Object getData() {
		
		return null;
	}

	@Override
	public Maze3d getMaze() {
		
		return null;
	}

	@Override
	public Solution<Position> getSolution() {
		
		return null;
	}

	@Override
	public void displayCrossSection(String x, int index, String name) {
		
		
	}

	@Override
	public void saveMaze(String name, String fileName) throws FileNotFoundException, IOException {
		
	}

	@Override
	public void loadMaze(String fileName, String name) throws IOException {
		
		
	}

	@Override
	public void mazeSize(String name) {
	
		
	}

	@Override
	public void fileSize(String name) {
		
		
	}

	@Override
	public void solve(String name, String algo) {
		
		
	}

	@Override
	public void displaySolution(String name) {
		
		
	}

	@Override
	public void exit() {
		
		
	}

	@Override
	public Maze3d getFutureMaze() throws InterruptedException, ExecutionException {
		
		return null;
	}

	@Override
	public Solution<Position> getFutureSolution() throws InterruptedException, ExecutionException {
	
		return null;
	}

	
	

}
