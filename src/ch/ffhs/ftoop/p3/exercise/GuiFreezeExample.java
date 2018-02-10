package ch.ffhs.ftoop.p3.exercise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GuiFreezeExample extends JFrame {

	private JButton freezeButton;
	private JButton asyncUpdateButton;
	private JButton resetButton;
	private JLabel progressLabel;
	private JList<String> leftList;
	private JList<String> rightList;
	public boolean debug = false;

	public GuiFreezeExample() {
		super("GUI Freeze");
		setContentPane(createContentPane());
		addListeners();
	}

	private DefaultListModel<String> getModel(JList<String> list) {
		return ((DefaultListModel<String>) list.getModel());
	}

	private void addListeners() {
		freezeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FileFinder(".txt", leftList).findFiles();
			}
		});
		asyncUpdateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FileFinder(".txt", rightList).execute();
			};

		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				progressLabel.setText("");
				((DefaultListModel<String>) leftList.getModel()).clear();
				((DefaultListModel<String>) rightList.getModel()).clear();
			}
		});
	}

	private JPanel createContentPane() {
		JPanel content = new JPanel(new BorderLayout(5, 5));

		leftList = new JList<>(new DefaultListModel<String>());
		JScrollPane leftScroll = new JScrollPane(leftList);
		leftScroll.setPreferredSize(new Dimension(200, 200));
		content.add(leftScroll, BorderLayout.WEST);

		freezeButton = new JButton("Freeze");
		asyncUpdateButton = new JButton("No Freeze");
		resetButton = new JButton("Reset");
		progressLabel = new JLabel();
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		buttonPanel.add(freezeButton);
		buttonPanel.add(asyncUpdateButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(progressLabel);

		JPanel buttonWrapper = new JPanel(new BorderLayout());
		buttonWrapper.add(buttonPanel, BorderLayout.NORTH);
		content.add(buttonWrapper, BorderLayout.CENTER);

		rightList = new JList<>(new DefaultListModel<String>());
		JScrollPane rightScroll = new JScrollPane(rightList);
		rightScroll.setPreferredSize(new Dimension(200, 200));
		content.add(rightScroll, BorderLayout.EAST);

		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		return content;
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		JFrame f = new GuiFreezeExample();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // wichtig!
		f.setSize(600, 400); // oder: f.pack();
		f.setVisible(true);
	}

	private class FileFinder extends SwingWorker<List<File>, File> {
		private final String ext;
		private final JList<String> list;
		private final List<File> files;
		private int counter;

		public FileFinder(String ext, JList<String> list) {
			this.ext = ext.toLowerCase();
			this.list = list;
			this.files = new ArrayList<>();
		}

		public void findFiles() {
			List<File> todo = new ArrayList<>();
			todo.add(File.listRoots()[0]);
			while (!todo.isEmpty()) {
				File f = todo.remove(0);
				if (debug) {
					System.out.println(f);
				}
				updateCounter(todo.size());
				if (f.isDirectory()) {
					File[] children = f.listFiles();
					if (children != null) {
						todo.addAll(Arrays.asList(children));
					}
				} else if (f.isFile()) {
					if (f.getName().toLowerCase().endsWith(ext)) {
						files.add(f);
						publish(f);
					}
				}
			}
		}

		private void updateCounter(int total) {
			counter++;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					progressLabel.setText(counter + " files read.");
				}
			});
		}

		@Override
		protected List<File> doInBackground() throws Exception {
			findFiles();
			return files;
		}

		@Override
		protected void process(List<File> chunks) {
			for (File file : chunks) {
				getModel(list).addElement(file.getPath());
			}
		}
	}
}
