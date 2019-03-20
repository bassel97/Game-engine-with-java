package gameEngine_core;

import gameEngine_Utils.Model;
import gameEngine_core.components.MeshRenderer;

public class Scene extends SceneBase {

	GameObject cardOne;
	GameObject cardTwo;

	MeshRenderer cOneRenderer;

	public Scene() {

		Model card = Model.getPlaneModel();

		cOneRenderer = new MeshRenderer(card);

		cardOne = new GameObject();
		cardOne.name = "Card One";
		cardOne.addComponent(cOneRenderer);

	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {
		super.update();
	}

}
