package gameEngine_Physics;

import java.util.ArrayList;

import gameEngine_core.Component;

public abstract class Collider extends Component {

	public boolean xMoveConstraint = false, yMoveConstraint = false, zMoveConstraint = false;

	public static ArrayList<Collider> colliders = new ArrayList<>();

	public abstract Collider canCollideWith(Collider otherCollider);
	
	
	public float bounciness = 0.1f;
	public float friction = 0.75f;

	public static void CheckCollions() {
		for (Collider collider : colliders) {
			for (Collider targetCollider : colliders) {
				if (collider != targetCollider) {
					collider.canCollideWith(targetCollider);
				}
			}
		}
	}

	public static Collider CheckCollions(Collider collider) {

		for (Collider targetCollider : colliders) {
			if (collider != targetCollider) {
				Collider c = collider.canCollideWith(targetCollider);
				if (c != null) {
					return c;
				}
			}
		}
		return null;
	}

}
