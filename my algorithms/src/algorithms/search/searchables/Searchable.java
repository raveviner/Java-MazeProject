package algorithms.search.searchables;

import java.util.ArrayList;

import algorithms.search.State;
import algorithms.search.heuristics.Heuristic;
/**
 * <i>Searchable</i> is an interface that help us do an adaptation of a given problem to a given search algorithm.
 * 
 *
 * @param <T> - is the type which best describes the state.
 */
public interface Searchable<T> {
	/**
	 * this method takes the starting point of a given problem and adapt it to a {@link State}.
	 * @return State 
	 */
	public State<T> getStartState();
	/**
	 * this method takes the end point of a given problem and adapt it to a {@link State}.
	 * @return State 
	 */
	public State<T> getGoalState();
	/**
	 * this method takes all the possible moves from a given node in a problem, put it in an 
	 * ArrayList and adapt it to {@link State}. The cost of every move is calculated in this method.
	 * @param s - a given {@link State}.
	 * @return ArrayList
	 */
	public ArrayList<State<T>> successors(State<T> s);
	/**
	 * this method is used when {@link AStar} search algorithm is used. It takes all the possible 
	 * moves from a given node in a problem, put it in an ArrayList and adapt it to {@link State}. 
	 * The cost of each state is calculated using the {@link Heuristic} that is given as a parameter.
	 * @param s - State
	 * @param h - Heuristic
	 * @return
	 */
	public ArrayList<State<T>> successorsAStar(State<T> s, Heuristic<T> h);
	
	

}
