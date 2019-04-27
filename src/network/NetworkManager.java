package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkManager extends Thread {

	static NetworkManager networkManager;

	public static NetworkManager GetInstance() {
		if (networkManager == null)
			networkManager = new NetworkManager();

		return networkManager;
	}

	private NetworkManager() {
		
		clientsData.add(new SerializableGameObjectData());

	}

	Socket clientSocket;

	ServerSocket serverSocketListener;

	public boolean isServer = true;

	boolean closeServerThread = false;

	GameClient gameClinet;
	GameServer gameServer;

	private int clientNumber = 1;
	
	public int clientTag = 0;

	public ArrayList<SerializableGameObjectData> clientsData = new ArrayList<SerializableGameObjectData>();

	public void StartServer() {

		try {

			System.out.println("The Game server is running.");

			serverSocketListener = new ServerSocket(9898);
			try {
				while (!Thread.interrupted()) {
					gameServer = new GameServer(serverSocketListener.accept(), clientNumber++);

					gameServer.start();
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
		
		try {
			clientSocket = new Socket("localhost", 9898);
			
			NetworkManager.GetInstance().clientsData.add(new SerializableGameObjectData());

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

	public void SetPlayerPos(int id, float x, float y) {
		clientsData.get(id).xPos = x;
		clientsData.get(id).yPos = y;
	}

}
