package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.GUI_WindowController;
import main.GameWindowController;

public class GameServer extends Thread {
	
	private Socket socket;
    private int clientNumber;

    public GameServer(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client# " + clientNumber + " at " + socket);
        
        NetworkManager.GetInstance().clientsData.add(new SerializableGameObjectData());
    }
    
    public void run() {
        try {
        	
        	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    
                        
            out.println(clientNumber);

            System.out.println("Sent " + clientNumber);
            
            while (true) {
            	
            	String response = in.readLine();
            	
            	String[] responseParts = response.split(" ");
            	
            	NetworkManager.GetInstance().clientsData.get(Integer.parseInt(responseParts[0])).SetWithString(responseParts);
            	
            	out.println(NetworkManager.GetInstance().clientsData.get(NetworkManager.GetInstance().clientTag));
            	
            }
        } catch (Exception e) {
            System.out.println("Error handling client# " + clientNumber + ": " + e);
            //e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Couldn't close the socket");
                e.printStackTrace();
            }
            System.out.println("Connection with client# " + clientNumber + " closed");
            
            System.out.println("HE QUIT SO ... YOU WON");
			GUI_WindowController.guiWindowController.gameState = "HE QUIT SO ... YOU WON";
			GameWindowController.gameWindowController.StopGame();
        }
    }

}
