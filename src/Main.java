import gameEngine_core.Input;
import gameEngine_core.Scene;
import gameEngine_core.Time;
import viewModule.GE_Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Game Started");

		GE_Window ge_window = new GE_Window("Cards game", 1280, 720, false);

		ge_window.init();

		Input.setWindowId(ge_window.getWindowId());

		Scene scene1 = new Scene();

		scene1.start();

		double time = Time.getTime();
		double deltaTime_min = 1.0 / 60.0;

		int frameCounter = 0;

		while (!ge_window.shouldClose()) {
			Time.deltaTime = 0;

			time = Time.getTime();
			frameCounter++;

			ge_window.draw();

			while (Time.deltaTime < deltaTime_min) {
				Time.deltaTime = Time.getTime() - time;
				// System.out.println("delta time " + Time.deltaTime + " time -
				// time " + (Time.getTime() - time));
			}
			//System.out.println("frameCounter : " + frameCounter);
			if (frameCounter == 60) {
				// System.out.println("Current second " + Time.getTime());
			}

			frameCounter %= 60;
			// System.out.println(Time.deltaTime);

			scene1.update();

			ge_window.swapBuffers();
		}

		ge_window.stop();

		scene1.end();

		System.out.println("Game Closed");

	}

}
