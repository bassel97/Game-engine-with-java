package main;

import network.NetworkManager;

public class Main {

	public static void main(String[] args) {

		//System.out.println(Thread.currentThread());

		/*GUI_Window guiWindow = new GUI_Window();
		guiWindow.StartGUIWindow(Thread.currentThread());*/
		
		GUI_WindowController guiController = new GUI_WindowController();
		
		guiController.StartGameGUI();
		
		GameWindowController gameController = new GameWindowController();
		
		try {
			NetworkManager.GetInstance().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.activeCount());
		
		guiController.Close();
		
		
		
		/*boolean gameStarted = false;
		while (!gameStarted) {
			try {
				Thread.currentThread().wait();
			} catch (InterruptedException e) {
				// e.printStackTrace();
				gameStarted = guiWindow.GameStarted();
			}
		}*/
		
		

	}

}
