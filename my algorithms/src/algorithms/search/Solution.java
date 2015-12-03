package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class <i>Solution</i> represents an array of states that led to a solution in a
 * search algorithm.
 * 
 *
 * @param <T> - is the type which best describes the state.
 */

public class Solution<T> {

	private ArrayList<State<T>> list = new ArrayList<State<T>>();
	/**
	 * This method simply adds a given state (that is a part of a solution) to an array.  
	 * @param s - represent a State.
	 * @return ArrayList of States.
	 */
	public void AddSolution(State<T> s) {
		list.add(s);
	}

	public int getCost() {
		return (int) list.get(list.size() - 1).getCost();
	}

	@Override
	public String toString() {
		Collections.reverse(list);
		return list.toString();
	}

}
