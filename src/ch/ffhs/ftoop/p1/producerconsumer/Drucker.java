package ch.ffhs.ftoop.p1.producerconsumer;

public class Drucker extends Thread {
	private Speicher speicher;
	int min;
	int max;

	Drucker(Speicher s,int min,int max) {
		this.speicher = s;
		this.max = max;
		this.min = min;
	}

	/**
	 * Holt einen Wert vom Zaehler und gibt ihn aus, gefolgt von einem einzelnen
	 * Leerzeichen.
	 * 
	 */
	@Override
	public void run() {
		int slope = 0;
		int intervall = max-min;
		while (slope <= intervall) {
			try {
				synchronized (speicher){
					System.out.print(speicher.getWert() + " ");
					speicher.wait();
				}
			}
				catch (InterruptedException e) {
				e.printStackTrace();
			}
			slope++;
		}
			}

}
