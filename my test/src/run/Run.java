package run;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {

	private static void testMazeGenerator(Maze3dGenerator mg, int x, int y, int z) {
		System.out.println(mg.measureAlgorithmTime(x, y, z));
		Maze3d maze = mg.generate(x, y, z);
		maze.printMaze();
		Position p = maze.getStartPosition();
		System.out.print("Start position: ");
		System.out.println(p);
		System.out.println("Possible moves position: ");
		System.out.println(maze.getPossibleMoves(p));
		System.out.print("Goal position: ");
		System.out.println(maze.getGoalPosition());

		try {
			System.out.println("Cross section by x:");
			int[][] maze2dx = maze.getCrossSectionByX(2);
			maze.printCrossSection(maze2dx);
			System.out.println("Cross section by y:");
			int[][] maze2dy = maze.getCrossSectionByY(5);
			maze.printCrossSection(maze2dy);
			System.out.println("Cross section by z:");
			int[][] maze2dz = maze.getCrossSectionByZ(0);
			maze.printCrossSection(maze2dz);

			maze.getCrossSectionByX(-1);

		} catch (IndexOutOfBoundsException e) {
			System.out.println("good!");
		}

	}

	public static void main(String[] args) {
		testMazeGenerator(new SimpleMaze3dGenerator(), 10, 10, 3);
		System.out.println("~~~~~~~~~~~~~~~~Randomized Prim's algorithm~~~~~~~~~~~~~~~~~~~~~~~~~~");
		testMazeGenerator(new MyMaze3dGenerator(), 10, 10, 3);

	}

}
