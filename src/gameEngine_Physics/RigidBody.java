package gameEngine_Physics;

import org.joml.Vector3f;

import gameEngine_core.Component;
import gameEngine_core.Time;

public class RigidBody extends Component {

	public static Vector3f gravity = new Vector3f(0, -0.1f, 0);

	public Vector3f velocity = new Vector3f();

	public float damping = 0.85f;

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		//System.out.println("Rigid Body velocity of " + gameObject.name + " " + velocity);
		
		Collider collider = gameObject.getCollider();
		
		velocity.add(gravity);
		
		Vector3f movementDircetion = new Vector3f(velocity).mul((float)Time.deltaTime);
		
		gameObject.transform.position.x += movementDircetion.x;

		if (collider != null) {
			if (Collider.CheckCollions(collider)) {
				gameObject.transform.position.x -= movementDircetion.x;
			}
		}

		gameObject.transform.position.y += movementDircetion.y;

		if (collider != null) {
			if (Collider.CheckCollions(collider)) {
				gameObject.transform.position.y -= movementDircetion.y;
			}
		}
		
		gameObject.transform.position.z += movementDircetion.z;

		if (collider != null) {
			if (Collider.CheckCollions(collider)) {
				gameObject.transform.position.z -= movementDircetion.z;
			}
		}
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

}
