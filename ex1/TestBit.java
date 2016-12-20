package uk.ac.cam.jl946.prejava.ex1;

public class TestBit {
	public static void main(String[] args) {
		long val = Long.decode(args[0]);
		int pos = Integer.parseInt(args[1]);
		PackedLong pl = new PackedLong(val);
		System.out.println(pl.get(pos));
	}
}