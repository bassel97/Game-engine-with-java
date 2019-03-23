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
	
	
	public Scene() {

		ModelWithUV card = ModelWithUV.getPlaneModelWithUVs();

		shader = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");

		shader2 = new Shader("src\\shaders\\test.vs", "src\\shaders\\test.fs");
		
		texture1 = new Texture2D("res\\textures\\Card1.jpg");
		texture2 = new Texture2D("res\\textures\\Card2.jpg");
		
		cOneRenderer = new MeshRenderer(card, shader, texture1);
		cTwoRenderer = new MeshRenderer(card, shader2, texture2);

		cardOne = new GameObject();
		cardOne.name = "Card One";
		cardOne.addComponent(cOneRenderer);
		
		cardTwo = new GameObject();
		cardTwo.name = "Card Two";
		cardTwo.addComponent(cTwoRenderer);

	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {

		cOneRenderer.shader.use();
		cOneRenderer.shader.setUniformFloat("xOffset", 0.5f);
		

		cTwoRenderer.shader.use();
		cTwoRenderer.shader.setUniformFloat("xOffset", -0.5f);
		
		super.update();
	}

}
