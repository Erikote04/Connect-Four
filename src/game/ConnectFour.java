package game;

import java.awt.Color;
import pqtUtilidades.StdDraw;

public class ConnectFour {
	static Circle[][] board; 
	static Rectangle gameZone; 
	static final double CELL_SIZE = 25; 
	static int player = 1; 
	static String warning = null;
	
	private static void drawBoard() {
		gameZone.drawRectangle();
		Circle hole = new Circle(0, 0, CELL_SIZE * 0.8 / 2); 
		hole.setColor(Color.WHITE);
		Circle c = null; 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) { 
				double x = gameZone.getTopLeft().getPositionX() + j * CELL_SIZE + CELL_SIZE / 2;
				double y = gameZone.getTopLeft().getPositionY() - i * CELL_SIZE + CELL_SIZE / 2
						- CELL_SIZE;
				if (board[i][j] == null) { 
					c = hole; 
				} else { 
					c = board[i][j]; 
				}
				c.getCenter().setPositionX(x);
				c.getCenter().setPositionY(y);
				c.drawCircle();
			}
		}
	}

	private static Integer detectMouseColumn() {
		double mouseX = StdDraw.mouseX(); 
		double mouseY = StdDraw.mouseY(); 
		if (mouseX <= gameZone.getTopLeft().getPositionX()
				|| mouseX >= gameZone.getBottomRight().getPositionX()
				|| mouseY <= gameZone.getBottomRight().getPositionY()
				|| mouseY >= gameZone.getTopLeft().getPositionY()) {
			return null; 
		}
		double distanceFromLeftEdge = (mouseX - gameZone.getTopLeft().getPositionX()) / CELL_SIZE; 
		return (int) distanceFromLeftEdge; 
	}

	private static void highlightColumn(Integer mouseColumn) {
		Color highlightColor = null;
		if (player == 1) {
			highlightColor = new Color(255, 255, 0, 70);
		} else {
			highlightColor = new Color(255, 0, 0, 70);
		}
		Point topLeft = new Point(
				gameZone.getTopLeft().getPositionX() + mouseColumn * CELL_SIZE, 
				gameZone.getTopLeft().getPositionY(), highlightColor); 
		Point bottomRight = new Point(
				gameZone.getTopLeft().getPositionX() + mouseColumn * CELL_SIZE + CELL_SIZE, 
				gameZone.getBottomRight().getPositionY()); 
		Rectangle highlightRectangle = new Rectangle(topLeft, bottomRight);
		highlightRectangle.drawRectangle();
	}

	private static void enterChip(int player, int mouseColumn) {
		if (player < 1 || player > 2)
			throw new IllegalArgumentException("Player must be 1 or 2");
		if (mouseColumn < 0 || mouseColumn > 6)
			throw new IllegalArgumentException("Column must be between 0 and 6");
		if (board[0][mouseColumn] != null)
			throw new ExceptionFullColumn("The column " + mouseColumn + " is full");
		Circle chip = new Circle(0, 0, CELL_SIZE * 0.8 / 2);
		if (player == 1)
			chip.setColor(Color.YELLOW);
		else
			chip.setColor(Color.RED);
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][mouseColumn] == null) {
				board[i][mouseColumn] = chip;
				return;
			}
		}
	}

	private static boolean isWinner() {
		for (int i = 0; i < board.length; i++) {
			int counter = 0;
			for (int j = 1; j < board[0].length; j++) {
				if (board[i][j] != null && board[i][j - 1] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i][j - 1].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}
		
		for (int j = 0; j < board[0].length; j++) {
			int counter = 0;
			for (int i = 1; i < board.length; i++) {
				if (board[i][j] != null && board[i - 1][j] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i - 1][j].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}

		int row = 0;
		for (int column = 0; column < board[0].length; column++) {
			int counter = 0;
			for (int i = row + 1, j = column + 1; i < board.length && j < board[0].length; i++, j++) {
				if (board[i][j] != null && board[i - 1][j - 1] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i - 1][j - 1].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}

		int column = 0;
		for ( row = 0; row < board.length; row++) {
			int counter = 0;
			for (int i = row + 1, j = column + 1; i < board.length && j < board[0].length; i++, j++) {
				if (board[i][j] != null && board[i - 1][j - 1] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i - 1][j - 1].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}
		
		row = 0;
		for (column = 0; column < board[0].length; column++) {
			int counter = 0;
			for (int i = row + 1, j = column - 1; i < board.length && j >= 0; i++, j--) {
				if (board[i][j] != null && board[i - 1][j + 1] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i - 1][j + 1].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}
		
		column = board[0].length - 1;
		for (row = 0; row < board.length; row++) {
			int counter = 0;
			for (int i = row + 1, j = column - 1; i < board.length && j >= 0; i++, j--) {
				if (board[i][j] != null && board[i - 1][j + 1] != null) { 
					if (board[i][j].getCenter().getColor().equals(board[i - 1][j + 1].getCenter().getColor())) { 
						counter++; 
						if (counter == 3) { 
							return true;
						}
					} else { 
						counter = 0;
					}
				} else { 
					counter = 0;
				}
			}
		}
		return false;
	}
	
	private static boolean isFullBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis(); 

		board = new Circle[6][7]; 
		Point topLeftCorner = new Point(-88, 70, Color.BLUE); 
		gameZone = new Rectangle( 
				topLeftCorner, 
				new Point(topLeftCorner.getPositionX() + board[0].length * CELL_SIZE, 
						  topLeftCorner.getPositionY() - board.length * CELL_SIZE)); 

		StdDraw.setXscale(-100, 100);
		StdDraw.setYscale(-100, 100);

		StdDraw.enableDoubleBuffering();
		boolean isJustPressed = false;
		boolean isEndGame = false;
		
		while (!isEndGame) {
			
			drawBoard();
			
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(0, 90, "Player " + player);
			
			if (warning != null) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.text(0, 80, warning); 
			}
			
			Integer mouseColumn = detectMouseColumn();
			if (mouseColumn != null) { 
				highlightColumn(mouseColumn); 
				if (StdDraw.isMousePressed()) { 
					if (!isJustPressed) { 
						try {
							enterChip(player, mouseColumn); 
							warning = null; 
								if (isWinner()) { 
									isEndGame = true; 
								} else { 
									if (player == 1) {
										player = 2;
									} else {
										player = 1;
									}
								}
								if (isFullBoard()) {
									isEndGame = true;
								}
						} catch (ExceptionFullColumn e) { 
							System.out.println(e.getMessage());
							warning = e.getMessage();	
						}
					}
					isJustPressed = true;
				} else
					isJustPressed = false;
			}

			StdDraw.show();
			StdDraw.pause(10);
			StdDraw.clear();
		}

		long endTime = System.currentTimeMillis(); 
		long gameDuration = endTime - startTime; 
		long seconds = gameDuration / 1000; 
		long minutes = 0; 
		if (seconds > 60) { 
			minutes = seconds / 60; 
			seconds = seconds % 60; 
		}

		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		if (isWinner()) {
			StdDraw.text(0, 90, "WINNER: player " + player);
		}
		if (isFullBoard()) {
			StdDraw.text(0, 90, "TIE");
		}
		StdDraw.text(0, 80, "The game has lasted " + minutes + ":" + seconds);
		drawBoard();
		StdDraw.show();
		StdDraw.pause(5000);
		System.exit(0); 
	}
}
