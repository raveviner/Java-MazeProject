package algorithms.search.heuristics;

import algorithms.search.State;
import algorithms.search.searchables.Searchable;
/**
 * <i>Heuristic</i> is an interface that help calculate the cost of every node in 
 * an {@link AStar} algorithm. It contains method h() that does the calculations.
 *
 * @param <T> - is the type which best describes the state.
 */
public interface Heuristic<T> {
	/**
	 * this method calculate the cost of a given {@link State}.
	 * @param s - State
	 * @param m - Searchable type
	 * @return double
	 */
	public double h(State<T> s, Searchable<T> m);


}
