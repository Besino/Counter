package ch.ffhs.ftoop.p1.counter;

/**
 * Wir wollen beobachten, wie sich unsynchronsierte Klassen in einer parallelen
 * Welt verhalten. Im Folgenden finden Sie eine unsynchronisierte Zählerklasse.
 * Vervollständigen Sie den Code so, dass n-viele Threads gestartetet werden.
 * Diese Threads sollen eine gemeinsame Instanz der Klasse Counter nutzen und
 * m-mal die inc()- * Methode aufrufen. Ihr Programm soll dann warten, bis alle
 * Threads terminieren. Prüfen Sie sodann, ob die Variable count in Counter den
 * korrekten Wert enthält.
 * 
 * a) Testen Sie mit unterschiedlichen Werten von n und m. b) Schreiben Sie zur
 * Verifikation einen passenden Unit-Test. c) Aendern Sie Counter so, dass sich
 * das Programm korrekt verhält.
 * 
 * 
 * @author ble
 * 
 */
public class CounterDemo {

	static class Counter {
		int count;

		synchronized void inc() {
			count = count + 1;
		}

		synchronized int getCount() {
			return count;
		}
	}

	// Diese Klasse soll nebenläufig abgarbeitet werden.
	class CounterThread implements Runnable {
		
		Counter counter;
		int m;
		
		CounterThread(Counter c, int m){
			this.counter = c;
			this.m = m;
		}
			public void run() {
				for (int i=0; i<m; i++) 
					counter.inc();
			}
		}


	// Hier werden die Threads gestartet.
	int runCounterThreads(int threadCount, int countCalls)
			throws InterruptedException {

		Counter counter = new Counter();
		
		Thread[] threads = new Thread[threadCount];
		for (int i=0;i<threadCount;i++) {
			threads[i] = new Thread(new CounterThread(counter, countCalls));
			threads[i].start();
		}
		for (int i=0;i<threadCount;i++) {
			threads[i].join();
		}
		
		return counter.getCount();
	}

	public static void main(String[] args) throws NumberFormatException,
			InterruptedException {
		/*
		if (args.length != 2) {
			System.out
					.println("Usage: CounterDemo <threadCount> <counterCalls>");
			System.exit(1);
		}*/
		CounterDemo demo = new CounterDemo();
		System.out.println("Counter is: "
				+ demo.runCounterThreads(100,5000));
	}

}
