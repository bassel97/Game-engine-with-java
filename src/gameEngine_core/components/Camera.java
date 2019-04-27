package gameEngine_core.components;

import org.joml.Matrix4f;

import gameEngine_core.Component;

public class Camera extends Component {

	float fieldOfView = 60f;
	float aspectRatio = (float) 16 / (float) 9;
	float near_plane = 0.1f;
	float far_plane = 100f;

	private Matrix4f projMatrix;

	public Matrix4f getProjectionMatrix() {
		return projMatrix;
	}

	public Matrix4f getViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f().identity();

		viewMatrix.rotateAffineXYZ(gameObject.transform.rotation.x, gameObject.transform.rotation.y,
				gameObject.transform.rotation.z);

		viewMatrix.translate(-gameObject.transform.position.x, -gameObject.transform.position.y,
				-gameObject.transform.position.z);

		return viewMatrix;
	}

	private Matrix4f calcualetProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();

		float y_scale = this.coTangent(this.degreesToRadians(fieldOfView / 2f));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = far_plane - near_plane;

		projectionMatrix.m00(x_scale);
		projectionMatrix.m11(y_scale);
		projectionMatrix.m22(-((far_plane + near_plane) / frustum_length));
		projectionMatrix.m23(-1);
		projectionMatrix.m32(-((2 * near_plane * far_plane) / frustum_length));
		projectionMatrix.m33(0);

		return projectionMatrix;
	};

	@Override
	public void start() {
		projMatrix = calcualetProjectionMatrix();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	private float coTangent(float angle) {
		return (float) (1f / Math.tan(angle));
	}

	private float degreesToRadians(float degrees) {
		return degrees * (float) (Math.PI / 180d);
	}

	@Override
	public Component getComponentCopy() {
		Camera copy = new Camera();
		
		return copy;
	}

}
