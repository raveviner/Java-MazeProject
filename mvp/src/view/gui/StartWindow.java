package view.gui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import view.gui.dialog.MazeCon;
import view.gui.dialog.MazeProperties;
import view.gui.widget.MazeDisplay;

/**
 * Window designed for a 3d maze game.
 *
 */
public class StartWindow extends BasicWindow {

	private String srchAlgo;
	private String mazeName;
	private Maze3d m;
	private Position p; // used to save the position of the character in the
						// maze.
	private Solution<Position> s;
	private int counter = 0; //used in timer in solving

	public void setMazeName(String name) {
		mazeName = name;
	}

	//CTOR
	public StartWindow(int width, int height, String search) {
		super(width, height);
		this.srchAlgo=search;

	}

	public void setSolution(Solution<Position> s) {
		this.s = s;
	}

	public void setMaze(Maze3d m) {
		this.m = m;
	}

	@Override //inherited from BasicWindow
	public void initWidgets() {

		shell.setLayout(new GridLayout(2, false));

		/*creating a menu bar*/
		Menu menuBar = new Menu(shell, SWT.BAR);
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);

		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");
		cascadeFileMenu.setMenu(fileMenu);

		MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveItem.setText("&Save");
		fileSaveItem.setEnabled(false);

		MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
		fileLoadItem.setText("&Load");

		MenuItem filePropertiesItem = new MenuItem(fileMenu, SWT.PUSH);
		filePropertiesItem.setText("&Properties");

		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("&Exit");

		shell.setMenuBar(menuBar);
		
		//create button
		Button create = new Button(shell, SWT.PUSH);
		create.setText("Create Maze");
		create.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));

		//maze display widget
		MazeDisplay maze = new MazeDisplay(shell, SWT.DOUBLE_BUFFERED, display);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		maze.setEnabled(false);

		Button solve = new Button(shell, SWT.PUSH);
		solve.setText("Solve");
		solve.setEnabled(false);//enabled only when a maze is ready
		solve.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));

		//stepForward and backward labels will indicate whether i can step in or out in the maze
		Label stepForward = new Label(shell, SWT.READ_ONLY);
		stepForward.setText("Step Forward");
		stepForward.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));

		Label stepBackward = new Label(shell, SWT.READ_ONLY);
		stepBackward.setText("Step Backward");
		stepBackward.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));

		Button exit = new Button(shell, SWT.PUSH);
		exit.setText("Exit");
		exit.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));

		create.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				MazeCon configuration = new MazeCon(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, 150, 300);

				SelectionListener triggerOnOK = new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						String command = "generate 3d maze " + configuration.getNameInput().getText() + " "
								+ configuration.getxInput().getText() + " " + configuration.getyInput().getText() + " "
								+ configuration.getzInput().getText();

						mazeName = configuration.getNameInput().getText();
						// generates the maze
						setChanged();
						notifyObservers(command);

						// close the dialog
						configuration.getShell().close();

						// gets and displays the last generated maze
						setChanged();
						notifyObservers("display this");
						maze.setMaze(m);
						p = m.getStartPosition();
						maze.setPostion(p);
						maze.display();
						maze.redraw();
						if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
							stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
						else
							stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
						if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
							stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
						else
							stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));

						solve.setEnabled(true);
						fileSaveItem.setEnabled(true);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}
				};
				configuration.setSelectionListener(triggerOnOK);
				configuration.showConfigurationDialog();
				maze.setEnabled(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		maze.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.keyCode == SWT.ARROW_LEFT && m.getValue(p.getX() - 1, p.getY(), p.getZ()) == 0) {
					p.setX(p.getX() - 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));

				}

				if (e.keyCode == SWT.ARROW_RIGHT && m.getValue(p.getX() + 1, p.getY(), p.getZ()) == 0) {
					p.setX(p.getX() + 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				}

				if (e.keyCode == SWT.ARROW_DOWN && m.getValue(p.getX(), p.getY() + 1, p.getZ()) == 0) {
					p.setY(p.getY() + 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				}

				if (e.keyCode == SWT.ARROW_UP && m.getValue(p.getX(), p.getY() - 1, p.getZ()) == 0) {
					p.setY(p.getY() - 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				}

				if (e.keyCode == SWT.PAGE_UP && m.getValue(p.getX(), p.getY(), p.getZ() + 1) == 0) {
					p.setZ(p.getZ() + 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				}

				if (e.keyCode == SWT.PAGE_DOWN && m.getValue(p.getX(), p.getY(), p.getZ() - 1) == 0) {
					p.setZ(p.getZ() - 1);
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() + 1)))
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepForward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
					if (m.getPossibleMoves(p).contains(new Position(p.getX(), p.getY(), p.getZ() - 1)))
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
					else
						stepBackward.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
				}
				
				if(p.equals(m.getGoalPosition())){
					maze.setEnabled(false);
				}
				

			}
		});

		solve.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				String command = "solve " + mazeName + " "+srchAlgo;
				setChanged();
				notifyObservers(command);

				setChanged();
				notifyObservers("display solution this");

				ArrayList<State<Position>> list = s.getList();

				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						display.asyncExec(new Runnable() {
							public void run() {
								maze.setPostion(list.get(counter).getState());
								maze.display();
								maze.redraw();
							}
						});

						if (counter == list.size() - 1)
							timer.cancel();
						else
							counter++;
					}
				}, 0, 700);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		fileSaveItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setText("open");
				fd.setFilterPath("C:/Users/Rave/Documents/Java/project");
				String[] filterExt = { "*.mz" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();

				if (selected!=null) {
					String command = "save maze " + mazeName + " " + selected;
					setChanged();
					notifyObservers(command);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		fileLoadItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("C:/Users/Rave/Documents/Java/project");
				String[] filterExt = { "*.mz" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				mazeName = "loaded";

				if (selected!=null) {
					String command = "load maze " + selected + " loaded";
					setChanged();
					notifyObservers(command);
					setChanged();
					notifyObservers("display loaded");
					maze.setMaze(m);
					p = m.getStartPosition();
					maze.setPostion(p);
					maze.display();
					maze.redraw();
					maze.setEnabled(true);
					solve.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		fileExitItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting");
				int response = messageBox.open();
				if (response == SWT.YES) {
					String command = "exit";
					setChanged();
					notifyObservers(command);
					display.dispose();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		filePropertiesItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeProperties properties= new MazeProperties(shell,SWT.DIALOG_TRIM,500,300);
				properties.showPropertiesDialog();
				//Program.launch("properties.xml");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		exit.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("save solution solutions.gzip" );
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting");
				int response = messageBox.open();
				if (response == SWT.YES) {
					String command = "exit";
					setChanged();
					notifyObservers(command);
					display.dispose();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		//this happens when the x button in the window is pressed
		shell.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting");
				int response = messageBox.open();
				if (response == SWT.YES) {
					String command = "exit";
					setChanged();
					notifyObservers(command);
					display.dispose();
				}

			}

		});
	
	}

}
