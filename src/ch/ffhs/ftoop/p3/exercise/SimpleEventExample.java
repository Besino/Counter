package ch.ffhs.ftoop.p3.exercise;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SimpleEventExample extends JFrame {

	public SimpleEventExample() {
		super("SimpleEventExample");
		setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel content = new JPanel(new BorderLayout(5, 5));

		final JLabel label = new JLabel("Was ist? Keine Lust zu klicken?");
		content.add(label, BorderLayout.CENTER);

		JButton button = new JButton("Klick mich!");
		button.addActionListener(new ActionListener() {
			private int counter = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				label.setText("Knopf wurde " + counter + "x gedrückt!");
			}
		});
		content.add(button, BorderLayout.EAST);

		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		return content;
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		JFrame f = new SimpleEventExample();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.pack();
		f.setVisible(true);
	}
}
