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

public class SimpleLayoutExampleFrame extends JFrame {
	public SimpleLayoutExampleFrame() {
		super("Notizensammlung");
		setContentPane(createContentPane());
	}

	public static void main(String[] args)
			throws UnsupportedLookAndFeelException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JFrame f = new SimpleLayoutExampleFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(400, 150); // oder: f.pack();
		f.setVisible(true);
	}

	private JPanel createContentPane() {
		JPanel content = new JPanel(new BorderLayout(5, 5));
		JScrollPane listScroll = new JScrollPane(new JList<String>());
		listScroll.setPreferredSize(new Dimension(150, 200));
		content.add(listScroll, BorderLayout.WEST);

		JPanel detail = new JPanel(new BorderLayout(0, 5));
		JPanel header = new JPanel(new BorderLayout());

		header.add(new JLabel("Datum: "), BorderLayout.WEST);
		header.add(new JTextField(20), BorderLayout.CENTER);

		detail.add(header, BorderLayout.NORTH);

		JScrollPane textScroll = new JScrollPane(new JTextArea());

		detail.add(textScroll, BorderLayout.CENTER);

		JPanel footer = new JPanel(new GridLayout(1, 2));
		footer.add(new JButton("Hinzufügen"));
		footer.add(new JButton("Löschen"));

		detail.add(footer, BorderLayout.SOUTH);

		content.add(detail, BorderLayout.CENTER);
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		return content;
	}

}