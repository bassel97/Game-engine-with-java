package main;

import gameEngine_core.Input;
import gameEngine_core.KeyCode;
import gameEngine_core.Scene;
import gameEngine_core.Time;
import viewModule.GE_Window;

public class GameWindowController {

	public static GameWindowController gameWindowController;

	boolean waitingGUI = true;

	GE_Window ge_window;

	boolean closeGame = false;

	public GameWindowController() {

		gameWindowController = this;

		PauseOnGUI();
	}

	public void StartGame() {
		System.out.println("Game Started");

		ge_window = new GE_Window("Boxes game", 1024, 720, false);

		ge_window.init();

		Input.setWindowId(ge_window.getWindowId());

		Scene scene1 = new Scene();

		scene1.start();

		double time = Time.getTime();
		double deltaTime_min = 1.0 / 30.0;

		while (!ge_window.shouldClose() && !closeGame) {
			Time.deltaTime = 0;

			time = Time.getTime();

			ge_window.draw();

			while (Time.deltaTime < deltaTime_min) {
				Time.deltaTime = Time.getTime() - time;
			}

			scene1.update();

			ge_window.swapBuffers();

			if (Input.getKeyPress(KeyCode.escape)) {
				GUI_WindowController.guiWindowController.PauseGameGUI();
				waitingGUI = true;
				PauseOnGUI();
			}

		}

		ge_window.stop();

		scene1.end();

		System.out.println("Game Closed");
	}

	public void PauseOnGUI() {
		try {
			if (waitingGUI) {
				synchronized (GameWindowController.gameWindowController) {
					GameWindowController.gameWindowController.wait();
					waitingGUI = !GUI_WindowController.guiWindowController.IsGameRunning();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void StopGame() {
		closeGame = true;
	}

}
