package uk.ac.cam.jl946.prejava.ex1;

public class SetBit {
	public static void main(String[] args) {
		long val = Long.decode(args[0]);
		int pos = Integer.parseInt(args[1]);
		boolean bool = Boolean.parseBoolean(args[2]);
		PackedLong pl = new PackedLong(val);
		pl.set(pos, bool);
		System.out.println(pl.get());
		long upl = PackedLong.set(val, pos, bool);
		System.out.println(upl);
	}
}