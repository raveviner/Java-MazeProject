package view.gui;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * The most basic type of window. Implements Runnable since it runs in a new thread.
 *
 */
public abstract class BasicWindow extends Observable implements Runnable {

	Display display;
	Shell shell;
	int height, width;
	
	/**
	 * C'TOR can initiate width and height of the window.
	 * @param width
	 * @param height
	 */
	public BasicWindow(int width, int height){
		this.height=height;
		this.width=width;
		
	}
	
	/**
	 * abstract method responsible for all the widgets the window will display.
	 */
	abstract void initWidgets(); 
	
	@Override //inherited from runnable
	public void run() {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText("3D Maze");
		initWidgets();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
