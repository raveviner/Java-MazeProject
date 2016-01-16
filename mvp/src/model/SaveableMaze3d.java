package model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

import algorithms.mazeGenerators.Maze3d;

public class SaveableMaze3d implements Serializable {

	
	private static final long serialVersionUID = 1L;
	String name;
	Maze3d maze;

	public SaveableMaze3d(String name, Maze3d maze) {
		super();
		this.name = name;
		this.maze = maze;
	}

	public SaveableMaze3d(byte[] ary) {
		byte[]a=Arrays.copyOfRange(ary, ary.length-4, ary.length);
		/*String newA = Arrays.toString(a);// [0,0,0,8]
		newA = newA.replace("[", "");
		newA = newA.replace("]", "");
		newA = newA.replace(", ", "");
		int newAInt = Integer.parseInt(newA);*/
		
		int b=ByteBuffer.wrap(a).getInt();
		byte[]m=Arrays.copyOfRange(ary, 0, ary.length-4-b);
		byte[]n=Arrays.copyOfRange(ary, b, ary.length-4);
		maze = new Maze3d(m);
		name= n.toString();
	}
	
	public byte[] toByteArray()
	{
		byte[] mazeByte = maze.toByteArray();
		byte[] nameByte = name.getBytes();
		byte[] totalBytesArr = new byte[mazeByte.length+nameByte.length+4];
		int a = nameByte.length;
		
		System.arraycopy(ByteBuffer.allocate(4).putInt(a).array(), 0, totalBytesArr, totalBytesArr.length-4, 4);
		System.arraycopy(mazeByte, 0, totalBytesArr, 0, mazeByte.length);
		System.arraycopy(nameByte, 0, totalBytesArr, mazeByte.length, nameByte.length);
		return totalBytesArr;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Maze3d getMaze() {
		return maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	@Override
	public boolean equals(Object obj) {
		Maze3d maze = (Maze3d) obj;
		if(maze.equals(this.maze))
			return true;
		else
			return false;
	}
	
}
