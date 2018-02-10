package ch.ffhs.ftoop.p1.printersample;

class AlternatingPrinter implements Runnable {
	private final String message;
	private final boolean[] alternator;
	private final boolean waitValue;

	public AlternatingPrinter(String message, boolean[] alternator,
			boolean waitValue) {
		this.alternator = alternator;
		this.message = message;
		this.waitValue = waitValue;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			printMessage(i);
		}
	}

	private void printMessage(int i) {
		synchronized (alternator) {
			while (alternator[0] != waitValue)
				try {
					alternator.wait();
				} catch (InterruptedException ex) {
				}
			System.out.println(i + ": " + message);
			alternator[0] = !waitValue; // toggle
			alternator.notifyAll();
		}
	}

	public static void main(String[] args) {
		// somewhere else
		final boolean[] alternator = new boolean[1];
		Runnable doSomething = new AlternatingPrinter(
				"Running, running, running!", alternator, true);
		Runnable doSomethingElse = new AlternatingPrinter(
				"Having some coffee!", alternator, false);
		Thread t1 = new Thread(doSomething);
		Thread t2 = new Thread(doSomethingElse);
		t1.start();
		t2.start();

	}
}
