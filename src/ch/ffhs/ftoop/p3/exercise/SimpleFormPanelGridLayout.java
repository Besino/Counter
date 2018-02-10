package ch.ffhs.ftoop.p3.exercise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class SimpleFormPanelGridLayout extends JPanel {
	private JPanel form;
	private final int LABEL_WIDTH;
	private final int LABEL_ALIGNMENT;

	public SimpleFormPanelGridLayout(int labelWidth, int labelAlignment) {
		super(new GridLayout(0, 1, 0, 5));
		LABEL_WIDTH = labelWidth;
		LABEL_ALIGNMENT = labelAlignment;
		form = this;

	}

	public JTextField addRow(String labelText) {
		JPanel row = new JPanel(new BorderLayout(5, 0));
		form.add(row);
		JLabel label = new JLabel(labelText, LABEL_ALIGNMENT);
		Dimension size = label.getPreferredSize();
		label.setPreferredSize(new Dimension(LABEL_WIDTH, size.height));
		row.add(label, BorderLayout.WEST);
		JTextField textField = new JTextField();
		row.add(textField, BorderLayout.CENTER);
		return textField;
	}

	public static void main(String[] args)
			throws UnsupportedLookAndFeelException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JFrame f = new JFrame("Uebung SimpleForm GridLayout");

		SimpleFormPanelGridLayout sfp = new SimpleFormPanelGridLayout(100,
				JLabel.LEFT);
		JTextField name = sfp.addRow("Name:");
		JTextField vorname = sfp.addRow("Vorname:");
		JTextField email = sfp.addRow("Email:");
		JTextField telefon = sfp.addRow("Telefon:");

		f.setContentPane(sfp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(400, 150); // oder: f.pack();
		f.setVisible(true);
	}

}
