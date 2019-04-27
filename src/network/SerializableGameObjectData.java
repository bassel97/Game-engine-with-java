package network;

import java.io.Serializable;

public class SerializableGameObjectData implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public int clientNumber;
	
	public float xPos,yPos;
	
	public boolean allocated;

	@Override
	public String toString() {
		return clientNumber + " " + xPos + " " + yPos;
	}
	
	public void SetWithString(String[] values) {
		
		clientNumber = Integer.parseInt(values[0]);
		
		xPos = Float.parseFloat(values[1]);
		yPos = Float.parseFloat(values[2]);
	}
	
}
