package viewModule;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI_Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean startGame = false;

	Thread mainThread;

	public void StartGUIWindow(Thread mainThread) {

		this.mainThread = mainThread;

		// JFrame frame = new JFrame();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startGame = true;

				// System.out.println(java.lang.Thread.activeCount());

				// System.out.println(Thread.currentThread());

				// notifyAll();

				// System.out.println(mainThread.getState());

			}
		});

		start.setAlignmentY(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(start);

		this.add(panel);

		this.setVisible(true);

	}

	public boolean GameStarted() {
		return startGame;
	}

}
