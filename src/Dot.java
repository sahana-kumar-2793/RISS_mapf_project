import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;


public class Dot {
	
	public enum DOT_TYPE {
		AGENT,
		OBSTACLE,
		CHILLING
	}
	
	public DOT_TYPE dotType;
	public int xPos;
	public int yPos;
	public int rad;
	
	public Dot(DOT_TYPE dotType, int xPos, int yPos, int rad) {
		this.dotType = dotType;
		this.xPos = xPos;
		this.yPos = yPos;
		this.rad = rad;
	}
	
	public void paint(Graphics g)
	{
	    Graphics2D ga = (Graphics2D) g;
	    
	    if(dotType == DOT_TYPE.AGENT) {
		     ga.setPaint(Color.blue);
	    } else if(dotType == DOT_TYPE.OBSTACLE) {
		     ga.setPaint(Color.red);

	    } else {
		     ga.setPaint(Color.black);

	    }
	    ga.fillOval(xPos, yPos, rad, rad); //
	}
}


