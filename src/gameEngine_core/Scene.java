package gameEngine_core;

import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.components.MeshRenderer;
import shaders.Shader;

public class Scene extends SceneBase {

	GameObject cardOne;
	GameObject cardTwo;

	MeshRenderer cOneRenderer;
	MeshRenderer cTwoRenderer;

	Shader shader;
	Shader shader2;

	Texture2D texture1;
	Texture2D texture2;

	//TODO remove this crime into time class
	float timeCounter;
	
	public Scene() {

		ModelWithUV card = ModelWithUV.getCardModelWithUVs();

		shader = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");

		shader2 = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");

		texture1 = new Texture2D("res\\textures\\Card3.jpg");
		texture2 = new Texture2D("res\\textures\\Card4.jpg");

		cOneRenderer = new MeshRenderer(card, shader, texture1);
		cTwoRenderer = new MeshRenderer(card, shader2, texture2);

		cardOne = new GameObject();
		cardOne.name = "Card One";
		cardOne.addComponent(cOneRenderer);
		cardOne.transform.position.y = -0.7f;
		cardOne.transform.scale.x = cardOne.transform.scale.y = 0.2f;

		cardTwo = new GameObject();
		cardTwo.name = "Card Two";
		cardTwo.addComponent(cTwoRenderer);
		cardTwo.transform.scale.x = cardTwo.transform.scale.y = 0.2f;
		
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {
		
		timeCounter += 0.01f;

		cardOne.transform.position.x = (float) (Math.sin(timeCounter));

		super.update();
	}

}
