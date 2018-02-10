package ch.ffhs.ftoop.p1.printersample;

class SynchronizedPrinter implements Runnable {
	private String message;
	private Object lock;

	public SynchronizedPrinter(String message, Object lock) {
		this.message = message;
		this.lock = lock;
	}

	public void run() {
		synchronized (lock) {
			for (int i = 0; i < 10; i++) {
				System.out.println(i + ": " + message);
			}
		}
	}

	// somewhere else
	public static void main(String[] args) {
		final Object lock = new Object();
		Runnable doSomething = new SynchronizedPrinter(
				"Running, running, running!", lock);
		Runnable doSomethingElse = new SynchronizedPrinter(
				"Having some coffee!", lock);
		Thread t1 = new Thread(doSomething);
		Thread t2 = new Thread(doSomethingElse);
		t1.start();
		t2.start();
	}
}