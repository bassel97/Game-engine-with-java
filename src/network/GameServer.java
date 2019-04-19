package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameServer extends Thread {
	
	private Socket socket;
    private int clientNumber;

    public GameServer(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client# " + clientNumber + " at " + socket);
    }

    public SerializableObjectData clientData  = new SerializableObjectData();
    
    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(clientNumber);

            while (true) {

            	String response = in.readLine();
            	
            	String[] responseParts = response.split(" ");
				NetworkManager.GetInstance().clientsData[Integer.parseInt(responseParts[0])].xPos = Float.parseFloat(responseParts[1]);
				NetworkManager.GetInstance().clientsData[Integer.parseInt(responseParts[0])].yPos = Float.parseFloat(responseParts[2]);
            	
            	
            	String myPos = "0 " + clientData.xPos + " " + clientData.yPos;
            	out.println(myPos);
            }
        } catch (Exception e) {
            System.out.println("Error handling client# " + clientNumber + ": " + e);
            //e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Couldn't close a socket, what's going on?");
                e.printStackTrace();
            }
            System.out.println("Connection with client# " + clientNumber + " closed");
        }
    }

}
