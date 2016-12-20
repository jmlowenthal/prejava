package uk.ac.cam.jl946.prejava.ex1;

/**
	@brief		64 bit boolean array
	Stores 64 boolean values in a zero-indexed bit "array".
*/
public class PackedLong {
	private long data;
	
	/**
		@brief		Initialises all values to `false`
	*/
	public PackedLong() {
		data = 0L;
	}
	
	/**
		@brief		Initialises from premade long
	*/
	public PackedLong(long val) {
		data = val;
	}
	
	/**
		@brief		Gets the boolean from the given position (zero-indexed)
	*/	
	public boolean get(int position) {
		return get(data, position);
	}
	
	/**
		@brief		Gets the boolean from the given position (zero-indexed)
	*/
	public static boolean get(long packed, int position) {
		return ((packed >>> position) & 1) != 0;
	}
	
	/**
		@brief		Gets bit array as a long
	*/
	public long get() {
		return data;
	}
	
	/**
		@brief		Sets an item in the array (zero-indexed)
	*/
	public void set(int position, boolean value) {
		data = set(data, position, value);
	}
	
	/**
		@brief		Sets an item in the array (zero-indexed)
	*/
	public static long set(long packed, int position, boolean value) {
		// mask data (set data[position] to 0) then fill with value
		 return (packed & ~(1L << position)) | (value ? 1L << position : 0L);
	}
	
	/**
		@brief		Sets the array from premade long
	*/
	public void set(long val) {
		data = val;
	}
}