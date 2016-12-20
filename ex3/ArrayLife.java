package uk.ac.cam.jl946.prejava.ex3;

public class ArrayLife {
	public static boolean getCell(boolean[][] world, int col, int row) {
		return row > -1 && row < world.length && col > -1 && col < world[row].length && world[row][col];
	}

	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if (row > -1 && row < world.length && col > -1 && col < world[row].length) world[row][col] = value;
	}

	public static void print(boolean[][] world) {
		for (int x = 0; x < world.length; ++x) {
			for (int y = 0; y < world[x].length; ++y) {
				System.out.print(getCell(world, x, y) ? "o " : "_ ");
			}
			System.out.println();
		}
	}

	public static int countNeighbours(boolean[][] world, int col, int row) {
		int count = 0;
		for (int x = -1; x < 2; ++x){
			for (int y = -1; y < 2; ++y) {
				if (x == 0 && y == 0) continue;
				if (getCell(world, col + x, row + y)) ++count;
			}
		}
		return count;
	}

	public static boolean computeCell(boolean[][] world, int col, int row) {
		int neighbours = countNeighbours(world, col, row);
		return getCell(world, col, row) ? neighbours > 1 && neighbours < 4 : neighbours == 3;
	}

	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean[][] next = new boolean[world.length][];
		for (int i = 0; i < world.length; ++i) {
			next[i] = new boolean[world[i].length];
		}
		
		for (int y = 0; y < world.length; ++y) {
			for (int x = 0; x < world[y].length; ++x) {
				setCell(next, x, y, computeCell(world, x, y));
			}
		}
		
		return next;
	}

	public static void play(boolean[][] world) throws java.io.IOException {
		int inp = 0;
		while (inp != 'q') {
			print(world);
			world = nextGeneration(world);
			inp = System.in.read();
		}
	}
	
	public static boolean getFromPackedLong(long packed, int position) {
		return ((packed >>> position) & 1) == 1;
	}

	public static void main(String[] args) throws java.io.IOException {
	   int size = Integer.parseInt(args[0]);
	   long initial = Long.decode(args[1]);
	   boolean[][] world = new boolean[size][size];
	   //place the long representation of the game board in the centre of "world"
	   for(int i = 0; i < 8; i++) {
		  for(int j = 0; j < 8; j++) {
			 world[i+size/2-4][j+size/2-4] = getFromPackedLong(initial,i*8+j);
		  }
	   }
	   play(world);
	}
}