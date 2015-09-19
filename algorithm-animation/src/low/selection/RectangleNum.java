package low.selection;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectangleNum {
	private int x;
	private int y;
	private int value;
	private Color color;
	
	public RectangleNum(){
		
	}
	
	public RectangleNum(int x, int y, int value, Color color){
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
	}
	
	public void draw(Graphics2D g2, String name){
		int clientX = 30 + x*30;
		int clientY = 20 + y*30;
		Rectangle2D.Double rect = new Rectangle2D.Double(clientX, clientY, 20, value*20);
		g2.setPaint(color);
		if(name == null)
			g2.fill(rect);
		g2.setPaint(Color.BLACK);
		//g2.draw(rect);
		if(name == null)
			g2.drawString(String.valueOf(value), clientX, clientY - 10);
		else 
			g2.drawString(name + value, clientX, clientY - 10);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
