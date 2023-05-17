package game;

import pqtUtilidades.StdDraw;

public class Rectangle {
	private Point topLeft;
	private Point bottomRight;

	public Rectangle(Point topLeft, Point bottomRight) {
		if (bottomRight == null) {
			throw new IllegalArgumentException("Can't be null");
		}
		this.bottomRight = bottomRight;
		setTopLeft(topLeft);
		setBottomRight(bottomRight);
	}

	public Rectangle(double topLeftX, double topLeftY, double bottonRightX, double bottomRightY) {
		this(new Point(topLeftX, topLeftY), new Point(bottonRightX, bottomRightY));
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		if (topLeft == null) {
			throw new IllegalArgumentException("Can't be null");
		}
		if (topLeft.getPositionX() >= bottomRight.getPositionX()
				|| topLeft.getPositionY() <= bottomRight.getPositionY()) {
			throw new IllegalArgumentException("Points of the rectangle are incorrect");
		}
		this.topLeft = topLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Point bottomRight) {
		if (bottomRight == null) {
			throw new IllegalArgumentException("Can't be null");
		}
		if (topLeft.getPositionX() >= bottomRight.getPositionX()
				|| topLeft.getPositionY() <= bottomRight.getPositionY()) {
			throw new IllegalArgumentException("Points of the rectangle are incorrect");
		}
		this.bottomRight = bottomRight;
	}

	public Point halfHeightLeft() {
		return new Point(topLeft.getPositionX(), topLeft.getPositionY() - height() / 2);
	}

	public Point bottomLeft() {
		return new Point(topLeft.getPositionX(), bottomRight.getPositionY());
	}

	public Point bottomHalfWidth() {
		return new Point(bottomRight.getPositionX() - width() / 2, bottomRight.getPositionY());
	}

	public Point halfHeightRight() {
		return new Point(bottomRight.getPositionX(), bottomRight.getPositionY() + height() / 2);
	}

	public Point topRight() {
		return new Point(bottomRight.getPositionX(), topLeft.getPositionY());
	}

	public Point topHalfWidth() {
		return new Point(topLeft.getPositionX() + width() / 2, topLeft.getPositionY());
	}

	public Point center() {
		return new Point(topLeft.getPositionX() + width() / 2, bottomRight.getPositionY() + height() / 2);
	}

	public double width() {
		return bottomRight.getPositionX() - topLeft.getPositionX();
	}

	public double height() {
		return topLeft.getPositionY() - bottomRight.getPositionY();
	}

	public double area() {
		return width() * height(); 
	}

	public void drawRectangle() {
		drawRectangle(true);
	}

	public void drawRectangle(boolean relleno) {
		StdDraw.setPenColor(topLeft.getColor());
		if (relleno) {
			StdDraw.filledRectangle(center().getPositionX(), center().getPositionY(), width() / 2, height() / 2);
		} else {
			StdDraw.setPenRadius(0.005);
			StdDraw.rectangle(center().getPositionX(), center().getPositionY(), width() / 2, height() / 2);
		}
	}
}
