package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient extends Thread {

	public Socket socket;

	public GameClient(Socket socket) {

		this.socket = socket;

		clientData = new SerializableObjectData();
	}

	public static int tagNumber;

	public SerializableObjectData clientData;

	public void run() {

		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			tagNumber = Integer.parseInt(in.readLine());
			System.out.println(tagNumber);

			String message = "";
			while (!Thread.interrupted()) {
				message = tagNumber + " " + clientData.xPos + " " + clientData.yPos;

				out.println(message); // send message to server

				String response = in.readLine();

				String[] responseParts = response.split(" ");
				NetworkManager.GetInstance().clientsData[Integer.parseInt(responseParts[0])].xPos = Float
						.parseFloat(responseParts[1]);
				NetworkManager.GetInstance().clientsData[Integer.parseInt(responseParts[0])].yPos = Float
						.parseFloat(responseParts[2]);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
