package uk.ac.cam.jl946.prejava.ex2;
import uk.ac.cam.jl946.prejava.ex1.PackedLong;

/**
	@brief		Abstraction of 64-bit Conway GoL world
	Includes static functions for a non-typed world (i.e. a long).
*/
public class TinyWorld {
	PackedLong world = new PackedLong();
	
	/**
		@brief		Initialises world to given 64-bit long
	*/
	public TinyWorld(long w) {
		world = new PackedLong(w);
	}
	
	/**
		@brief		Initialises world with pre-packed long
	*/
	public TinyWorld(PackedLong pl) {
		world = pl;
	}
	
	/**
		@brief		Gets the underlying data
	*/
	public long get() {
		return world.get();
	}
	
	/**
		@brief		Gets a particular item from the PackedLong
	*/
	public boolean get(int i) {
		return world.get(i);
	}
	
	/**
		@brief		Gets a particular cell (x, y) from the world
	*/
	public boolean get(int col, int row) {
		return getCell(world.get(), col, row);
	}
	
	/**
		@brief		Gets a particular cell from a non-typed world
	*/
	public static boolean getCell(long world, int col, int row) {
		return PackedLong.get(world, row * 8 + col) && (col > -1 && col < 8 && row > -1 && row < 8);
	}
	
	/**
		@brief		Sets the world to a 64-bit long
	*/
	public void set(long l){
		world.set(l);
	}
	
	/**
		@brief		Sets a position in the underlying PackedLong
	*/
	public void set(int i, boolean v) {
		world.set(i, v);
	}
	
	/**
		@brief		Sets a cell (x, y) to a given value
	*/
	public void set(int col, int row, boolean value) {
		world.set(setCell(world.get(), col, row, value));
	}
	
	/**
		@brief		Sets a cell (x, y) of a non-typed world to a given value
	*/
	public static long setCell(long world, int col, int row, boolean value) {
		return PackedLong.set(world, row * 8 + col, value);
	}
	
	/**
		@brief		Counts the number of neighbours a cell in a non-typed world has
	*/
	public static int countNeighbours(long world, int col, int row) {
		int count = 0;
		for (int x = -1; x < 2; ++x){
			for (int y = -1; y < 2; ++y) {
				if (x == 0 && y == 0) continue;
				if (getCell(world, col + x, row + y)) ++count;
			}
		}
		return count;
	}
	
	/**
		@brief		Determines whether the cell (from a non-typed world) should survive
	*/
	public static boolean computeCell(long world, int col, int row) {
		int neighbours = countNeighbours(world, col, row);
		return getCell(world, col, row) ? neighbours > 1 && neighbours < 4 : neighbours == 3;
	}
	
	/**
		@brief		Steps the simulation
	*/
	public void next() {
		world.set(nextGeneration(world.get()));
	}
	
	/**
		@brief		Step the simulation for a non-type world
		@return		New world
	*/
	public static long nextGeneration(long world) {
		long next = 0L;
		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) {
				next = setCell(next, x, y, computeCell(world, x, y));
			}
		}
		return next;
	}
	
	/**
		@brief		Print the world
	*/
	public void print() {
		print(world.get());
	}
	
	/**
		@brief		Print a non-typed world
	*/
	public static void print(long world) {
		for (int i = 0; i < 64; ++i) {
			System.out.print(PackedLong.get(world, i) ? "o " : "_ ");
			if (i % 8 == 7) System.out.println();
		}
	}
	
	/**
		@brief		Play GoL with a new world
	*/
	public static void play(long world) throws java.io.IOException {
		int inp = 0;
		while (inp != 'q') {
			print(world);
			world = nextGeneration(world);
			inp = System.in.read();
		}
	}
	
	/**
		@brief		Parse command line arguments and play with new world
	*/
	public static void main(String[] args) throws java.io.IOException {
		play(Long.decode(args[0]));
	}
}