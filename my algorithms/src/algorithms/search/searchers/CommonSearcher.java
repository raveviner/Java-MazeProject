package algorithms.search.searchers;

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	protected int evaluatedNodes=0;
	
	protected void addEvaluated(){
		this.evaluatedNodes++;
	}
	

}
