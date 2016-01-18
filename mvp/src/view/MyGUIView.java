package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import view.gui.StartWindow;

/**
 * MyGUIView extends ObservableView and implements View and Observer.
 * Responsible for the GUI view. Observable because it notify the presenter
 * and Observer because it get notified from the GUI.
 *
 */
public class MyGUIView extends ObservableView implements View, Observer {

	private StartWindow sw;

	public MyGUIView(HashMap<String, Command> map, StartWindow sw) {
		super(map); // initializes the hash map in ObservableView
		this.sw = sw;
	}

	@Override
	public void displayMaze(Maze3d maze) {
		sw.setMaze(maze);
		
	}

	@Override
	public void displayDir(String[] string) {
		

	}

	@Override
	public void displayHint(String string) {
		

	}

	@Override
	public void displayCrossSecion(int[][] maze2d) {
		

	}

	@Override
	public void displaySize(int size) {
		

	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		sw.setSolution(solution);
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//gets the command from GUI and notify Presenter.
		line = ((String) arg1).split(" ");
		command = hmap.get(line[0]);
		setChanged();
		notifyObservers(command);

	}
}
