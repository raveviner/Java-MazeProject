package view;

import java.util.HashMap;
import java.util.Observable;

import presenter.Command;

/**
 * ObservableView is an abstract class representing classes that are View and also
 * Observable. Meaning they can send notify to Observers.
 *
 */
public abstract class ObservableView extends Observable {

	protected String[] line = null; //line stores the whole command the user inserted
	protected Command command; //command stores only the command taken from the line the user inserted
	protected HashMap<String, Command> hmap;

	public ObservableView(HashMap<String, Command> hmap) {
		super();
		this.hmap = hmap;
	}

	public String[] getUserCommand() {
		return this.line;
	}
	
	public void passCommand(String string) {
		line = string.split(" ");
		command = hmap.get(line[0]);
		setChanged();
		notifyObservers(command);
	}

}
