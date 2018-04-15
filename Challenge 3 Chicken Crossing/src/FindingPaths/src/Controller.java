
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	public static void main(String[] args) {

		int gridSize = 4;
		//grid initialised to 0 
		int[][] grid = new int[gridSize][gridSize];

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = 0;

			}
		}

		//pot hole generater not used for this example
		grid[0][1] = 1;
		grid[3][0] = 1;
		grid[2][1] = 1;
		grid[0][2] = 1;

		//print grid
		System.out.println("GRID");
		gridPrinter(grid);
		System.out.println();
		//valid paths
		System.out.println("VALID PATHS");
		int paths = PathFinder(grid,0,0);
		System.out.println();
		//number of valid paths
		System.out.println("NUMBER OF PATHS :" + paths);
		

		System.out.println("FINISHED");

	}
	
	public static int PathFinder(int [][] grid,int x,int y){
		
		LinkedList<int[]> paths = new LinkedList<int[]>();

		LinkedList<Node> mem = new LinkedList<Node>();

		int numberOfPaths = pathFind(grid, 0, 0, -1, paths, mem);

		for (int i = 0; i < mem.size(); i++) {	
			Node temp = mem.get(i);
			while (temp != null) {
				System.out.print(temp.getPos()[0] + "," + temp.getPos()[1] + "  ");
				temp = temp.getLink();
			}

			System.out.println();

		}
		
		return numberOfPaths;
		
	}

	// function to find the number of valid paths
	public static int pathFind(int[][] grid, int x, int y, int lastMove, LinkedList<int[]> currentPath,
			LinkedList<Node> mem) {
		// holding number of valid paths found
		int numberPaths = 0;
		// storing the last move for mem
		int secondLastMove = -1;
		// add position to the current path linked list
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		currentPath.addLast(pos);

		// if x pos of chicken is on the left edge valid path is found
		if (x == grid.length - 1) {

			pathToMem(currentPath, mem);
			currentPath.removeLast();
			return 1;
		}

		// checks the mem linked list to see if the current point can already lead to a path
		for (int i = 0; i < mem.size(); i++) {
			boolean found = false;
			Node node = mem.get(i);
			//iterate for each node
			while (node != null && !(found)) {
				//if postion has been visited a new path has been found and is added to the mem linked list
				if (node.getPos()[0] == x && node.getPos()[1] == y) {
					// currentPath.removeLast();
					Node nextNode = node.getLink();

					if (nextNode != null) {
						if (nextNode.getPos()[0] > x) {
							secondLastMove = 2;
						} else if (nextNode.getPos()[1] < y) {
							secondLastMove = 1;
						} else if (nextNode.getPos()[1] > y) {
							secondLastMove = 0;
						}

					}

					Node temp = pathToMem(currentPath, mem);
					Node lastNode = new Node(currentPath.getLast());
					temp.setLink(nextNode);
					numberPaths += 1;
					found = true;
				}
				node = node.getLink();
			}

			if (found) {
				break;
			}

		}

		// check if x+ (right) is a valid move
		if (lastMove != 2 && secondLastMove != 2 && (x + 1) < grid.length && grid[x + 1][y] != 1) {

			numberPaths += pathFind(grid, x + 1, y, -1, currentPath, mem);
		}

		// check if y+ (down) is a valid move
		if (((lastMove != 1) && (secondLastMove != 1)) && (y + 1) < grid.length && grid[x][y + 1] != 1) {

			numberPaths += pathFind(grid, x, y + 1, 0, currentPath, mem);
		}

		// check if y- (up) is a valid move
		if (((lastMove != 0) && (secondLastMove != 1)) && (y - 1) > -1 && grid[x][y - 1] != 1) {

			numberPaths += pathFind(grid, x, y - 1, 1, currentPath, mem);

		}

		// remove last position of current path
		currentPath.removeLast();
		return numberPaths;

	}

	// add the current path to the mem linked list
	public static Node pathToMem(LinkedList<int[]> currentPath, LinkedList<Node> mem) {
		// convert the currentPath to a new path in the mem linked list
		Node node = new Node(currentPath.get(0));
		mem.addLast(node);
		Node temp = node;
		for (int i = 1; i < currentPath.size(); i++) {
			node = new Node(currentPath.get(i));
			temp.setLink(node);
			temp = node;
		}

		return temp;

	}
	
	//print the grid 
	public static void gridPrinter(int[][] grid){
		
		System.out.println("   0 1 2 3 ");
		System.out.println("   | | | |");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(i + "--");
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[j][i] + " ");

			}

			System.out.println();
		}
		
	}
	
	//randomly generates pot holes on grid
	public static void generatePotHoles(int[][] grid){
		Random rand = new Random();
		int  numberofPotHoles = rand.nextInt(grid.length * grid.length);
		
		for(int i=0;i<numberofPotHoles;i++){
			int x =  rand.nextInt(grid.length);
			int y =  rand.nextInt(grid.length);	
		}
	}

}
