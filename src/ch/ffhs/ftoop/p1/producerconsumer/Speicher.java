package ch.ffhs.ftoop.p1.producerconsumer;

//TODO Entfernen Sie den abstract modifier und implementieren Sie die fehlenden Methoden!
public class Speicher implements SpeicherIf {

	private int wert;
	private boolean hatWert = false;
	
	@Override
	public synchronized int getWert() throws InterruptedException {
		while (!isHatWert()) 
			wait();
		hatWert = false;
		notify();
		return wert;
	}
	@Override
	public synchronized void setWert(int wert) throws InterruptedException {
		while (!isHatWert())
			wait();
		this.wert = wert;
		hatWert = true;
		notify();
	}
	@Override
	public synchronized boolean isHatWert() {
		if (hatWert = true) {
			return true;
		}
		else {
		return false;
		}
	}

}
