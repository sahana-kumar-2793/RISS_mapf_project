import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Visualizer extends JFrame {
	private Environment env;
	private int xbuffer = 50;
	private int ybuffer = 50;

	
	public Visualizer(Environment env) {
		super.setTitle("Visualizer");
		super.setSize(env.X_DIMENSION, env.Y_DIMENSION);
		super.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.env = env;
	}
	
	public void paint(Graphics g) {
		double xIncrement = (env.X_DIMENSION - 2 * xbuffer)/(env.getX() + 1);
	    double yIncrement = (env.Y_DIMENSION - 2 * ybuffer)/(env.getY() + 1);
//	    System.out.println(env.getX());
//	    System.out.println("X Increment: " + xIncrement);
//	    System.out.println(yIncrement);
		g.setColor(Color.black);
//		g.fillOval(50, 50, 50, 50);
		int[][] dotGrid = new int[env.getX()][env.getY()];
		Obstacle[][] dotGridVars = new Obstacle[env.getX()][env.getY()];
		
		for (Agent a : env.getAgents()) {
			dotGrid[a.getX() - 1][a.getY() - 1] = 1;
			dotGridVars[a.getX() - 1][a.getY() - 1] = a;
		}
		
		for(Obstacle o : env.getObstacles()) {
			dotGrid[o.getX()][o.getY()] = 2;
			dotGridVars[o.getX() - 1][o.getY() - 1] = o;

		}
		
		for(int x = 1; x <= env.getX(); x++) {
			for (int y = 1; y <= env.getY(); y ++) {
				if(dotGrid[x-1][y-1] == 1) {
					Agent curr = (Agent) dotGridVars[x-1][y-1];
					boolean onTarget = curr.isOnTarget();
					g.setColor(onTarget ? Color.green : Color.orange);
					System.out.println(curr.isOnTarget());
//					if(curr.isOnTarget()) {
//						g.setColor(Color.green);
//					}
					
				} else if (dotGrid[x-1][y-1] == 2){
					g.setColor(Color.red);
					Obstacle curr = dotGridVars[x-1][y-1];
				} else {
					g.setColor(Color.black);
				}
				g.fillOval( (int) (xbuffer + (x*xIncrement)), (int) (ybuffer + (y*yIncrement)), env.radius, env.radius);

			}
		}
		
//		g.setColor(Color.cyan);
//		for(Agent a : env.getAgents()) {
//			System.out.println("Agent x Loc: " + (buffer + (a.getX() * xIncrement)));
//			g.fillOval((int) (buffer + (a.getX()*xIncrement)), (int)(a.getY() * yIncrement), env.radius, env.radius);
//			System.out.println("Agent visualization");
//		}
//		
//		g.setColor(Color.red);
//		for(Obstacle a : env.getObstacles()) {
//			g.fillOval((int) (buffer + (a.getX()*xIncrement)), (int)(a.getY() * yIncrement), a.getSize(), a.getSize());
//		}
	}
	
	public static void main(String[] args) {
		ArrayList<Obstacle> environmentVariables = new ArrayList<Obstacle>();
		
		
		//Determine grid size
		String gridxString = JOptionPane.showInputDialog("Please enter the number of dots you would like on the x axis of your grid");
		String gridyString = JOptionPane.showInputDialog("Please enter the number of dots you would like on the y axis of your grid");	
		int gridx = Integer.parseInt(gridxString);
		int gridy = Integer.parseInt(gridyString);
		
		//Agent Determination
		String numAgentsString = JOptionPane.showInputDialog("How many independent agents would you like to create?");
		int numAgents = Integer.parseInt(numAgentsString);
		for (int i = 0; i < numAgents; i++) {
			String agentId = JOptionPane.showInputDialog("Please assign agent " + (i+1) + " an id.");
			
			String startingXPosAgent = JOptionPane.showInputDialog("Please assign agent " + agentId + " a starting x position (integer).");
			int startXPos = Integer.parseInt(startingXPosAgent);
			String startingYPosAgent = JOptionPane.showInputDialog("Please assign agent " + agentId + " a starting y position (integer).");
			int startYPos = Integer.parseInt(startingYPosAgent);
			
			String goalXPosAgent = JOptionPane.showInputDialog("Please assign agent " + agentId + " a goal x position (integer).");
			int goalXPos = Integer.parseInt(goalXPosAgent);
			String goalYPosAgent = JOptionPane.showInputDialog("Please assign agent " + agentId + " a goal y position (integer).");
			int goalYPos = Integer.parseInt(goalYPosAgent);
			
			Agent a = new Agent(startXPos, startYPos, goalXPos, goalYPos, agentId);
			environmentVariables.add(a);
		}
		
		String numObstaclesString = JOptionPane.showInputDialog("How many static obstacles would you like to create?");
		int numObstacles = Integer.parseInt(numObstaclesString);
		
		for (int i = 0; i < numObstacles; i++) {
			String obstacleId = JOptionPane.showInputDialog("Please assign obstacle " + (i + 1) + " an id.");
			String xPosString = JOptionPane.showInputDialog("Please assign obstacle " + obstacleId + " an (integer) x position.");
			int xPos = Integer.parseInt(xPosString);
			String yPosString = JOptionPane.showInputDialog("Please assign obstacle " + obstacleId + " an (integer) y position.");
			int yPos = Integer.parseInt(yPosString);
			
			Obstacle o = new Obstacle(xPos, yPos, obstacleId);
			environmentVariables.add(o);
		}
//		System.out.println("Grid X: " + gridX);
		
		Environment env = new Environment(gridx, gridy, environmentVariables);
		
		
//		 Visualizer frame = new Visualizer();       
//	      frame.addWindowListener(
//	      new WindowAdapter()
//	      {
//	         public void windowClosing(WindowEvent we)
//	         {
//	            System.exit(0);
//	         }
//	      }
//	      );
//	           
//	      frame.setSize(env.X_DIMENSION, env.Y_DIMENSION);
//	      frame.setVisible(true);
		
//		JFrame jf = new JFrame("Renderer");
//        jf.getContentPane().add(env,BorderLayout.CENTER);
//        jf.setBounds(100,100,400,300);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setVisible(true);
		
		Visualizer v = new Visualizer(env);
		v.paint(null);

		
	}
}
