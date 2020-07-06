
public class Obstacle {
	private int xPos, yPos;
	private int size;
	private String id;
	
	public Obstacle(int xPos, int yPos, String id) {
		this.xPos = xPos;
		this.yPos = yPos;
		size = 10;
		this.id = id;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getId() {
		return id;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getType() {
		return("Obstacle");
	}

}
