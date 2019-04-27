package gameEngine_core;

import java.util.ArrayList;

import gameEngine_Physics.BoxCollider;
import gameEngine_Physics.Collider;
import gameEngine_Physics.RigidBody;
import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.components.Camera;
import gameEngine_core.components.MeshRenderer;
import network.NetworkManager;
import network.SerializableGameObjectData;
import shaders.Shader;

public class Scene extends SceneBase {

	GameObject cardOne;
	GameObject cardTwo;

	GameObject cameraObject;

	MeshRenderer cOneRenderer;
	MeshRenderer cTwoRenderer;

	BoxCollider boxCollider1;
	BoxCollider boxCollider2;

	RigidBody rbody1;
	RigidBody rbody2;

	Shader shader;
	Shader shader2;

	Texture2D texture1;
	Texture2D texture2;

	public Camera renderingCam;

	ArrayList<GameObject> players = new ArrayList<GameObject>();

	public Scene() {

		// ModelWithUV card = ModelWithUV.getCardModelWithUVs();
		ModelWithUV plane = ModelWithUV.getPlaneModelWithUVs();

		shader = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");
		shader2 = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");

		texture1 = new Texture2D("res\\textures\\Card3.jpg");
		texture2 = new Texture2D("res\\textures\\Card4.jpg");

		cOneRenderer = new MeshRenderer(plane, shader, texture1, this);
		cTwoRenderer = new MeshRenderer(plane, shader2, texture2, this);

		boxCollider1 = new BoxCollider(1f, 1f, 1f);// (2.1f, 3.05f, 0.1f);
		boxCollider1.zMoveConstraint = true;

		boxCollider2 = new BoxCollider(1f, 1f, 1f);
		boxCollider2.zMoveConstraint = true;

		rbody1 = new RigidBody();
		rbody2 = new RigidBody();

		renderingCam = new Camera();

		cardOne = new GameObject("Card One");
		cardOne.addComponent(cOneRenderer);
		cardOne.addComponent(boxCollider1);
		cardOne.transform.position.y = -0.5f;
		cardOne.transform.position.z = -2f;
		cardOne.transform.scale.y = 0.25f;
		cardOne.transform.scale.x = 5f;

		// cardOne.SetActive(true);

		cardTwo = new GameObject("Card Two");
		cardTwo.addComponent(cTwoRenderer);
		cardTwo.addComponent(boxCollider2);
		cardTwo.addComponent(rbody2);
		cardTwo.transform.scale.x = cardTwo.transform.scale.y = 0.25f;
		cardTwo.transform.position.z = -2f;
		cardTwo.transform.position.x = -0f;
		cardTwo.transform.position.y = 1.01f;

		boxCollider2.SetActive(false);
		cardTwo.SetActive(false);

		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(renderingCam);

		GameObject copy = GameObject.Instantiate(cardTwo);
		copy.SetActive(true);
		copy.getRigidBody().SetActive(false);
		players.add(copy);

	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void update() {

		while (players.size() < NetworkManager.GetInstance().clientsData.size()) {
			GameObject copy = GameObject.Instantiate(cardTwo);
			copy.SetActive(true);
			copy.getRigidBody().SetActive(false);
			//copy.transform.position.y = 1.01f;
			players.add(copy);
		}

		for (int i = 0; i < NetworkManager.GetInstance().clientsData.size(); i++) {

			if (NetworkManager.GetInstance().clientTag == i) {

				RigidBody playerRB = players.get(i).getRigidBody();

				playerRB.SetActive(true);

				if (Input.getKeyDown(KeyCode.right))
					playerRB.accelaration.x = 5f;

				if (Input.getKeyDown(KeyCode.left))
					playerRB.accelaration.x = -5f;

				if (Input.getKeyPress(KeyCode.space) && playerRB.isColliding())
					playerRB.accelaration.y = 100;

				NetworkManager.GetInstance().SetPlayerPos(i, players.get(i).transform.position.x,
						players.get(i).transform.position.y);

				NetworkManager.GetInstance().clientsData.get(i).clientNumber = i;
				NetworkManager.GetInstance().clientsData.get(i).xPos = players.get(i).transform.position.x;
				NetworkManager.GetInstance().clientsData.get(i).yPos = players.get(i).transform.position.y;

				continue;
			}

			players.get(i).getRigidBody().SetActive(false);

			SerializableGameObjectData client = NetworkManager.GetInstance().clientsData.get(i);

			players.get(client.clientNumber).transform.position.x = client.xPos;
			players.get(client.clientNumber).transform.position.y = client.yPos;
		}

		Collider.CheckCollions();

		super.update();
	}
}
