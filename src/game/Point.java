package game;

import java.awt.Color;

import pqtUtilidades.StdDraw;

public class Point {  
	private double positionX;  
	private double positionY;  
	private double movementX;  
	private double movementY;  
	Color color;  
	
	public Point(double positionX, double positionY) { 
		this(positionX, positionY, Color.BLACK);
	}
	
	public Point(double positionX, double positionY, Color color) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
	}

	public double getPositionX() {
		return positionX;
	}

	public Point setPositionX(double positionX) {
		this.positionX = positionX;
		return this;
	}

	public double getPositionY() {
		return positionY;
	}

	public Point setPositionY(double positionY) {
		this.positionY = positionY;
		return this;
	}

	public double getMovementX() {
		return movementX;
	}

	public void setMovementX(double movementX) {
		this.movementX = movementX;
	}

	public double getMovementY() {
		return movementY;
	}

	public void setMovementY(double movementY) {
		this.movementY = movementY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
		
	public void dibujarPunto() { 
		StdDraw.setPenColor(color); 
		StdDraw.setPenRadius(0.03); 
		StdDraw.point(positionX, positionY); 
	}
}
