package view.gui.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 * MazeCon class represent the dialog that pops up when generating maze.
 * It stores the X,Y,Z axis and the name of the maze.
 *
 */
public class MazeCon {

	private Shell shell;
	private Button set;
	private Text xInput, yInput, zInput, nameInput;

	public MazeCon(Shell parent, int style, int width, int height) {

		shell = new Shell(parent, style);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(width, height);
		shell.setText("Maze Size");

		new Label(shell, SWT.READ_ONLY).setText("X:");
		xInput = new Text(shell, SWT.BORDER);
		new Label(shell, SWT.READ_ONLY).setText("Y:");
		yInput = new Text(shell, SWT.BORDER);
		new Label(shell, SWT.READ_ONLY).setText("Z:");
		zInput = new Text(shell, SWT.BORDER);
		new Label(shell, SWT.READ_ONLY).setText("Name:");
		nameInput = new Text(shell, SWT.BORDER);

		set = new Button(shell, SWT.MODELESS);
		set.setText("Set");
		set.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

	}

	/**
	 * Adds a selection listener to the set button
	 */
	public void setSelectionListener(SelectionListener selectedEvent) {
		set.addSelectionListener(selectedEvent);
	}

	public void showConfigurationDialog() {
		shell.pack();
		shell.open();
	}

	public Text getxInput() {
		return xInput;
	}

	public void setxInput(Text xInput) {
		this.xInput = xInput;
	}

	public Text getyInput() {
		return yInput;
	}

	public void setyInput(Text yInput) {
		this.yInput = yInput;
	}

	public Text getzInput() {
		return zInput;
	}

	public void setzInput(Text zInput) {
		this.zInput = zInput;
	}

	public Text getNameInput() {
		return nameInput;
	}

	public void setNameInput(Text nameInput) {
		this.nameInput = nameInput;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

}
