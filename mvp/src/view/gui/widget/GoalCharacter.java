package view.gui.widget;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * This class holds the image of the character that appears in the goal position. It has a method paint, 
 * which draws the image in the widget.
 *
 */
public class GoalCharacter {

	private int x, y;
	private Display display; //display of the maze
	private Image image = new Image(display, "C:/Users/Rave/Documents/Java/project/King_pig.png");
	

	//CTOR
	public GoalCharacter(int x, int y,Display d) {
		this.x = x;
		this.y = y;
		this.display=d;
		
	}

	public void paint(PaintEvent e, int w, int h) {
		
		
		e.gc.drawImage(image, 0, 0,image.getBounds().width,image.getBounds().height,x,y,w,h);
		
	}
}
