package ch.ffhs.ftoop.p1.deadlock;

/**
 * Aufgabe: Dieses Programm demonstriert einen Deadlock. Lassen Sie dieses
 * Programm mehrfach laufen und schauen Sie, was passiert.
 * 
 * a) Erklären Sie das Verhalten des Programms. b) Korrigieren Sie das Programm,
 * so dass es sich korrekt verhält. Verändern Sie dabei nicht die Klasse Friend.
 * 
 * @author bele
 * 
 */
public class Deadlock {

	synchronized void doStuff() throws InterruptedException {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		Thread gastonThread = new Thread(new Runnable() {
			synchronized public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				alphonse.bow(gaston);
			}
		}, "Gaston");
		
		
		Thread alphonseThread = new Thread(new Runnable() {
			synchronized public void run() {
				
				gaston.bow(alphonse);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Alphonse");
		
		alphonseThread.start();
		gastonThread.start();

		alphonseThread.join();
		gastonThread.join();

	}

	public static void main(String[] args) throws InterruptedException {
		Deadlock d = new Deadlock();

		d.doStuff();
		
	}
}

class Friend {
	private final String name;

	public Friend(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Friend bower) {
		System.out.format("%s: %s" + "  has bowed to me!%n", this.name,
				bower.getName());
				
			bower.bowBack(this);
	}

	public synchronized void bowBack(Friend bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name,
				bower.getName());
	}
}
