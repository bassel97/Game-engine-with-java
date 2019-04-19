package gameEngine_core;

import gameEngine_Physics.BoxCollider;
import gameEngine_Physics.Collider;
import gameEngine_Physics.RigidBody;
import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.components.Camera;
import gameEngine_core.components.MeshRenderer;
import network.NetworkManager;
import shaders.Shader;

public class Scene extends SceneBase {

	GameObject cardOne;
	GameObject cardTwo;
	GameObject cardThree;

	GameObject cameraObject;

	MeshRenderer cOneRenderer;
	MeshRenderer cTwoRenderer;
	MeshRenderer cThreeRenderer;

	BoxCollider boxCollider1;
	BoxCollider boxCollider2;
	BoxCollider boxCollider3;

	RigidBody rbody1;
	RigidBody rbody2;
	RigidBody rbody3;

	Shader shader;
	Shader shader2;

	Texture2D texture1;
	Texture2D texture2;

	public Camera renderingCam;

	public Scene() {

		ModelWithUV card = ModelWithUV.getCardModelWithUVs();

		shader = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");
		shader2 = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");

		texture1 = new Texture2D("res\\textures\\Card3.jpg");
		texture2 = new Texture2D("res\\textures\\Card4.jpg");

		cOneRenderer = new MeshRenderer(card, shader, texture1, this);
		cTwoRenderer = new MeshRenderer(card, shader2, texture2, this);
		cThreeRenderer = new MeshRenderer(card, shader, texture1, this);

		boxCollider1 = new BoxCollider(2.1f, 3.05f, 0.1f);
		boxCollider1.zMoveConstraint = true;

		boxCollider2 = new BoxCollider(2.1f, 3.05f, 0.1f);
		boxCollider2.zMoveConstraint = true;

		boxCollider3 = new BoxCollider(2.1f, 3.05f, 0.1f);
		boxCollider3.zMoveConstraint = true;

		rbody1 = new RigidBody();
		rbody2 = new RigidBody();
		rbody3 = new RigidBody();

		renderingCam = new Camera();

		cardOne = new GameObject("Card One");
		cardOne.addComponent(cOneRenderer);
		cardOne.addComponent(boxCollider1);
		cardOne.transform.position.y = -1f;
		cardOne.transform.position.z = -2f;
		cardOne.transform.scale.y = 0.25f;
		cardOne.transform.scale.x = 5f;

		cardTwo = new GameObject("Card Two");
		cardTwo.addComponent(cTwoRenderer);
		cardTwo.addComponent(boxCollider2);
		cardTwo.addComponent(rbody2);
		cardTwo.transform.scale.x = cardTwo.transform.scale.y = 0.25f;
		cardTwo.transform.position.z = -2f;
		cardTwo.transform.position.x = -0f;
		cardTwo.transform.position.y = 1.01f;

		cardThree = new GameObject("Card Three");
		cardThree.addComponent(cThreeRenderer);
		cardThree.addComponent(boxCollider3);
		cardThree.transform.position.x = -1f;
		cardThree.transform.position.z = -2f;
		cardThree.transform.scale.y = 0.25f;
		cardThree.transform.scale.x = 0.25f;
		cardThree.addComponent(rbody3);

		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(renderingCam);
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {

		// Here is the level logic

		if (NetworkManager.GetInstance().isServer) {
			
			rbody3.SetActive(false);
			
			if (Input.getKeyDown(KeyCode.right))
				rbody2.accelaration.x = 5f;

			if (Input.getKeyDown(KeyCode.left))
				rbody2.accelaration.x = -5f;
			
			if (Input.getKeyPress(KeyCode.space) && rbody2.isColliding())
				rbody2.accelaration.y = 100;
			
			renderingCam.gameObject.transform.position.x = cardTwo.transform.position.x;
			renderingCam.gameObject.transform.position.y = cardTwo.transform.position.y;
			
			NetworkManager.GetInstance().SetPlayerPos(cardTwo.transform.position.x, cardTwo.transform.position.y);
			
			cardThree.transform.position.x = NetworkManager.GetInstance().clientsData[1].xPos;
			cardThree.transform.position.y = NetworkManager.GetInstance().clientsData[1].yPos;
			
		}else {
			
			rbody2.SetActive(false);
			
			if (Input.getKeyDown(KeyCode.right))
				rbody3.accelaration.x = 5f;

			if (Input.getKeyDown(KeyCode.left))
				rbody3.accelaration.x = -5f;
			
			if (Input.getKeyPress(KeyCode.space) && rbody3.isColliding())
				rbody3.accelaration.y = 100;
			
			renderingCam.gameObject.transform.position.x = cardThree.transform.position.x;
			renderingCam.gameObject.transform.position.y = cardThree.transform.position.y;
			
			NetworkManager.GetInstance().SetPlayerPos(cardThree.transform.position.x, cardThree.transform.position.y);
			
			cardTwo.transform.position.x = NetworkManager.GetInstance().clientsData[0].xPos;
			cardTwo.transform.position.y = NetworkManager.GetInstance().clientsData[0].yPos;
		}

		Collider.CheckCollions();

		super.update();
	}
}
