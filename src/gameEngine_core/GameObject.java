package gameEngine_core;

import java.util.ArrayList;

import org.joml.Matrix4f;

import gameEngine_Physics.BoxCollider;
import gameEngine_Physics.Collider;
import gameEngine_Physics.RigidBody;
import gameEngine_Physics.SphereCollider;

public class GameObject {

	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public final Transform transform = new Transform();

	public String name;
	
	boolean isActive = true;

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
		
		if(!isActive)
			return;
		
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
	
	public void SetActive(boolean active) {
		isActive = active;
	}
	
	public Collider getCollider(){
		for (Component component : components) {
			if(component.getClass() == Collider.class || component.getClass() == SphereCollider.class || component.getClass() == BoxCollider.class){
				return (Collider) component;
			}
		}
		return null;
	}
	
	public RigidBody getRigidBody(){
		for (Component component : components) {
			if(component.getClass() == RigidBody.class){
				return (RigidBody) component;
			}
		}
		return null;
	}

	public static GameObject Instantiate(GameObject target) {
		GameObject copy = new GameObject(target.name + "Copy");
		
		copy.transform.position.set(target.transform.position);
		copy.transform.rotation.set(target.transform.rotation);
		copy.transform.scale.set(target.transform.scale);
		
		for (Component component : target.components) {
			copy.addComponent( component.getComponentCopy() );
		}
		
		copy.start();
		
		return copy;
	}
}
