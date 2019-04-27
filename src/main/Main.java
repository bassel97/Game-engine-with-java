package main;

import network.NetworkManager;

public class Main {

	public static void main(String[] args) {
		
		GUI_WindowController guiController = new GUI_WindowController();
		guiController.StartGameGUI();
		
		GameWindowController gameController= new GameWindowController();
		gameController.StartGame();
		
		try {
			NetworkManager.GetInstance().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.activeCount());
		
		guiController.EndGameGUI();
		
		
		
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
