package gameEngine_core;

import java.util.ArrayList;

public abstract class Component {

	public GameObject gameObject;
	
	public abstract void start();

	public abstract void update();

	public static ArrayList<Component> components = new ArrayList<Component>();

	public Component() {
		components.add(this);

	}

}
