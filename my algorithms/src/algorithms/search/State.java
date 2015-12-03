package algorithms.search;

/**
 * We use <i>State</i> class to help us define each node in a search algorithm.
 * <i>State</i>'s data members are: T(type), cost, and the state it came from. 
 *
 * @param <T> - is the type which best describes the state.
 */

public class State<T> implements Comparable<State<T>> {
	private T state;
	private double cost; // cost to reach this state
	private State<T> cameFrom; // the state we came from to this state

	public State(T state, double c, State<T> s) { // CTOR
		this.state = state;
		this.cost = c;
		this.cameFrom = s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State<T> other = (State<T>) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	@Override
	public int compareTo(State<T> s) {
		if (this.cost > s.getCost())
			return 1;
		else if (this.cost < s.getCost())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {

		return state.toString();
	}

}
