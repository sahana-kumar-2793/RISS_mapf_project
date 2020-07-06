
public class Agent extends Obstacle{
	private int startX, startY;
	private int currX, currY;
	private int goalX, goalY;
	
	public Agent(int startX, int startY, int goalX, int goalY, String id) {
		super(startX, startY, id);
		currX = startX;
		currY = startY;
		this.goalX = goalX;
		this.goalY = goalY;
	}
	
	public void move(int xMove, int yMove) {
		currX += xMove;
		currY += yMove;
	}
	
	public void getStartX() {
		super.getX();
	}
	
	public void getStartY() {
		super.getY();
	}
	
	public int getX() {
		return currX;
	}
	
	public int getY() {
		return currY;
	}
	
	public int getGoalX() {
		return goalX;
	}
	
	public int getGoalY() {
		return goalY;
	}
	
	public String getType() {
		return "Agent";
	}
	
	public boolean isOnTarget() {
		return goalX == currX && goalY == currY;
	}
}
