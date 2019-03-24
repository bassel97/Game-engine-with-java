package gameEngine_core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {
	public Vector3f position = new Vector3f(0, 0, 0);

	public Vector3f scale = new Vector3f(1, 1, 1);

	public Vector3f rotation = new Vector3f(0,0,0);

	public Transform parent = null;

	public Matrix4f getTransformMatrix() {
		Matrix4f transformMat = new Matrix4f().identity();

		transformMat.translate(position.x, position.y, position.z);
		transformMat.rotateAffineXYZ(rotation.x, rotation.y, rotation.z);
		transformMat.scale(scale.x, scale.y, scale.z);

		if (parent != null)
			transformMat.mul(parent.getTransformMatrix());

		return transformMat;
	}
}
