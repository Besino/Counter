package ch.ffhs.ftoop.p2.finalizerdemo;

public class FinalizerTest {
	int id;

	public FinalizerTest(int id) {
		this.id = id;
	}

	protected void finalize() throws Throwable {
		System.out.println(id + " was finalized!");
	}

	public static void main(String[] args) throws Exception {
		FinalizerTest[] testObjects = new FinalizerTest[10];
		for (int i = 0; i < testObjects.length; i++) {
			testObjects[i] = new FinalizerTest(i);
		}
		System.gc();
		testObjects = null; // was passiert bei Auskommentierung?
		System.gc(); // was passiert bei Auskommentierung?
		Thread.sleep(1000); // sleep 1 second
		System.out.println("Regular exit!");
	}
}