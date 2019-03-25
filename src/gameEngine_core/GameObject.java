package gameEngine_core;

import java.util.ArrayList;

import org.joml.Matrix4f;

public class GameObject {

	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public final Transform transform = new Transform();

	public String name;

	private ArrayList<Component> components = new ArrayList<>();

	public GameObject() {
		gameObjects.add(this);
	}
	
	public GameObject(String name) {
		this.name = name;
		gameObjects.add(this);
	}

	public void start() {
		for (Component component : components) {
			component.start();
		}
	}

	public void update() {
		for (Component component : components) {
			component.update();
		}
	}

	public void end() {
		for (Component component : components) {
			component.end();
		}
	}
	
	public Matrix4f getTransformationMatrix() {
		return transform.getTransformMatrix();
	}
	
	public void setTransformParent(Transform transform) {
		this.transform.parent = transform;
	}

	public void addComponent(Component component) {
		components.add(component);
		
		component.gameObject = this;
	}

}
