package game;

import java.awt.Color;
import pqtUtilidades.StdDraw;

public class Circle {
	private Point center;
	private double radius;

	public Circle(Point center, double radius) {
		super();
		setCenter(center);
		setRadius(radius);
	}

	public Circle(double x, double y, double radius) {
		super();
		center = new Point(x, y);
		setRadius(radius);
	}

	public Circle() { 
		super();
		center = new Point(0, 0);
		setRadius(10);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point centro) {
		if (centro == null) {
			throw new IllegalArgumentException("The center can't be null");
		}
		this.center = centro;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radio) {
		if (radio <= 0)
			throw new IllegalArgumentException("The radius must be greater than zero");
		this.radius = radio;
	}

	public void setColor(Color color) {
		center.setColor(color);
	}

	public void drawCircle() {
		StdDraw.setPenColor(center.getColor());
		StdDraw.filledCircle(center.getPositionX(), center.getPositionY(), radius);
	}
}
