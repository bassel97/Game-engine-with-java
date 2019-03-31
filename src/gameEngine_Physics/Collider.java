package gameEngine_Physics;

import java.util.ArrayList;

import gameEngine_core.Component;

public abstract class Collider extends Component {

	public boolean xMoveConstraint = false, yMoveConstraint = false, zMoveConstraint = false;

	public static ArrayList<Collider> colliders = new ArrayList<>();

	public abstract boolean canCollideWith(Collider otherCollider);

	public static void CheckCollions() {
		for (Collider collider : colliders) {
			for (Collider targetCollider : colliders) {
				if (collider != targetCollider) {
					collider.canCollideWith(targetCollider);
				}
			}
		}
	}

	public static boolean CheckCollions(Collider collider) {

		for (Collider targetCollider : colliders) {
			if (collider != targetCollider) {
				if (collider.canCollideWith(targetCollider)) {
					return true;
				}
			}
		}

		return false;
	}

}
