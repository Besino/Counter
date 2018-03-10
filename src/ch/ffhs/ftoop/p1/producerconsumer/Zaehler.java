package ch.ffhs.ftoop.p1.producerconsumer;

public class Zaehler extends Thread {

	private Speicher speicher;
	private int max, min;

	/**
	 * 
	 * @param s
	 *            Das Speicherobject, das die aktuelle Zahl haelt.
	 * @param min
	 *            Der Startwert f�r den Zaehler
	 * @param max
	 *            Der Endwert f�r den Zaehler (einschliesslich)
	 * 
	 */
	Zaehler(Speicher s, int min, int max) {
		this.speicher = s;
		this.max = max;
		this.min = min;
	}

	/**
	 * Diese Run Methode z�hlt den Wert in Speicher hoch - von min bis max
	 * (einschliesslich).
	 * 
	 */
	@Override
	public void run() {
		int wert = min;
		
			while(wert<=max) {
			try {
				synchronized(speicher) {
				speicher.setWert(wert);
				speicher.wait();
				wert++;		
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
	}
	
}
