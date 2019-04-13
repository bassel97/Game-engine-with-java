package gameEngine_Utils;

import org.lwjgl.opengl.GL30;

public class ModelWithUV extends Model {

	int UVsId;

	float[] UVs;

	public ModelWithUV(float[] vertices, float[] UVs, int[] indices) {

		this.vertices = vertices;
		this.indices = indices;
		this.UVs = UVs;

		vertexCount = indices.length;

		loadToGPU();
	}

	@Override
	public void loadToGPU() {
		vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayID);

		indicesBufferId = bindIndicesBuffer(indices);

		vertexBufferId = storeFloatBuffer(0, 3, vertices);

		UVsId = storeFloatBuffer(1, 2, UVs);

		GL30.glBindVertexArray(0);
	}

	@Override
	public void removeFromGPU() {
		super.removeFromGPU();

		GL30.glDeleteBuffers(UVsId);
	}

	public static ModelWithUV getPlaneModelWithUVs() {
		ModelWithUV m = new ModelWithUV(
				new float[] { -0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f, 0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f },

				new float[] { 0, 0, 1, 0, 1, 1, 0, 1 },

				new int[] { 0, 1, 2, 2, 3, 0 }

		);

		return m;
	}

	public static ModelWithUV getCardModelWithUVs() {
		ModelWithUV m = new ModelWithUV(
				new float[] { -1.05f, 1.525f, 0.0f, 1.05f, 1.525f, 0.0f, -1.05f, -1.52f, 0.0f, 1.05f, -1.525f, 0.0f },

				new float[] { 0, 0, 1, 0, 0, 1, 1, 1 },

				new int[] { 0, 2, 1, 1, 2, 3 }

		);

		return m;
	}

	public static ModelWithUV getBoxModelWithUVs() {
		ModelWithUV m = new ModelWithUV(new float[] { 1.0f, -1.0f, -1.0f, 1.000000f, -1.000000f, 1.0f, -1.000000f,
				-1.000000f, 1.0f, -1.000000f, -1.000000f, -1.0f, 1.000000f, 1.000000f, -0.999999f, 0.999999f, 1.000000f,
				1.000001f, -1.000000f, 1.000000f, 1.000000f, -1.000000f, 1.000000f, -1.000000f, 1.000000f, -1.000000f,
				-1.000000f, 1.000000f, -1.000000f, -1.000000f, 1.000000f, -1.000000f, 1.000000f, 1.000000f, -1.000000f,
				1.000000f, -1.000000f, -1.000000f, -1.000000f, -1.000000f, -1.000000f, -1.000000f, 1.000000f, 1.000000f,
				-0.999999f, 1.000000f, 1.000000f, -0.999999f, -1.000000f, -1.000000f, 1.000000f, -1.000000f, -1.000000f,
				1.000000f, 0.999999f, 1.000000f, 1.000001f, 0.999999f, 1.000000f, 1.000001f, -1.000000f, 1.000000f,
				1.000000f, -1.000000f, 1.000000f, 1.000000f, -1.000000f, 1.000000f, -1.000000f, -1.000000f, 1.000000f,
				-1.000000f },

				new float[] { 0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f,
						0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f,
						0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f,
						0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f,
						0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f,
						0.000000f, 0.000000f, 1.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f, 1.000000f },

				new int[] { 9, 11, 17, 13, 16, 24, 22, 20, 10, 15, 19, 12, 2, 6, 21, 18, 3, 7, 23, 14, 5, 1, 4, 8 }

		);

		return m;
	}

}
