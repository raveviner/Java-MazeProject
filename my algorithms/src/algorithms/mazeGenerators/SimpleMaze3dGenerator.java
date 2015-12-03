package algorithms.mazeGenerators;
import java.util.Random;

	public class SimpleMaze3dGenerator extends Maze3dAbstract {

	@Override
	public Maze3d generate(int x,int y, int z) {
		Maze3d maze = new Maze3d(x,y,z);
		Random rand=new Random();
		
		/*initializing the array's values to 1's*/
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				for(int h=0;h<z;h++){
					maze.insertValue(i, j, h, 1);
				}
			}
		}
		
		int randomX = rand.nextInt(x-2)+1; //random position on x-axis
		int randomY = rand.nextInt(y-2)+1; //random position in y-axis
		int randomZ = rand.nextInt(z-2)+1; //random position in z-axis
		int randomWall = rand.nextInt(6); //chooses which wall will be the enter point
		int errorMove = 0; //next move that cannot be performed (so the route generator won't go back)
		
		switch (randomWall){
		case 0: 
			randomX = 0;
			maze.insertValue(randomX,randomY ,randomZ , 0); //enter point to the maze
			maze.setStartPosition(randomX, randomY, randomZ);
			randomX++;
			maze.insertValue(randomX,randomY ,randomZ , 0); //first step into the maze
			errorMove = 5;
			break;
		case 1: 
			randomY = 0;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomY++;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			errorMove = 4;
			break;
		case 2: 
			randomZ = 0;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomZ++;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			errorMove = 3;
			break;
		case 3: 
			randomZ = maze.getZlength()-1;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomZ--;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			errorMove = 2;
			break;
		case 4: 
			randomY = maze.getYlength()-1;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomY--;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			errorMove = 1;
			break;
		case 5: 
			randomX = maze.getXlength()-1;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			maze.setStartPosition(randomX, randomY, randomZ);
			randomX--;
			maze.insertValue(randomX,randomY ,randomZ , 0);
			errorMove = 0;
			break;
		}
		
		/*while stops when the route generator reaches one of the cube's sides*/
		while (randomX != x-1 && randomY != y-1 && randomZ != z-1 && randomX != 0 && randomY != 0 && randomZ != 0){
			int randomMove = rand.nextInt(6); //move possible in the maze: 0-right, 1-up, 2-forward, 3-backwards, 4-down, 5-left.
			if (randomMove != errorMove) {
				switch (randomMove) {
				case 0:
					randomX++;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 5;
					break;
				case 1:
					randomY++;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 4;
					break;
				case 2:
					randomZ++;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 3;
					break;
				case 3:
					randomZ--;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 2;
					break;
				case 4:
					randomY--;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 1;
					break;
				case 5:
					randomX--;
					maze.insertValue(randomX, randomY, randomZ, 0);
					errorMove = 0;
					break;
				}
			} 
		} 
		
		maze.setEndPosition(randomX, randomY, randomZ);
		
		/*generating random 1's or 0's in every empty cell in the maze*/
		for(int i=1;i<x-1;i++){
			for(int j=1;j<y-1;j++){
				for(int h=1;h<z-1;h++){
					if(maze.getValue(i, j, h)!=0)
						maze.insertValue(i, j, h, rand.nextInt(2));
				}
			}
		}
		
		
			
		return maze;
	}
	
	
}


