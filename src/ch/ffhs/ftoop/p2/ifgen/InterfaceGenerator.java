package ch.ffhs.ftoop.p2.ifgen;

public class InterfaceGenerator {

	public void generate(String classname) throws ClassNotFoundException {
		// TODO Implementieren Sie hier den Interface Generator und geben Sie
		// den erzeugten Code nach stdout aus.
	}

	public static void main(String[] args) throws ClassNotFoundException {
		if (args.length != 1) {
			System.out.println("Usage: InterfaceGenerator <classname>");
			System.exit(1);
		}

		InterfaceGenerator ifgen = new InterfaceGenerator();
		ifgen.generate(args[0]);

	}

}
