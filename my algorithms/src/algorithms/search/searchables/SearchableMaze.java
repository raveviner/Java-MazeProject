package algorithms.search.searchables;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import algorithms.search.heuristics.Heuristic;

/**
 * 
 * <i>SearchableMaze</i> is a type of {@link Searchable}. 
 * It adapts a maze problem to states so it can be solved by search algorithms.
 *
 */
public class SearchableMaze implements Searchable<Position> {
	
	private Maze3d maze;
	
	//C'TOR is receiving a given maze
	public SearchableMaze(Maze3d m){ 
		this.maze=m;
	}
	
	@Override
	public State<Position> getStartState(){
		State<Position> s=new State<Position>(maze.getStartPosition(),0,null);
		return s;
		
	}
	
	@Override
	public State<Position> getGoalState(){
		State<Position> s=new State<Position>(maze.getGoalPosition(),0,null);
		return s;
		
	}
	
	@Override
	public ArrayList<State<Position>> successors(State<Position> s){
		ArrayList<State<Position>> list = new ArrayList<State<Position>>();
		ArrayList<Position> listP= maze.getPossibleMoves(s.getState());
		for (Position p: listP){
			list.add(new State<Position>(p,s.getCost()+1,s));
		}
		
		return list;
	}
	
	@Override
	public ArrayList<State<Position>> successorsAStar(State<Position> s, Heuristic<Position> h){
		ArrayList<State<Position>> list = new ArrayList<State<Position>>();
		ArrayList<Position> listP= maze.getPossibleMoves(s.getState());
		for (Position p: listP){
			list.add(new State<Position>(p,(h.h(s, this))+1,s));
		}
		
		return list;
	}

}
