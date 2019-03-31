package gameEngine_Physics;

import org.joml.Vector3f;

public class BoxCollider extends Collider {

	private Vector3f boxBoundary = new Vector3f();

	public BoxCollider(float x, float y, float z) {
		colliders.add(this);
		boxBoundary.x = x;
		boxBoundary.y = y;
		boxBoundary.z = z;
	}

	@Override
	public boolean canCollideWith(Collider otherCollider) {

		if (otherCollider.getClass() == getClass()) {
			BoxCollider otherBoxCollider = (BoxCollider) otherCollider;

			Vector3f scaledBoundaryBox = new Vector3f(boxBoundary);
			scaledBoundaryBox.x *= gameObject.transform.scale.x;
			scaledBoundaryBox.y *= gameObject.transform.scale.y;
			scaledBoundaryBox.z *= gameObject.transform.scale.z;

			Vector3f otherScaledBoundaryBox = new Vector3f(otherBoxCollider.boxBoundary);
			otherScaledBoundaryBox.x *= otherBoxCollider.gameObject.transform.scale.x;
			otherScaledBoundaryBox.y *= otherBoxCollider.gameObject.transform.scale.y;
			otherScaledBoundaryBox.z *= otherBoxCollider.gameObject.transform.scale.z;

			float xDistance = Math
					.abs(gameObject.transform.position.x - otherBoxCollider.gameObject.transform.position.x);
			float yDistance = Math
					.abs(gameObject.transform.position.y - otherBoxCollider.gameObject.transform.position.y);
			float zDistance = Math
					.abs(gameObject.transform.position.z - otherBoxCollider.gameObject.transform.position.z);

			float boundX = (scaledBoundaryBox.x) / 2.0f + (otherScaledBoundaryBox.x) / 2.0f;
			float boundY = (scaledBoundaryBox.y) / 2.0f + (otherScaledBoundaryBox.y) / 2.0f;
			float boundZ = (scaledBoundaryBox.z) / 2.0f + (otherScaledBoundaryBox.z) / 2.0f;

			/*System.out.println(gameObject.name);
			System.out.println("X distance " + xDistance);
			System.out.println("Y distance " + yDistance);
			System.out.println("Z distance " + zDistance);

			System.out.println("X bound " + boundX);
			System.out.println("Y bound " + boundY);
			System.out.println("Z bound " + boundZ);*/

			/*if ((xDistance < boundX) && (yDistance < boundY) && (zDistance < boundZ)) {
				if (xDistance < boundX && !xMoveConstraint) {
					if (gameObject.transform.position.x < otherBoxCollider.gameObject.transform.position.x) {
						gameObject.transform.position.x = otherBoxCollider.gameObject.transform.position.x
								- (scaledBoundaryBox.x / 2.0f + otherScaledBoundaryBox.x / 2.0f);
					} else {
						gameObject.transform.position.x = otherBoxCollider.gameObject.transform.position.x
								+ (scaledBoundaryBox.x / 2.0f + otherScaledBoundaryBox.x / 2.0f);
					}
				}

				if (yDistance < boundY && !yMoveConstraint) {
					if (gameObject.transform.position.y < otherBoxCollider.gameObject.transform.position.y) {
						gameObject.transform.position.y = otherBoxCollider.gameObject.transform.position.y
								- (scaledBoundaryBox.y / 2.0f + otherScaledBoundaryBox.y / 2.0f);
					} else {
						gameObject.transform.position.y = otherBoxCollider.gameObject.transform.position.y
								+ (scaledBoundaryBox.y / 2.0f + otherScaledBoundaryBox.y / 2.0f);
					}
				}

				if (zDistance < boundZ && !zMoveConstraint) {
					if (gameObject.transform.position.z < otherBoxCollider.gameObject.transform.position.z) {
						gameObject.transform.position.z = otherBoxCollider.gameObject.transform.position.z
								- (scaledBoundaryBox.z / 2.0f + otherScaledBoundaryBox.z / 2.0f);
					} else {
						gameObject.transform.position.z = otherBoxCollider.gameObject.transform.position.z
								+ (scaledBoundaryBox.z / 2.0f + otherScaledBoundaryBox.z / 2.0f);
					}
				}
			}*/

			return (xDistance < boundX) && (yDistance < boundY) && (zDistance < boundZ);
		}

		return false;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

}
