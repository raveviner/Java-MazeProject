package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.heuristics.MazeAirDistance;
import algorithms.search.searchables.SearchableMaze;
import algorithms.search.searchers.AStar;
import algorithms.search.searchers.BFS;

public class MyModel extends Observable implements Model {

	private int numOfThreads = 1;
	private HashMap<String, Maze3d> hmap = new HashMap<String, Maze3d>();
	private HashMap<SaveableMaze3d, Solution<Position>> shmap = new HashMap<SaveableMaze3d, Solution<Position>>();
	private ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
	private Object data;
	private Future<Maze3d> futureMaze;
	private Future<Solution<Position>> futureSolution;
	private Maze3d maze3d;
	private Solution<Position> solution;
	private SaveableMaze3d saveableMaze;
	//private String mazeName;

	@Override
	public Solution<Position> getFutureSolution() throws InterruptedException, ExecutionException {
		return futureSolution.get();
	}

	@Override
	public Maze3d getFutureMaze() throws InterruptedException, ExecutionException {
		return futureMaze.get();
	}

	public MyModel(int numOfThreads) {
		this.numOfThreads = numOfThreads;
		
	}

	@Override
	public Maze3d getMaze() {
		return maze3d;
	}

	@Override
	public Solution<Position> getSolution() {
		return solution;
	}

	@Override
	public void dir(String path) {
		File file = new File(path);
		data = file.list();

		setChanged();
		notifyObservers("done dir");

	}

	@Override
	public void generate3DMaze(String name, int x, int y, int z) {

		futureMaze = executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				Maze3d maze = new MyMaze3dGenerator().generate(x, y, z);
				hmap.put(name, maze);
				
				setChanged();
				notifyObservers("done generate maze");
				return maze;
			}
		});
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public void display(String name) {

		if (name.equals("this")) {
			setChanged();
			notifyObservers("display this");
		} else {
			
			maze3d = hmap.get(name);
			setChanged();
			notifyObservers("display");
		}

	}

	@Override
	public void displayCrossSection(String section, int index, String name) {
		Maze3d maze = hmap.get(name);
		if (section.equals("x"))
			data = (maze.getCrossSectionByX(index));
		if (section.equals("y"))
			data = (maze.getCrossSectionByY(index));
		if (section.equals("z"))
			data = (maze.getCrossSectionByZ(index));
		setChanged();
		notifyObservers("display cross section");

	}

	@Override
	public void saveMaze(String name, String fileName) throws IOException {
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
		saveableMaze = new SaveableMaze3d(name,hmap.get(name));
		out.write(saveableMaze.toByteArray());
		out.close();
		setChanged();
		notifyObservers("maze is saved");

	}

	@Override
	public void loadMaze(String fileName, String name) throws IOException {
		InputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
		byte[] buffer = new byte[10000];
		in.read(buffer);
		in.close();
		int i=0;
		int j=0;
		while(j!=(-1)){
			j=buffer[i];
			i++;
		}
		byte[] b = Arrays.copyOf(buffer, i-1);
		
		saveableMaze = new SaveableMaze3d(b);
		hmap.put(name, saveableMaze.getMaze());
		hmap.put(saveableMaze.getName(), saveableMaze.getMaze());
		setChanged();
		notifyObservers("maze is loaded");

	}

	@Override
	public void mazeSize(String name) {
		Maze3d maze = hmap.get(name);
		data = maze.getXlength() * maze.getYlength() * maze.getZlength() * 4;
		setChanged();
		notifyObservers("maze size");

	}

	@Override
	public void fileSize(String name) {
		File file = new File(name);
		data = (int) file.length();
		setChanged();
		notifyObservers("file size");
	}

	@Override
	public void solve(String name, String algo) {
		if (shmap.containsKey(hmap.get(name))) {
			setChanged();
			notifyObservers("maze is solved");
		} else {
			futureSolution = executor.submit(new Callable<Solution<Position>>() {

				@Override
				public Solution<Position> call() throws Exception {

					Maze3d maze = hmap.get(name);
					Solution<Position> s = null;

					if (algo.equals("BFS")) {
						BFS<Position> bfs = new BFS<Position>();
						s = bfs.search(new SearchableMaze(maze));
						shmap.put(new SaveableMaze3d(name, maze), s);
						setChanged();
						notifyObservers("maze is solved");
					} else if (algo.equals("AStar")) {
						AStar<Position> aStar = new AStar<Position>(new MazeAirDistance<Position>());
						s = aStar.search(new SearchableMaze(maze));
						shmap.put(new SaveableMaze3d(name, maze), s);
						setChanged();
						notifyObservers("maze is solved");

					}

					return s;
				}
			});
		}

	}

	@Override
	public void displaySolution(String name) {
		if (name.equals("this")) {
			setChanged();
			notifyObservers("display solution this");
		} else {
			solution = shmap.get(hmap.get(name));
			setChanged();
			notifyObservers("display solution");
		}

	}

	@Override
	public void exit() {
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdownNow();
	}

	@Override
	public void saveSolution(String fileName) throws FileNotFoundException, IOException {
		//OutputStream out = new GZIPOutputStream(new FileOutputStream("solutions.gzip"));
		ObjectOutputStream o = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutions.gzip")));
		o.writeObject(shmap);
		o.flush();
		o.close();
		
		setChanged();
		notifyObservers("solutions are saved");

	}
	
	@SuppressWarnings("unchecked")
	public void loadSolutions() throws ClassNotFoundException, IOException{
	
		File myFile = new File("solutions.gzip");
		try
		{
			if(!myFile.createNewFile())
			{
				ObjectInputStream mazeZip = new ObjectInputStream(new GZIPInputStream(new FileInputStream(myFile)));

				
				this.shmap = (HashMap<SaveableMaze3d, Solution<Position>>) mazeZip.readObject();
				
				mazeZip.close();
			} 
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	
	

}
