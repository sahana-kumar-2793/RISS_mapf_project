import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;





public class Environment extends JPanel {
	public final int X_DIMENSION = 2000;
	public final int Y_DIMENSION = 2000;
	public final int radius = 10;
	private int xSize, ySize;
	private ArrayList<Obstacle> environmentVariables;
	private Agent[] agents;
	private Obstacle[] obstacles;
	
	public Environment(int xSize, int ySize, ArrayList<Obstacle> environmentVariables) {
		this.xSize = xSize;
		this.ySize = ySize;
		
		this.environmentVariables = environmentVariables;
		ArrayList<Agent> agentList = new ArrayList<Agent>();
		ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
		
		//Differentiating agents and obstacles
		for (Obstacle environmentVariable : environmentVariables) {
			if(environmentVariable.getType() == "Agent") {
				agentList.add((Agent) environmentVariable);
			} else {
				obstacleList.add(environmentVariable);
			}
		}
		
		//initializing agent and obstacle positions
		agents = new Agent[agentList.size()];
		obstacles = new Obstacle[obstacleList.size()];
		
		for(int i = 0; i < agentList.size(); i++) {
			agents[i] = agentList.get(i);
		}
		
		for(int i = 0; i < obstacleList.size(); i++) {
			obstacles[i] = obstacleList.get(i);
		}
	}
	
	public int getX() {
		return xSize;
	}
	
	public int getY() {
		return ySize;
	}
	
	public String moveAgent(String id, int xMovement, int yMovement) {
		Agent activeAgent = null;
		for(Agent agent : agents) {
			if (agent.getId() == id) {
				activeAgent = agent;
				break;
			}
		}
		
		if(activeAgent == null) {
			return "No agent with the given identifier was found.";
		}
		
		int activeX = activeAgent.getX();
		int activeY = activeAgent.getY();
		int desiredX = activeX + xMovement;
		int desiredY = activeY + yMovement;
		
		String collisionId = null;
		for(Obstacle environmentVariable : environmentVariables) {
			if(environmentVariable.getX() == desiredX && environmentVariable.getY() == desiredY) {
				return "Movement terminated due to collision with " + environmentVariable.getId() + ".";
			}
		}
		
		activeAgent.move(xMovement, yMovement);
		
		return "Movement successful.";
	}
	
	public Agent[] getAgents() {
		return agents;
	}
	
	public Obstacle[] getObstacles() {
		return obstacles;
	}
	
	public ArrayList<Dot> setGrid() {
		double xIncrement = X_DIMENSION/(xSize+1);
		double yIncrement = Y_DIMENSION/(ySize+1);
		Dot[][] prelimGrid = new Dot[xSize][ySize];
		ArrayList<Dot> result = new ArrayList<Dot>();
		
		for(Obstacle environmentVariable : environmentVariables) {
			Dot.DOT_TYPE dT = environmentVariable.getType() == "Agent" ? Dot.DOT_TYPE.AGENT : Dot.DOT_TYPE.OBSTACLE;
			prelimGrid[environmentVariable.getX()] [environmentVariable.getY()] = new Dot(dT, (int) (environmentVariable.getX()*xIncrement), (int) (environmentVariable.getY()*yIncrement), radius);
		}
		
		for(int x = 0; x < xSize; x++) {
			for(int y = 0; y < ySize; y++) {
				if(prelimGrid[x][y] != null) {
					prelimGrid[x][y] = new Dot(Dot.DOT_TYPE.CHILLING, (int) (x*xIncrement), (int) (y*yIncrement), radius);
				}
			}
		}
		
		for(Dot[] arr : prelimGrid) {
			for(Dot dot : arr) {
				result.add(dot);
			}
		}
		
		return result;
	}
	
//	public void paint(Graphics g) {
//	     Graphics2D ga = (Graphics2D)g;
//	     ga.setPaint(Color.black);
//	     
//	     double xIncrement = X_DIMENSION/(xSize + 1);
//	     double yIncrement = X_DIMENSION/(ySize+1);
//	     
//	     for(int x = 1; x <= xSize; x++) {
//	    	 for (int y = 1; y <= ySize; y++) {
//	    		 ga.drawOval((int) (xIncrement*x), (int) (yIncrement*y), radius, radius);
//	    	 }
//	     }
//	     
//	     ga.setPaint(Color.blue);
//	     
//	     for(Agent agent : agents) {
//	    	 ga.drawOval(agent.getX(), agent.getY(), radius, radius);
//	     }
//	     
//	     ga.setPaint(Color.red);
//	     
//	     for(Obstacle obstacle : obstacles) {
//	    	 ga.drawOval(obstacle.getX(), obstacle.getY(), radius, radius);
//	     }
//	     
//	
//	  }
	
	
	protected void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    
	    ArrayList<Dot> dotGrid = this.setGrid();

	    for (Dot dot : dotGrid)
	        dot.paint(g);
	}
	
	
	

	
	
	

}
