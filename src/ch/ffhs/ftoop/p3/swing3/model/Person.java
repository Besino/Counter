package ch.ffhs.ftoop.p3.swing3.model;

import java.util.Calendar;

public class Person {
	private String name;
	private String vorname;
	private int jahrgang;

	public Person(String name, String vorname, int jahrgang) {
		this.name = name;
		this.vorname = vorname;
		this.jahrgang = jahrgang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public int getJahrgang() {
		return jahrgang;
	}

	public void setJahrgang(int jahrgang) {
		this.jahrgang = jahrgang;
	}

	public int getAlter() {
		int aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
		return aktuellesJahr - jahrgang;
	}

	@Override
	public String toString() {
		return String.format("%s, %s (%d)", name, vorname, getAlter());
	}
}
