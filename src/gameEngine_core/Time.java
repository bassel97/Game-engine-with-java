package gameEngine_core;

import org.lwjgl.glfw.GLFW;

public class Time {

	public static double deltaTime;
	
	public static double getTime(){
		return GLFW.glfwGetTime();
		//return System.nanoTime() / 1000000000.0;
	}
	
}
