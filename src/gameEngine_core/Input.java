package gameEngine_core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

	private static long windowId;

	public static boolean getKeyDown(int keyCode) {

		return GLFW.glfwGetKey(windowId, keyCode) == 1;
	}

	private static boolean[] keys = new boolean[65536];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action == 1;
	}

	public static boolean getKeyPress(int keycode) {
		
		boolean returnV = keys[keycode];
		
		keys[keycode] = false;
		
		return returnV;
	}

	public static void setWindowId(long windowId) {
		Input.windowId = windowId;
	}

}
