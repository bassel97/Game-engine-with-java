package gameEngine_core;

import org.lwjgl.glfw.GLFW;

public class KeyCode {
	
	static boolean keys[] = new boolean[65536];
	
	public static int A = GLFW.GLFW_KEY_A;	
	public static int S = GLFW.GLFW_KEY_S;
	public static int D = GLFW.GLFW_KEY_D;
	public static int W = GLFW.GLFW_KEY_W;
	
	public static int right = GLFW.GLFW_KEY_RIGHT;
	public static int up = GLFW.GLFW_KEY_UP;
	public static int down = GLFW.GLFW_KEY_DOWN;
	public static int left = GLFW.GLFW_KEY_LEFT;
	
	public static int space = GLFW.GLFW_KEY_SPACE;
	
	public static int escape = GLFW.GLFW_KEY_ESCAPE;
	
}
