package uk.ac.cam.jl946.prejava.ex4;

import java.math.BigInteger;

public class FibonacciCache {
	private static BigInteger cache[] = null;
	
	public static void store() {
		if (cache == null) throw new NullPointerException("Cache is empty");
		
		cache[0] = BigInteger.valueOf(1);
		cache[1] = BigInteger.valueOf(1);
		for (int i = 2; i < cache.length; ++i) {
			cache[i] = cache[i - 1].add(cache[i - 2]);
		}
	}
	
	public static void reset(int size) {
		cache = new BigInteger[size];
	}
	
	public static BigInteger get(int i) throws Exception {
		if (cache == null) throw new Exception("Cache is empty");
		if (i < 0 || i >= cache.length) throw new ArrayIndexOutOfBoundsException();
		return cache[i];
	}
	
	public static void main(String[] args) throws Exception {
		int i = Integer.decode(args[0]);
		reset(i);
		store();
		for (int j = 0; j < i; ++j) {
			System.out.println((j+1) + "\t" + get(j));
		}
	}
}