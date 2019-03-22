import gameEngine_core.Scene;
import viewModule.GE_Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Game Started");

		GE_Window ge_window = new GE_Window("Cards game", 1280, 720, true);

		ge_window.init();

		Scene scene1 = new Scene();

		scene1.start();

		while (!ge_window.shouldClose()) {
			ge_window.draw();

			scene1.update();

			ge_window.swapBuffers();
		}
		
		ge_window.stop();

		scene1.end();
		
		System.out.println("Game Closed");

	}

}
