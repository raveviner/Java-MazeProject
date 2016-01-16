package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
/**
 * MyCLIView extends ObservableView and implements View and Runnable.
 * Responsible for the Command Line Interface view. Observable because it notify the presenter
 * and Runnable because it can run in a new thread.
 *
 */
public class MyCLIView extends ObservableView implements View, Runnable {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private PrintWriter out = new PrintWriter(System.out);

	public MyCLIView(HashMap<String, Command> map) {
		super(map);// initializes the hash map in ObservableView
	}

	@Override
	public void run() {
		//running when start() method is called from thread. 
		//receiving commands from user.
		try {
			line = (in.readLine()).split(" ");

			while (!(line[0]).equals("exit")) {

				command = hmap.get(line[0]);
				setChanged();
				notifyObservers(command);
				line = (in.readLine()).split(" ");
			}

		} catch (IOException e) {
			out.println("Incorrect Input");
		}

	}

	@Override
	public void displayMaze(Maze3d maze) {
		for (int h = 0; h < maze.getZlength(); h++) {
			for (int j = 0; j < maze.getYlength(); j++) {
				for (int i = 0; i < maze.getXlength(); i++) {
					out.print(maze.getValue(i, j, h) + ", ");
				}
				out.print("\n");
			}
			out.println("----------------");
		}
		out.flush();
	}

	@Override
	public void displayDir(String[] string) {
		for (String s : string) {
			out.println(s);
		}
		out.flush();

	}

	/**
	 * displayHint receives a message and prints it to the screen.
	 */
	public void displayHint(String string) {
		//is used to inform the user about certain process.
		out.println(string);
		out.flush();

	}

	@Override
	public void displayCrossSecion(int[][] crossSection) {

		for (int i = 0; i < crossSection[0].length; i++) {
			for (int j = 0; j < crossSection.length; j++) {
				out.print(crossSection[j][i] + ", ");
			}
			out.print("\n");
		}
		out.flush();

	}

	@Override
	public void displaySize(int size) {
		out.println(size + " bytes");
		out.flush();

	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		out.println(solution);
		out.flush();

	}
}
