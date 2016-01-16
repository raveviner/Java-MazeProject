package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Model {
	public void dir(String path);
	public void generate3DMaze(String name, int x, int y, int z);
	public void display(String name);
	
	public Object getData(); 
	public Maze3d getMaze();
	public Solution<Position> getSolution();
	
	public void displayCrossSection(String x,int index,String name);
	public void saveMaze(String name, String fileName) throws FileNotFoundException, IOException;
	public void loadMaze(String fileName, String name) throws IOException;
	public void mazeSize(String name);
	public void fileSize(String name);
	public void solve(String name, String algo);
	public void displaySolution(String name);
	public void exit();
	public Maze3d getFutureMaze() throws InterruptedException, ExecutionException;
	public Solution<Position> getFutureSolution() throws InterruptedException, ExecutionException;
	
	public void saveSolution(String fileName) throws FileNotFoundException, IOException; 
	
	
	
	

}
