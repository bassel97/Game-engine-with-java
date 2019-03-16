import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Hello Game");

		if (!GLFW.glfwInit()) {
			System.err.println("Couldn't initialize glfw");
			System.exit(-1);
		}

		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		
		long window = GLFW.glfwCreateWindow(800, 600, "Testing s", 0, 0);
		
		if(window == 0) {
			System.err.println("Couldn't initialize glfw");
			System.exit(-1);
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - 800)/2, (videoMode.height() - 600)/2);
		
		GLFW.glfwShowWindow(window);
	
		while(!GLFW.glfwWindowShouldClose(window)) {
			GLFW.glfwPollEvents();
			
			GLFW.glfwSwapBuffers(window);
		}
		
	}
	
}
