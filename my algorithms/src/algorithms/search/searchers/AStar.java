package algorithms.search.searchers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import algorithms.search.Solution;
import algorithms.search.State;
import algorithms.search.heuristics.Heuristic;
import algorithms.search.searchables.Searchable;

/**
 * <i>AStar</i> class contains the search algorithm AStar. 
 * AStar is a type of BFS algorithm, it arranges the most promising states according to a priority queue.
 * AStar uses heuristics to calculate every state cost.
 *
 * @param <T> - is the type which best describes the state.
 */
public class AStar<T> extends CommonSearcher<T> {

	private Heuristic<T> h;

	public AStar(Heuristic<T> heuristic) { // CTOR
		this.h = heuristic;
	}
	
	@Override
	public Solution<T> search(Searchable<T> s) {
		PriorityQueue<State<T>> open = new PriorityQueue<State<T>>();
		HashSet<State<T>> close = new HashSet<State<T>>();
		Solution<T> solution = new Solution<T>();
		ArrayList<State<T>> successors;
		State<T> state;

		open.add(s.getStartState());
		addEvaluated();

		while (open.isEmpty() == false) {
			state = open.poll();
			close.add(state);
			if (state.getState().equals(s.getGoalState().getState())) {
				do {
					solution.AddSolution(state);
					state = state.getCameFrom();
				} while (state.getCameFrom() != null);
				solution.AddSolution(state);
				return solution;
			}

			successors = s.successorsAStar(state, h);
			for (State<T> successor : successors) {

				if (open.contains(successor) == false && close.contains(successor) == false) {
					open.add(successor);
					addEvaluated();
				} else if (successor.getCost() < state.getCost()) {
					if (open.contains(successor) == false){
						open.add(successor);
						addEvaluated();}
					else
						state.setCost(successor.getCost());

				}

			}

		}

		return null;
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}

}
