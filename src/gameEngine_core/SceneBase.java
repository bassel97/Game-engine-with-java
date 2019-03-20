package gameEngine_core;

public abstract class SceneBase {

	public void start() {
		for (GameObject gameObject : GameObject.gameObjects) {
			gameObject.start();
		}
	}

	public void update() {
		for (GameObject gameObject : GameObject.gameObjects) {
			gameObject.update();
		}
	}

}
