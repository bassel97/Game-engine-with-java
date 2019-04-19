package main;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import network.NetworkManager;

public class GUI_WindowController extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static GUI_WindowController guiWindowController;

	boolean isGameRunning = false;
	//JFrame frame = new JFrame();
	
	public GUI_WindowController() {
		guiWindowController = this;
	}

	public void StartGameGUI() {		
		
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
				
				isGameRunning = true;
				setVisible(false);

				try
			    {
			      synchronized(GameWindowController.gameWindowController) {
			    	  GameWindowController.gameWindowController.notify();
			      }
			    } catch (Exception e) {}

			}
		});
		
		JButton startAsServer = new JButton("Start as Server");
		startAsServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				NetworkManager.GetInstance().isServer = true;
				NetworkManager.GetInstance().start();
				
				isGameRunning = true;
				setVisible(false);
				
				try
			    {
			      synchronized(GameWindowController.gameWindowController) {
			    	  GameWindowController.gameWindowController.notify();
			      }
			    } catch (Exception e) {}

			}
		});
		
		JButton startAsClient = new JButton("Start As Client");
		startAsClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				NetworkManager.GetInstance().isServer = false;
				NetworkManager.GetInstance().start();
				
				isGameRunning = true;
				setVisible(false);
				
				try
			    {
			      synchronized(GameWindowController.gameWindowController) {
			    	  GameWindowController.gameWindowController.notify();
			      }
			    } catch (Exception e) {}

			}
		});

		start.setAlignmentY(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(start);
		panel.add(startAsServer);
		panel.add(startAsClient);

		this.add(panel);

		this.setVisible(true);
	}
	
	public void ShowGUI() {
		this.setVisible(true);
		isGameRunning = false;
	}
	
	public void Close() {
		System.out.println("Show end GUI");
		setVisible(true);
	}
	
	public boolean IsGameRunning() {
		return isGameRunning;
	}

}
