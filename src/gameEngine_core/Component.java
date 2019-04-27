package gameEngine_core;

import java.util.ArrayList;

public abstract class Component {

	public GameObject gameObject;
	
	protected boolean isActive = true;
	
	public abstract void start();

	public abstract void update();
	
	public abstract void end();

	public static ArrayList<Component> components = new ArrayList<Component>();

	public Component() {
		components.add(this);

	}
	
	public void SetActive(boolean active) {
		isActive = active;
	}
	
	public abstract Component getComponentCopy();

}
