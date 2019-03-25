package gameEngine_core.components;

import org.lwjgl.opengl.GL30;

import gameEngine_Utils.Model;
import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.Component;
import gameEngine_core.Scene;
import shaders.Shader;

public class MeshRenderer extends Component {

	Model model;

	public Shader shader;

	Texture2D texture;
	boolean hasTexture;

	Scene currentScene;

	public MeshRenderer(Model model, Shader shader, Scene currentScene) {
		super();

		this.currentScene = currentScene;

		this.model = model;

		this.shader = shader;

		shader.loadToGPU();
	}

	public MeshRenderer(ModelWithUV model, Shader shader, Texture2D texture, Scene currentScene) {
		super();

		this.currentScene = currentScene;

		this.model = model;

		this.shader = shader;

		this.texture = texture;

		this.hasTexture = true;

		shader.loadToGPU();
	}

	@Override
	public void start() {

	}

	@Override
	public void update() {

		shader.use();

		shader.setUniformMat4("transformationMatrix", gameObject.getTransformationMatrix());
		shader.setUniformMat4("projectionMatrix", currentScene.renderingCam.getProjectionMatrix());
		shader.setUniformMat4("viewMatrix", currentScene.renderingCam.getViewMatrix());

		GL30.glBindVertexArray(model.getVertexArrayID());

		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);

		if (hasTexture) {
			GL30.glActiveTexture(GL30.GL_TEXTURE0);
			texture.bind();
		}

		GL30.glDrawElements(GL30.GL_TRIANGLES, model.getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
		// GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, model.getVertexCount());

		GL30.glDisableVertexAttribArray(1);
		GL30.glDisableVertexAttribArray(0);

		GL30.glBindVertexArray(0);
	}

	@Override
	public void end() {
		model.removeFromGPU();

		shader.removeFromGPU();
	}

}
