package view.gui.widget;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
/**
 * 
 * MazeDisplay is a widget responsible for the display of the maze.
 *
 */
public class MazeDisplay extends Canvas {

	private Maze3d maze;
	private Position position;//holds position of the character on the maze
	private GameCharacter character;//the image of the character
	private Display display; //initialized through the ctor
	private FinishGesture finish;//image pops when reaching goal position
	private GoalCharacter goal;//image displayed at the goal position

	//C'TOR
	public MazeDisplay(Composite parent, int style, Display d) {
		super(parent, style);
		setBackground(new Color(null, 255, 255, 255));
		this.display = d;

	}

	public void setPostion(Position p) {
		this.position = p;
	}

	public void setMaze(Maze3d m) {
		maze = m;
	}

	/**
	 * when display is called, it uses PaintListener to draw the maze.
	 */
	public void display() {
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

				int width = getSize().x; //width of the widget
				int height = getSize().y; //height of the widget
				
				//the width and height of every cell in the maze
				int w = width / maze.getCrossSectionByZ(position.getZ()).length;
				int h = height / maze.getCrossSectionByZ(position.getZ())[0].length;

				for (int i = 0; i < maze.getCrossSectionByZ(position.getZ()).length; i++)
					for (int j = 0; j < maze.getCrossSectionByZ(position.getZ())[0].length; j++) {
						int x = i * w;
						int y = j * h;
						if (maze.getCrossSectionByZ(position.getZ())[i][j] != 0)
							e.gc.fillRectangle(x, y, w, h);
					}

				if (position.getZ() == maze.getGoalPosition().getZ()) {
					goal = new GoalCharacter((maze.getGoalPosition().getX()) * w + 15,
							(maze.getGoalPosition().getY()) * h + 5, display);
					goal.paint(e, w - 30, h - 20);
				}
				character = new GameCharacter((position.getX() * w) + 5, (position.getY() * h) + 5, display);
				character.paint(e, w - 10, h - 10);

				if (position.equals(maze.getGoalPosition())) {

					finish = new FinishGesture(width/4, height/4, display);
					finish.paint(e, width/2, height/2);

				}

			}
		});

	}

}
