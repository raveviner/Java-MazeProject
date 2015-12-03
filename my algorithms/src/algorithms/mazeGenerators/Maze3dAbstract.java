package algorithms.mazeGenerators;



public abstract class Maze3dAbstract implements Maze3dGenerator {

	@Override
	public abstract Maze3d generate(int x, int y, int z);
	
	@Override
	public String measureAlgorithmTime(int x,int y,int z) {
		
		
		double time = (double)System.currentTimeMillis();
		generate(x, y, z);
		double time2 = (double)System.currentTimeMillis();
		
		
		return "algorithm time is: "+((time2-time));
	}
	
	
}
