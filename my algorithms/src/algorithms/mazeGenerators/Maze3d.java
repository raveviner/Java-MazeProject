package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class Maze3d implements Serializable {

	private int[][][] maze3d;
	private int initialX, initialY, initialZ; // start point
	private int finalX, finalY, finalZ; // end point

	// constructor
	public Maze3d(int x, int y, int z) {
		maze3d = new int[x][y][z];

	}
	
	// c'tor that converts byte array to and int 3d array.
	public Maze3d(byte[] ary){
		maze3d=new int[(int)ary[1]][(int)ary[2]][(int)ary[3]];
		this.initialX=(int)ary[4];
		this.initialY=(int)ary[5];
		this.initialZ=(int)ary[6];
		this.finalX=(int)ary[7];
		this.finalY=(int)ary[8];
		this.finalZ=(int)ary[9];
		int counter=10;
		for (int k = 0; k < (int)ary[3]; k++) {
			for (int j = 0; j < (int)ary[2]; j++) {
				for (int i = 0; i < (int)ary[1]; i++) {
					this.maze3d[i][j][k]=(int)ary[counter];
					counter++;
				}
			}
		}
	}
	
	// copy constructor
	public Maze3d(Maze3d maze) {
		int x, y, z;
		x = maze.getXlength();
		y = maze.getYlength();
		z = maze.getZlength();
		maze3d = new int[x][y][z];
	}

	public void setStartPosition(int x, int y, int z) {
		initialX = x;
		initialY = y;
		initialZ = z;
	}

	public void setEndPosition(int x, int y, int z) {
		finalX = x;
		finalY = y;
		finalZ = z;
	}

	public int getXlength() {
		return maze3d.length;
	}

	public int getYlength() {
		return maze3d[0].length;
	}

	public int getZlength() {
		return maze3d[0][0].length;
	}

	public void insertValue(int x, int y, int z, int value) {
		maze3d[x][y][z] = value;
	}

	public int getValue(int x, int y, int z) {
		int value;
		value = maze3d[x][y][z];
		return value;
	}

	@Override
	public String toString() {
		return "Maze3d [maze3d=" + Arrays.deepToString(maze3d) + "]";
	}

	public void printMaze() {

		for (int h = 0; h < getZlength(); h++) {
			for (int j = 0; j < getYlength(); j++) {
				for (int i = 0; i < getXlength(); i++) {
					System.out.print(maze3d[i][j][h] + ", ");
				}
				System.out.print("\n");
			}
			System.out.println("----------------");
		}
	}

	public Position getStartPosition() {

		Position p = new Position(initialX, initialY, initialZ);
		return p;

	}

	public Position getGoalPosition() {

		Position p = new Position(finalX, finalY, finalZ);

		return p;
	}

	public ArrayList<Position> getPossibleMoves(Position p) {
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		ArrayList<Position> moves = new ArrayList<Position>();

		//
		if ((x + 1) < getXlength())
			if (maze3d[x + 1][y][z] == 0) {
				moves.add(new Position(x + 1, y, z));

			}
		if ((y + 1) < getYlength())
			if (maze3d[x][y + 1][z] == 0) {
				moves.add(new Position(x, y + 1, z));

			}
		if ((z + 1) < getZlength())
			if (maze3d[x][y][z + 1] == 0) {
				moves.add(new Position(x, y, z + 1));
			}

		if (x > 0)
			if (maze3d[x - 1][y][z] == 0) {
				moves.add(new Position(x - 1, y, z));
			}
		if (y > 0)
			if (maze3d[x][y - 1][z] == 0) {
				moves.add(new Position(x, y - 1, z));
			}
		if (z > 0)
			if (maze3d[x][y][z - 1] == 0) {
				moves.add(new Position(x, y, z - 1));
			}

		return moves;

	}

	public int[][] getCrossSectionByX(int x) {
		int[][] maze2d = new int[getZlength()][getYlength()];
		for (int i = 0; i < getYlength(); i++) {
			for (int j = 0; j < getZlength(); j++) {
				maze2d[j][i] = maze3d[x][i][j];
			}
		}

		return maze2d;

	}

	public int[][] getCrossSectionByY(int y) {
		int[][] maze2d = new int[getXlength()][getZlength()];
		for (int i = 0; i < getZlength(); i++) {
			for (int j = 0; j < getXlength(); j++) {
				maze2d[j][i] = maze3d[j][y][i];
			}
		}

		return maze2d;

	}

	public int[][] getCrossSectionByZ(int z) {
		int[][] maze2d = new int[getXlength()][getYlength()];
		for (int i = 0; i < getYlength(); i++) {
			for (int j = 0; j < getXlength(); j++) {
				maze2d[j][i] = maze3d[j][i][z];
			}
		}

		return maze2d;

	}

	public void printCrossSection(int[][] maze2d) {
		for (int i = 0; i < maze2d[0].length; i++) {
			for (int j = 0; j < maze2d.length; j++) {
				System.out.print(maze2d[j][i] + ", ");
			}
			System.out.print("\n");
		}
	}

	public Position[] getPossibleWalls(Position p) {
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		Position[] walls = new Position[6];

		if ((x + 2) < getXlength())
			if (maze3d[x + 1][y][z] == 1) {
				walls[0] = new Position(x + 1, y, z);

			}
		if ((y + 2) < getYlength())
			if (maze3d[x][y + 1][z] == 1) {
				walls[1] = new Position(x, y + 1, z);

			}
		if ((z + 2) < getZlength())
			if (maze3d[x][y][z + 1] == 1) {
				walls[2] = new Position(x, y, z + 1);
			}

		if (x > 1)
			if (maze3d[x - 1][y][z] == 1) {
				walls[3] = new Position(x - 1, y, z);
			}
		if (y > 1)
			if (maze3d[x][y - 1][z] == 1) {
				walls[4] = new Position(x, y - 1, z);
			}
		if (z > 1)
			if (maze3d[x][y][z - 1] == 1) {
				walls[5] = new Position(x, y, z - 1);
			}

		return walls;

	}
	
	/**
	 * <i>toByteArray</i> is a method that converts 3D maze into 1D array of bytes.
	 * Used in compression.
	 * @return byte[]
	 */
	public byte[] toByteArray() {
		byte[] ary = new byte[(this.getXlength() * this.getYlength() * this.getZlength())+10];
		int counter = 10;
		ary[0]= (byte)10; // indicates the number of variables that shouldn't be compressed.
		ary[1]=(byte)this.getXlength();//method also saves the high, length and depth of the maze
		ary[2]=(byte)this.getYlength();
		ary[3]=(byte)this.getZlength();
		ary[4]=(byte)this.initialX;//start position
		ary[5]=(byte)this.initialY;
		ary[6]=(byte)this.initialZ;
		ary[7]=(byte)this.finalX;//goal position
		ary[8]=(byte)this.finalY;
		ary[9]=(byte)this.finalZ;
		for (int k = 0; k < this.getZlength(); k++) {
			for (int j = 0; j < this.getYlength(); j++) {
				for (int i = 0; i < this.getXlength(); i++) {
					ary[counter] = (byte) this.maze3d[i][j][k];
					counter++;
				}
			}
		}
		return ary;
	}

	
	@Override
	public boolean equals(Object arg0) {
		if (this == arg0)
			return true;
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Maze3d maze = (Maze3d) arg0;
		if(Arrays.equals(this.toByteArray(), maze.toByteArray()))
			return true;
		return false;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maze3d == null) ? 0 : maze3d.hashCode());
		return result;
	}
	
	
	
	
}
