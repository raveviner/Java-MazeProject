package algorithms.search.heuristics;

import algorithms.search.State;
import algorithms.search.searchables.Searchable;

/**
 * 
  * <i>MazeManhattanDistance</i> is a type of {@link Heuristic} that uses method h() to calculate
 * the cost of a given State. <i>MazeManhattanDistance</i> is used for mazes and the
 * cost is the distances in the X-axis + Y-axis + Z-axis from the goal state.
 *
 * @param <Position> - Position is the type which best describes the state.
 */
public class MazeManhattanDistance<Position> implements Heuristic<Position> {

	@Override
	public double h(State<Position> s, Searchable<Position> m) {
		int x=Math.abs(((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getX()-((algorithms.mazeGenerators.Position) s.getState()).getX());
		int y=Math.abs(((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getY()-((algorithms.mazeGenerators.Position) s.getState()).getY());
		int z=Math.abs(((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getZ()-((algorithms.mazeGenerators.Position) s.getState()).getZ());
		
		return (x+y+z);
	}
}
