package ch.ffhs.ftoop.p1.deadlock;

import org.junit.Test;

import student.TestCase;

public class DeadlockTest extends TestCase {

	public void testDoStuff() throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			Deadlock d = new Deadlock();
			d.doStuff();
		}

	}

}
