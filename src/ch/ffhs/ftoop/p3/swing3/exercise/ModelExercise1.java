package ch.ffhs.ftoop.p3.swing3.exercise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ch.ffhs.ftoop.p3.swing3.model.Person;
import ch.ffhs.ftoop.p3.swing3.model.PersonModel;
import ch.ffhs.ftoop.p3.swing3.util.SimpleFormPanel;

public class ModelExercise1 extends JFrame {

	private JButton filterButton;
	private JButton resetButton;
	private JList<Person> personList;
	private JTextField textFilter;
	private JTextField maxAgeFilter;
	private JTextField minAgeFilter;

	public ModelExercise1() {
		super("Übung 3");
		setContentPane(createContentPane());
		addListeners();
	}

	private void addListeners() {
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textFilter.getText();
				int minAge;
				try {
					minAge = Integer.parseInt(minAgeFilter.getText());
				} catch (NumberFormatException e1) {
					minAge = 0;
				}
				int maxAge;
				try {
					maxAge = Integer.parseInt(maxAgeFilter.getText());
				} catch (NumberFormatException e1) {
					maxAge = 100;
				}
				getListModel().setFilter(text, minAge, maxAge);
			}
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFilter.setText("");
				minAgeFilter.setText("100");
				maxAgeFilter.setText("0");
				getListModel().setFilter("", 0, 100);
			}
		});
	}

	private PersonModel getListModel() {
		return (PersonModel) personList.getModel();
	}

	private JPanel createContentPane() {
		JPanel content = new JPanel(new BorderLayout(5, 5));

		SimpleFormPanel formPanel = new SimpleFormPanel(50);
		formPanel.setPreferredSize(new Dimension(200, 100));
		textFilter = formPanel.addRow("Text: ");
		minAgeFilter = formPanel.addRow("Min Age: ");
		maxAgeFilter = formPanel.addRow("Max Age:");

		filterButton = new JButton("Filter");
		resetButton = new JButton("Reset");
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		buttonPanel.add(filterButton);
		buttonPanel.add(resetButton);

		JPanel controls = new JPanel(new BorderLayout());
		controls.add(buttonPanel, BorderLayout.SOUTH);
		controls.add(formPanel, BorderLayout.CENTER);

		content.add(controls, BorderLayout.CENTER);

		personList = new JList<Person>(new PersonModel());
		JScrollPane rightScroll = new JScrollPane(personList);
		rightScroll.setPreferredSize(new Dimension(200, 200));
		content.add(rightScroll, BorderLayout.EAST);

		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		return content;
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		JFrame f = new ModelExercise1();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(400, 200); // oder: f.pack();
		f.setVisible(true);
	}
}
