package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends Maze3dAbstract {

	@Override
	public Maze3d generate(int x, int y, int z) {

		// Randomized Prim's algorithm

		Maze3d maze = new Maze3d(x, y, z);
		Random rand = new Random();
		Position p = null;
		ArrayList<Position> list = new ArrayList<Position>();
		ArrayList<Position> blackList = new ArrayList<Position>();

		/* initializing the maze's values to 1's */
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int h = 0; h < z; h++) {
					maze.insertValue(i, j, h, 1);
				}
			}
		}

		int randomX = rand.nextInt(x - 2) + 1; // random position on x-axis
		int randomY = rand.nextInt(y - 2) + 1; // random position in y-axis
		int randomZ = rand.nextInt(z - 2) + 1; // random position in z-axis
		int randomWall = rand.nextInt(6); // chooses which wall will be the
											// enter point

		switch (randomWall) {
		case 0:
			randomX = 0;
			maze.insertValue(randomX, randomY, randomZ, 0); // enter point to
															// the maze
			maze.setStartPosition(randomX, randomY, randomZ);
			randomX++;
			maze.insertValue(randomX, randomY, randomZ, 0); // first step into
															// the maze
			p = new Position(randomX, randomY, randomZ);
			break;
		case 1:
			randomY = 0;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomY++;
			maze.insertValue(randomX, randomY, randomZ, 0);
			p = new Position(randomX, randomY, randomZ);
			break;
		case 2:
			randomZ = 0;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomZ++;
			maze.insertValue(randomX, randomY, randomZ, 0);
			p = new Position(randomX, randomY, randomZ);
			break;
		case 3:
			randomZ = maze.getZlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomZ--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			p = new Position(randomX, randomY, randomZ);
			break;
		case 4:
			randomY = maze.getYlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomY--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			p = new Position(randomX, randomY, randomZ);
			break;
		case 5:
			randomX = maze.getXlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomX--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			p = new Position(randomX, randomY, randomZ);
			break;
		}

		// maze creation starts here

		do {
			Position[] walls = maze.getPossibleWalls(p);
			for (int i = 0; i < 6; i++) {
				if (walls[i] != null)
					if (list.contains(walls[i]) == true) {
						list.remove(walls[i]);
						blackList.add(walls[i]);
					} else if (blackList.contains(walls[i]) == false)
						list.add(walls[i]);

			}

			if (list.size() > 0) {
				randomWall = rand.nextInt(list.size());
				p = list.get(randomWall);
				list.remove(randomWall);

				maze.insertValue(p.getX(), p.getY(), p.getZ(), 0);
			}

		} while (list.isEmpty() == false);

		randomX = rand.nextInt(x - 2) + 1; // random position on x-axis
		randomY = rand.nextInt(y - 2) + 1; // random position in y-axis
		randomZ = rand.nextInt(z - 2) + 1; // random position in z-axis
		randomWall = rand.nextInt(6); // chooses which wall will be the end
										// point

		switch (randomWall) {
		case 0:
			randomX = 0;
			maze.insertValue(randomX, randomY, randomZ, 0); // enter point to
															// the maze
			maze.setEndPosition(randomX, randomY, randomZ);
			randomX++;
			maze.insertValue(randomX, randomY, randomZ, 0); // first step into
															// the maze
			break;
		case 1:
			randomY = 0;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setEndPosition(randomX, randomY, randomZ);
			randomY++;
			maze.insertValue(randomX, randomY, randomZ, 0);
			break;
		case 2:
			randomZ = 0;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setEndPosition(randomX, randomY, randomZ);
			randomZ++;
			maze.insertValue(randomX, randomY, randomZ, 0);
			break;
		case 3:
			randomZ = maze.getZlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setEndPosition(randomX, randomY, randomZ);
			randomZ--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			break;
		case 4:
			randomY = maze.getYlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setEndPosition(randomX, randomY, randomZ);
			randomY--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			break;
		case 5:
			randomX = maze.getXlength() - 1;
			maze.insertValue(randomX, randomY, randomZ, 0);
			maze.setEndPosition(randomX, randomY, randomZ);
			randomX--;
			maze.insertValue(randomX, randomY, randomZ, 0);
			break;
		}

		return maze;
	}

}
