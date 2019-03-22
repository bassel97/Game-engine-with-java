package gameEngine_core.components;

import org.lwjgl.opengl.GL30;

import gameEngine_Utils.Model;
import gameEngine_core.Component;

public class MeshRenderer extends Component {

	Model model;

	public MeshRenderer(Model model) {
		super();

		this.model = model;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		GL30.glBindVertexArray(model.getVertexArrayID());

		GL30.glEnableVertexAttribArray(0);

		GL30.glDrawElements(GL30.GL_TRIANGLES, model.getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
		// GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, model.getVertexCount());

		GL30.glDisableVertexAttribArray(0);

		GL30.glBindVertexArray(0);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		model.removeFromGPU();
	}
	
}
