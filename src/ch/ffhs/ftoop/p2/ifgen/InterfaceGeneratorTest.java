package ch.ffhs.ftoop.p2.ifgen;

import org.junit.Test;

import student.TestCase;

public class InterfaceGeneratorTest extends TestCase {

	public void testIfGen() throws ClassNotFoundException {
		InterfaceGenerator
				.main(new String[] { "ch.ftoop.irgendeine.schoene.Klasse" });

		// TODO fuegen Sie hier einen passenden Vergleichsstring ein.
		assertFuzzyEquals("tbd", systemOut().getHistory());

	}

}
