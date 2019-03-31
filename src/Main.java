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
		double deltaTime_min = 1.0/60.0;
		
		while (!ge_window.shouldClose()) {
			time = Time.getTime();
			
			ge_window.draw();

			scene1.update();

			ge_window.swapBuffers();
		
			while(Time.deltaTime < deltaTime_min)
				Time.deltaTime = Time.getTime() - time;
			
			//System.out.println(Time.deltaTime);
		}

		ge_window.stop();

		scene1.end();

		System.out.println("Game Closed");

	}

}
