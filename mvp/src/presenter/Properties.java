package presenter;

import java.io.Serializable;
/**
 * Properties class holds the properties of the maze.
 *
 */
@SuppressWarnings("serial")
public class Properties implements Serializable {
	private int numOfThreads;
	private String interfaceView;
	private String searchAlgo;
	
	public Properties(int size, String intrView, String srchAlgo) {
		this.numOfThreads=size;
		this.interfaceView=intrView;
		this.searchAlgo=srchAlgo;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public String getInterfaceView() {
		return interfaceView;
	}
	public void setInterfaceView(String interfaceView) {
		this.interfaceView = interfaceView;
	}
	public String getSearchAlgo() {
		return searchAlgo;
	}
	public void setSearchAlgo(String searchAlgo) {
		this.searchAlgo = searchAlgo;
	}
	
	
}
