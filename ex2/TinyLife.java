package uk.ac.cam.jl946.prejava.ex2;

public class TinyLife {
	public static void main(String[] args) throws java.io.IOException {
		/*TinyWorld world = new TinyWorld(Long.decode(args[0]));
		int input = 0;
		while (input != 'q') {
			world.print();
			System.out.println(world.get());
			world.next();
			input = System.in.read();
		}*/
		long world = -6618517516449998667L;
		TinyWorld.print(world);
		TinyWorld.print(TinyWorld.nextGeneration(world));
	}
}