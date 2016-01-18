package view.gui.widget;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
/**
 * This class holds the image that appear when character reaches goal position. It has a method paint, 
 * which draws the image in the widget.
 *
 */
public class FinishGesture {
	private int x, y; //position of the gesture in the display
	private Display display;//display of the maze
	private Image image = new Image(display, "C:/Users/Rave/Documents/Java/project/king_red.png");
	

	//CTOR
	public FinishGesture(int x, int y,Display d) {
		this.x = x;
		this.y = y;
		this.display=d;
		
	}

	public void paint(PaintEvent e, int w, int h) {
		
		e.gc.drawImage(image, 0, 0,image.getBounds().width,image.getBounds().height,x,y,w,h);
		
	}


}
