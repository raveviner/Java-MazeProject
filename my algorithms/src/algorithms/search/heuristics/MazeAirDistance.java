package algorithms.search.heuristics;

import algorithms.search.State;
import algorithms.search.searchables.Searchable;

/**
 * 
 * <i>MazeAirDistance</i> is a type of {@link Heuristic} that uses method h() to calculate
 * the cost of a given State. <i>MazeAirDistance</i> is used for mazes and the
 * cost is the square root of the distances in the X-axis, Y-axis and Z-axis from the goal state.
 *
 * @param <Position> - Position is the type which best describes the state.
 */
public class MazeAirDistance<Position> implements Heuristic<Position> {

	@Override
	public double h(State<Position> s, Searchable<Position> m) {
		int x=((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getX()-((algorithms.mazeGenerators.Position) s.getState()).getX();
		int y=((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getY()-((algorithms.mazeGenerators.Position) s.getState()).getY();
		int z=((algorithms.mazeGenerators.Position) m.getGoalState().getState()).getZ()-((algorithms.mazeGenerators.Position) s.getState()).getZ();
		
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
	}

	
	

}
