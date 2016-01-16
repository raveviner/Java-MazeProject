package presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class Presenter implements Observer {
	private Model m;
	private View v;
	private Command command;

	public Presenter(Model m, View v) {
		this.m = m;
		this.v = v;

	}

	@Override
	public void update(Observable o, Object arg) {		
		if (o == v) {

			command = (Command) arg;
			command.doCommand(v.getUserCommand());

		}
		if (o == m) {
			String hint = (String) arg;
			if (hint.equals("done dir"))
				v.displayDir((String[]) m.getData());
			else if (hint.equals("done generate maze"))
				v.displayHint(hint);
			else if (hint.equals("display this"))
				try {
					v.displayMaze((Maze3d) m.getFutureMaze());
				} catch (InterruptedException | ExecutionException e) {}
			else if (hint.equals("display"))
				v.displayMaze((Maze3d) m.getMaze());
			else if (hint.equals("display cross section"))
				v.displayCrossSecion((int[][]) m.getData());
			else if (hint.equals("maze is saved"))
				v.displayHint(hint);
			else if (hint.equals("maze is loaded"))
				v.displayHint(hint);
			else if (hint.equals("maze size"))
				v.displaySize((int) m.getData());
			else if (hint.equals("file size"))
				v.displaySize((int) m.getData());
			else if (hint.equals("maze is solved"))
				v.displayHint(hint);
			else if (hint.equals("display solution"))
				v.displaySolution((Solution<Position>)m.getSolution());
			else if (hint.equals("display solution this"))
				try {
					v.displaySolution((Solution<Position>)m.getFutureSolution());
				} catch (InterruptedException | ExecutionException e) {}
			else if (hint.equals("solutions are saved"))
				v.displayHint(hint);

		}
	}

}
