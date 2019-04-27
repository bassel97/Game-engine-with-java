package gameEngine_core;

import java.util.ArrayList;

import gameEngine_Physics.BoxCollider;
import gameEngine_Physics.Collider;
import gameEngine_Physics.RigidBody;
import gameEngine_Utils.ModelWithUV;
import gameEngine_Utils.Texture2D;
import gameEngine_core.components.Camera;
import gameEngine_core.components.MeshRenderer;
import main.GUI_WindowController;
import main.GameWindowController;
import network.NetworkManager;
import network.SerializableGameObjectData;
import shaders.Shader;

public class Scene extends SceneBase {

	GameObject block;
	BoxCollider blockBoxCollider;
	Shader blockShader;
	Texture2D blockTexture;
	MeshRenderer blockMeshRenderer;

	GameObject end;
	Shader endShader;
	Texture2D endTexture;
	MeshRenderer endMeshRenderer;

	GameObject cardTwo;
	MeshRenderer cTwoRenderer;
	BoxCollider boxCollider2;
	RigidBody rbody2;
	Shader shader2;
	Texture2D texture2;

	GameObject cameraObject;
	public Camera renderingCam;

	ArrayList<GameObject> players = new ArrayList<GameObject>();

	public Scene() {

		// ModelWithUV card = ModelWithUV.getCardModelWithUVs();
		ModelWithUV plane = ModelWithUV.getPlaneModelWithUVs();

		blockShader = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");
		blockTexture = new Texture2D("res\\textures\\bricks.jpg");
		blockMeshRenderer = new MeshRenderer(plane, blockShader, blockTexture, this);
		blockBoxCollider = new BoxCollider(1f, 1f, 1f);
		blockBoxCollider.zMoveConstraint = true;
		blockBoxCollider.SetActive(false);
		block = new GameObject("Block");
		block.addComponent(blockMeshRenderer);
		block.addComponent(blockBoxCollider);
		block.SetActive(false);

		endShader = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");
		endTexture = new Texture2D("res\\textures\\portal.jpg");
		endMeshRenderer = new MeshRenderer(plane, endShader, endTexture, this);
		end = new GameObject("End");
		end.addComponent(endMeshRenderer);
		end.transform.position.x = 50.0f;
		end.transform.position.y = 2.5f;
		end.transform.position.z = -0.25f;
		end.transform.scale.x = end.transform.scale.y = 2.0f;

		shader2 = new Shader("res\\shaders\\test.vs", "res\\shaders\\test.fs");
		texture2 = new Texture2D("res\\textures\\box.png");
		cTwoRenderer = new MeshRenderer(plane, shader2, texture2, this);
		boxCollider2 = new BoxCollider(1f, 1f, 1f);
		boxCollider2.zMoveConstraint = true;
		boxCollider2.SetActive(false);
		boxCollider2.bounciness = 0.2f;
		rbody2 = new RigidBody();
		boxCollider2.SetActive(false);
		
		
		renderingCam = new Camera();
		cameraObject = new GameObject("Camera");
		cameraObject.addComponent(renderingCam);
		cameraObject.transform.position.z = 10;
		
		CreateBlocks();

		cardTwo = new GameObject("Card Two");
		cardTwo.addComponent(cTwoRenderer);
		cardTwo.addComponent(boxCollider2);
		cardTwo.addComponent(rbody2);
		cardTwo.transform.position.x = -0f;
		cardTwo.transform.position.y = 1.01f;
		cardTwo.SetActive(false);

		GameObject copy = GameObject.Instantiate(cardTwo);
		copy.SetActive(true);
		copy.getRigidBody().SetActive(false);
		players.add(copy);

	}

	@Override
	public void start() {
		super.start();
	}

	public void CreateBlocks() {
		// Blocks
		GameObject block01 = GameObject.Instantiate(block);
		block01.transform.position.y = -4;
		block01.transform.scale.x = 8.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 9;
		block01.transform.position.y = -3.5f;
		block01.transform.scale.x = 8.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 18;
		block01.transform.position.y = -3.0f;
		block01.transform.scale.x = 8.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 24.5f;
		block01.transform.position.y = -2.0f;
		block01.transform.scale.x = 1.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 31f;
		block01.transform.position.y = -1f;
		block01.transform.scale.x = 8.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 37.5f;
		block01.transform.position.y = 0f;
		block01.transform.scale.x = 1.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 40.5f;
		block01.transform.position.y = 0.5f;

		block01 = GameObject.Instantiate(block);
		block01.transform.position.x = 47;
		block01.transform.position.y = 1f;
		block01.transform.scale.x = 8.5f;
	}

	public boolean CheckPlayerWon(Transform player) {
		if (player.position.x > end.transform.position.x - 0.5f && player.position.x < end.transform.position.x + 0.5f) {
			if (player.position.y > end.transform.position.y - 0.5f && player.position.y < end.transform.position.y + 0.5f) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void update() {

		while (players.size() < NetworkManager.GetInstance().clientsData.size()) {
			GameObject copy = GameObject.Instantiate(cardTwo);
			copy.SetActive(true);
			copy.getRigidBody().SetActive(false);
			players.add(copy);
		}

		for (int i = 0; i < NetworkManager.GetInstance().clientsData.size(); i++) {

			if (NetworkManager.GetInstance().clientTag == i) {

				RigidBody playerRB = players.get(i).getRigidBody();

				playerRB.SetActive(true);

				if (Input.getKeyDown(KeyCode.right))
					playerRB.accelaration.x = 10f;

				if (Input.getKeyDown(KeyCode.left))
					playerRB.accelaration.x = -10f;

				if (Input.getKeyPress(KeyCode.space) && playerRB.isColliding())
					playerRB.accelaration.y = 500;

				NetworkManager.GetInstance().SetPlayerPos(i, players.get(i).transform.position.x,
						players.get(i).transform.position.y);

				NetworkManager.GetInstance().clientsData.get(i).clientNumber = i;
				NetworkManager.GetInstance().clientsData.get(i).xPos = players.get(i).transform.position.x;
				NetworkManager.GetInstance().clientsData.get(i).yPos = players.get(i).transform.position.y;

				cameraObject.transform.position.x = playerRB.gameObject.transform.position.x;
				cameraObject.transform.position.y = playerRB.gameObject.transform.position.y;
				
				if(CheckPlayerWon(playerRB.gameObject.transform)) {
					System.out.println("YOU WON");
					GUI_WindowController.guiWindowController.gameState = "YOU WON";
					GameWindowController.gameWindowController.StopGame();
				}
				
				if(playerRB.gameObject.transform.position.y < -5) {
					System.out.println("YOU LOST");
					GUI_WindowController.guiWindowController.gameState = "YOU LOST";
					GameWindowController.gameWindowController.StopGame();
				}

				continue;
			}

			players.get(i).getRigidBody().SetActive(false);

			SerializableGameObjectData client = NetworkManager.GetInstance().clientsData.get(i);

			players.get(client.clientNumber).transform.position.x = client.xPos;
			players.get(client.clientNumber).transform.position.y = client.yPos;
			
			if(CheckPlayerWon(players.get(i).transform)) {
				System.out.println("YOU Lost");
				GUI_WindowController.guiWindowController.gameState = "YOU Lost";
				GameWindowController.gameWindowController.StopGame();
			}
			
			if(players.get(i).transform.position.y < -5) {
				System.out.println("HE LOST SO ... YOU WON");
				GUI_WindowController.guiWindowController.gameState = "HE LOST SO ... YOU WON";
				GameWindowController.gameWindowController.StopGame();
			}
		}

		Collider.CheckCollions();

		super.update();
	}
}
