package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
 * 
 *The View interface is a part of MVP design. View is responsible for the 
 *content display.
 *
 */
public interface View {
	/**
	 * getUserCommand is a method that lets us pull the command from outside View.
	 */
	public String[] getUserCommand();
	public void displayMaze(Maze3d maze);
	public void displayDir(String[] string);
	public void displayHint(String string); //prints a simple message
	public void displayCrossSecion(int[][] maze2d);
	public void displaySize(int size); //prints the size in bytes of the maze
	public void displaySolution(Solution<Position> solution);
	
}
