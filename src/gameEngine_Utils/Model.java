package gameEngine_Utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public class Model {

	int vertexArrayID, vertexBufferId, indicesBufferId, vertexCount;

	float[] vertices;
	int[] indices;

	public Model() {

	}

	public Model(float[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;

		vertexCount = indices.length;

		loadToGPU();
	}

	public void loadToGPU() {
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices);
		vertexBuffer.flip();

		IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();

		vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayID);

		vertexBufferId = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexBufferId);
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexBuffer, GL30.GL_STATIC_DRAW);

		indicesBufferId = GL30.glGenBuffers();
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL30.GL_STATIC_DRAW);

		GL30.glEnableVertexAttribArray(0);
		GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 0, 0);
		GL30.glBindVertexArray(0);
		GL30.glDisableVertexAttribArray(0);

	}

	public void removeFromGPU() {
		GL30.glDeleteVertexArrays(vertexArrayID);

		GL30.glDeleteBuffers(vertexBufferId);
		GL30.glDeleteBuffers(indicesBufferId);
	}

	public int getVertexArrayID() {
		return vertexArrayID;
	}

	public int getVertexBufferId() {
		return vertexBufferId;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public static Model getPlaneModel() {
		Model m = new Model(new float[] { -0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f },

				new int[] { 0, 1, 2, 1, 2, 3 }

		);

		return m;
	}

	// TODO correct
	public static Model getSphereModel(int res) {

		float[] floatArr = new float[res * 3 * 3];

		for (int i = 0; i < res; i++) {
			for (int j = 0; j < 3; j++) {
				floatArr[i * 9] = 0;
				floatArr[i * 9 + 1] = 0;
				floatArr[i * 9 + 2] = 0;
			}

			floatArr[i * 9 + 3] = (float) Math.sin(i * (2 * Math.PI / (float) res));
			floatArr[i * 9 + 4] = (float) Math.cos(i * (2 * Math.PI / (float) res));
			floatArr[i * 9 + 5] = 0;

			for (int j = 0; j < 3; j++) {
				floatArr[i * 9 + 6 + j] = 0;
				floatArr[i * 9 + 6 + j] = 0;
				floatArr[i * 9 + 6 + j] = 0;
			}
		}

		Model m = new Model(floatArr, new int[] {

		});

		return m;
	}

}
