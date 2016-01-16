package algorithms.search.searchers;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.heuristics.MazeAirDistance;
import algorithms.search.searchables.SearchableMaze;
/**
 * 
 * AStar test class
 *
 */
public class AStarTest {

	
	@Test
	public void test() {
		
		MazeAirDistance<Position> h= new MazeAirDistance<Position>();
		AStar<Position> algo = new AStar<Position>(h);
		assertEquals(null, algo.search(null));

		algo = new AStar<Position>(null);

		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(5, 5, 5);
		SearchableMaze s = new SearchableMaze(maze);
		assertEquals(null, algo.search(s));
	}

}
