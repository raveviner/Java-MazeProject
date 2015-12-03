package algorithms.search.searchers;

import algorithms.search.Solution;
import algorithms.search.searchables.Searchable;
/**
 * 
 * <i>Searcher</i> is an interface which represents search algorithms  .
 *
 * @param <T> - is the type which best describes the state of a search algorithm.
 */
public interface Searcher<T> {
	/**
	 * <i>search</i> is a method that contains a search algorithm.
	 * 
	 * @param s - represent a searchable problem.
	 * @return Solution.
	 */
	public Solution<T> search(Searchable<T> s);
	
	public int getNumberOfNodesEvaluated();

}
