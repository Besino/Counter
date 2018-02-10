package ch.ffhs.ftoop.p3.swing3.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class PersonModel extends AbstractListModel<Person> {
	private List<Person> allPersons = new ArrayList<>();
	private List<Person> filteredPersons = new ArrayList<>();
	private String nameFilter;
	private int minAge;
	private int maxAge;

	public PersonModel() {
		initializeContents();
		setFilter("", 0, 100);
	}

	private void initializeContents() {
		allPersons.add(new Person("Hugentobler", "Alfred", 1923));
		allPersons.add(new Person("Flick", "Klara", 1972));
		allPersons.add(new Person("Sommer", "Hans", 1963));
		allPersons.add(new Person("Ittinger", "Barbara", 1978));
		allPersons.add(new Person("Lauber", "Zina", 1983));
	}

	@Override
	public int getSize() {
		return filteredPersons.size();
	}

	@Override
	public Person getElementAt(int index) {
		return filteredPersons.get(index);
	}

	public void setFilter(String namePart, int minAge, int maxAge) {
		fireIntervalRemoved(this, 0, filteredPersons.size());
		filteredPersons.clear();

		namePart = namePart.toLowerCase();
		for (Person person : allPersons) {
			boolean nameMatch = person.toString().toLowerCase()
					.contains(namePart);
			boolean ageMatch = person.getAlter() >= minAge
					&& person.getAlter() <= maxAge;
			if (nameMatch && ageMatch) {
				filteredPersons.add(person);
			}
		}
		fireIntervalAdded(this, 0, filteredPersons.size());
	}
}
