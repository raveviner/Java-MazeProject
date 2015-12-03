package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.heuristics.MazeAirDistance;
import algorithms.search.heuristics.MazeManhattanDistance;
import algorithms.search.searchables.SearchableMaze;
import algorithms.search.searchers.AStar;
import algorithms.search.searchers.BFS;
/**
 * 
 *this class is used to create a demonstration of the search algorithms. 
 *
 */
public class Demo {
	/**
	 * method run() generates and print a maze. It also prints the solution to the maze
	 * using the different search algorithms.
	 */
	public void run(){
		Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze=mg.generate(30, 30, 6);
		maze.printMaze();
		Solution<Position> s1, s2,s3;
		System.out.println("BFS:");
		BFS<Position> bfs=new BFS<Position>();
		s1=bfs.search(new SearchableMaze(maze));
		System.out.println(s1);
		System.out.println(bfs.getNumberOfNodesEvaluated());
		System.out.println("A* Air Distance:");
		AStar<Position> aStar=new AStar<Position>(new MazeAirDistance<Position>());
		s2=aStar.search(new SearchableMaze(maze));
		System.out.println(s2);
		System.out.println(aStar.getNumberOfNodesEvaluated());
		System.out.println("A* Manhattan Distance:");
		AStar<Position> aStar2=new AStar<Position>(new MazeManhattanDistance<Position>());
		s3=aStar2.search(new SearchableMaze(maze));
		System.out.println(s3);
		System.out.println(aStar2.getNumberOfNodesEvaluated());
	}
	
	
}
