package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager extends Thread {

	static NetworkManager networkManager;

	public static NetworkManager GetInstance() {
		if (networkManager == null)
			networkManager = new NetworkManager();

		return networkManager;
	}

	private NetworkManager() {

		for (int i = 0; i < clientsData.length; i++) {
			clientsData[i] = new SerializableObjectData();
		}
		
	}

	Socket clientSocket;

	ServerSocket serverSocketListener;

	public boolean isServer = true;

	boolean closeServerThread = false;

	GameClient gameClinet;
	GameServer gameServer;

	int clientNumber = 1;

	public SerializableObjectData[] clientsData = new SerializableObjectData[2];

	public void StartServer() {

		try {

			System.out.println("The Game server is running.");

			serverSocketListener = new ServerSocket(9898);
			try {
				while (!Thread.interrupted()) {
					gameServer = new GameServer(serverSocketListener.accept(), clientNumber++);

					gameServer.start();
					gameServer.join();
				}
			} finally {
				serverSocketListener.close();
				System.out.println("Server Closed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void StartClient() {

		// Connect to server
		try {
			clientSocket = new Socket("localhost", 9898);

			gameClinet = new GameClient(clientSocket);
			gameClinet.start();

			while (!Thread.interrupted()) {
			}
			gameClinet.interrupt();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		System.out.println(Thread.currentThread());

		if (isServer)
			StartServer();
		else
			StartClient();
	}

	public void SetPlayerPos(float x, float y) {
		if (gameClinet != null) {
			gameClinet.clientData.xPos = x;
			gameClinet.clientData.yPos = y;
		}

		if (gameServer != null) {
			gameServer.clientData.xPos = x;
			gameServer.clientData.yPos = y;
		}
	}

}
