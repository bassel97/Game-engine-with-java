package gameEngine_core;

import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.components.Camera;
import gameEngine_core.components.MeshRenderer;
import shaders.Shader;

public class Scene extends SceneBase {

	GameObject cardOne;
	GameObject cardTwo;
	GameObject cameraObject;

	MeshRenderer cOneRenderer;
	MeshRenderer cTwoRenderer;

	Shader shader;
	Shader shader2;

	Texture2D texture1;
	Texture2D texture2;

	public Camera renderingCam;

	// TODO remove this crime into time class
	float timeCounter;

	public Scene() {

		ModelWithUV card = ModelWithUV.getCardModelWithUVs();

		shader = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");
		shader2 = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");

		texture1 = new Texture2D("res\\textures\\Card3.jpg");
		texture2 = new Texture2D("res\\textures\\Card4.jpg");

		cOneRenderer = new MeshRenderer(card, shader, texture1, this);

		cTwoRenderer = new MeshRenderer(card, shader2, texture2, this);

		renderingCam = new Camera();

		cardOne = new GameObject("Card One");
		cardOne.addComponent(cOneRenderer);
		cardOne.transform.position.y = -0.25f;
		cardOne.transform.position.z = -2f;
		cardOne.transform.scale.x = cardOne.transform.scale.y = 0.25f;

		cardTwo = new GameObject("Card Two");
		cardTwo.addComponent(cTwoRenderer);
		cardTwo.transform.scale.x = cardTwo.transform.scale.y = 0.25f;
		cardTwo.transform.position.z = -2f;

		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(renderingCam);

	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {

		timeCounter += 0.01f;

		cardOne.transform.position.z = (float) (Math.sin(timeCounter * 5)) - 2;

		//renderingCam.gameObject.transform.rotation.z = (float) (Math.sin(timeCounter * 5));
		renderingCam.gameObject.transform.position.x = (float) (Math.sin(timeCounter * 15) * 0.015f);
		renderingCam.gameObject.transform.position.y = (float) (Math.cos(timeCounter * 15) * 0.025f);

		//cardOne.transform.rotation.z = timeCounter;
		//cardTwo.transform.rotation.y = timeCounter;//(float) (Math.sin(timeCounter));

		super.update();
	}

}
