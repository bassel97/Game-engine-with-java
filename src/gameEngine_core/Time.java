package gameEngine_core;

public class Time {

	public static double deltaTime;
	
	public static double getTime(){
		return System.nanoTime() / 1000000000.0;
	}
	
}
