package main;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import network.NetworkManager;

public class GUI_WindowController  {

	public static GUI_WindowController guiWindowController;

	boolean isGameRunning = false;

	public GUI_WindowController() {
		guiWindowController = this;
	}

	public String gameState = "QUIT";
	
	public void StartGameGUI() {

		JFrame jFrame = new JFrame();

		jFrame.setSize(500, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				isGameRunning = true;
				jFrame.setVisible(false);

				try {
					synchronized (GameWindowController.gameWindowController) {
						GameWindowController.gameWindowController.notify();
					}
				} catch (Exception e) {
				}

			}
		});

		JButton startAsServer = new JButton("Start as Server");
		startAsServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				NetworkManager.GetInstance().isServer = true;
				NetworkManager.GetInstance().start();

				isGameRunning = true;
				jFrame.setVisible(false);

				try {
					synchronized (GameWindowController.gameWindowController) {
						GameWindowController.gameWindowController.notify();
					}
				} catch (Exception e) {
				}

			}
		});

		JButton startAsClient = new JButton("Start As Client");
		startAsClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				NetworkManager.GetInstance().isServer = false;
				NetworkManager.GetInstance().start();

				isGameRunning = true;
				jFrame.setVisible(false);

				try {
					synchronized (GameWindowController.gameWindowController) {
						GameWindowController.gameWindowController.notify();
					}
				} catch (Exception e) {
				}

			}
		});

		start.setAlignmentY(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(start);
		panel.add(startAsServer);
		panel.add(startAsClient);

		jFrame.add(panel);

		jFrame.setVisible(true);
	}

	public void PauseGameGUI() {

		JFrame jFrame = new JFrame();

		jFrame.setSize(500, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JButton resume = new JButton("Resume");
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				isGameRunning = true;
				jFrame.setVisible(false);

				try {
					synchronized (GameWindowController.gameWindowController) {
						GameWindowController.gameWindowController.notify();
					}
				} catch (Exception e) {
				}

			}
		});

		resume.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(resume);

		jFrame.add(panel);

		jFrame.setVisible(true);
	}

	public void EndGameGUI() {

		JFrame jFrame = new JFrame();

		jFrame.setSize(500, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JLabel gameStateLabel = new JLabel(gameState);
		JLabel thanks = new JLabel("Thank you for playing :)");

		gameStateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		thanks.setAlignmentX(Component.CENTER_ALIGNMENT);


		panel.add(gameStateLabel);
		panel.add(thanks);

		jFrame.add(panel);

		jFrame.setVisible(true);
	}

	public boolean IsGameRunning() {
		return isGameRunning;
	}

}
