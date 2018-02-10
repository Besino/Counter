package ch.ffhs.ftoop.p3.swing3.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleFormPanel extends JPanel {
	private JPanel form;
	private final Map<String, JTextField> fields = new HashMap<>();
	private final int LABEL_WIDTH;
	private final int LABEL_ALIGNMENT;

	public SimpleFormPanel(int labelWidth) {
		this(labelWidth, JLabel.LEFT);
	}

	public SimpleFormPanel(int labelWidth, int labelAlignment) {
		super(new BorderLayout());
		LABEL_WIDTH = labelWidth;
		LABEL_ALIGNMENT = labelAlignment;
		form = new JPanel(new GridLayout(0, 1, 0, 5));
		add(form, BorderLayout.NORTH);
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

		fields.put(labelText, textField);
		return textField;
	}

	public JTextField getTextField(String labelText) {
		return fields.get(labelText);
	}
}