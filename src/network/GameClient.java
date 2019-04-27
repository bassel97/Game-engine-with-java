package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient extends Thread {

	public Socket socket;

	public GameClient(Socket socket) {

		this.socket = socket;
	}

	public static int tagNumber;

	public void run() {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("Reading Tag");

			tagNumber = Integer.parseInt(in.readLine());
			System.out.println("Tag Number = " + tagNumber);

			NetworkManager.GetInstance().clientTag = tagNumber;

			while (!Thread.interrupted()) {

				if (NetworkManager.GetInstance().clientTag >= NetworkManager.GetInstance().clientsData.size()) {
					NetworkManager.GetInstance().clientsData.add(new SerializableGameObjectData());
				}

				out.println(NetworkManager.GetInstance().clientsData.get(NetworkManager.GetInstance().clientTag));

				String response = in.readLine();

				String[] responseParts = response.split(" ");

				NetworkManager.GetInstance().clientsData.get(Integer.parseInt(responseParts[0]))
						.SetWithString(responseParts);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
