package gameEngine_Physics;

import org.joml.Vector3f;

import gameEngine_core.Component;
import gameEngine_core.Time;

public class RigidBody extends Component {

	public static Vector3f gravity = new Vector3f(0, -9.8f, 0);

	public Vector3f velocity = new Vector3f();

	public Vector3f accelaration = new Vector3f();
	
	public float damping = 0.85f;
	
	boolean isColliding = false;

	public boolean isColliding() {
		return isColliding;
	}

	@Override
	public void start() {

	}

	@Override
	public void update() {
		
		if(!isActive)
			return;
		
		isColliding = false;
		
		Collider collider = gameObject.getCollider();
		
		accelaration.add(gravity);
		
		velocity = velocity.add(accelaration.mul((float)Time.fixedDeltaTime));
		
		Vector3f movementDircetion = new Vector3f(velocity).mul((float)Time.fixedDeltaTime);
		
		gameObject.transform.position.x += movementDircetion.x;		
		Collider collidedWith = Collider.CheckCollions(collider);
		if (collider != null) {
			if (collidedWith != null) {
				isColliding = true;
				
				gameObject.transform.position.x -= movementDircetion.x;
				velocity.x = 0;
			}
		}

		gameObject.transform.position.y += movementDircetion.y;
		collidedWith = Collider.CheckCollions(collider);
		if (collider != null) {
			if (collidedWith != null) {
				isColliding = true;
				
				gameObject.transform.position.y -= movementDircetion.y;
				velocity.y = -collidedWith.bounciness * velocity.y;
				
				accelaration.x -= velocity.x * collidedWith.friction;				
			}
		}
		
		gameObject.transform.position.z += movementDircetion.z;
		collidedWith = Collider.CheckCollions(collider);
		if (collider != null) {
			if (collidedWith != null) {
				isColliding = true;
				
				gameObject.transform.position.z -= movementDircetion.z;
				velocity.z = 0;
			}
		}
	}

	@Override
	public void end() {

	}
	
	@Override
	public Component getComponentCopy() {
		RigidBody copy = new RigidBody();
		copy.isActive = isActive;
		return copy;
	}

}
