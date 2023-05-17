# Connect Four Game

Connect Four is a classic two-player board game. This package provides a Java implementation of the Connect Four game with a graphical user interface.

## Usage

To play the game, follow these steps:

1. Run the `ConnectFour` class.
2. The game window will appear.
3. The current player is displayed at the top of the window.
4. Move the mouse over the game board to highlight the column where you want to drop your chip.
5. Click the left mouse button to drop the chip in the selected column.
6. The game will automatically switch to the next player.
7. The first player to connect four chips in a row, column, or diagonal wins the game.
8. If the game board is completely filled without a winner, it is a tie.
9. After the game ends, the result will be displayed, and the game will exit after 5 seconds.

## Game Controls

- Move the mouse over the game board to select the column for dropping the chip.
- Click the left mouse button to drop the chip in the selected column.

## Class Details

### ConnectFour

The `ConnectFour` class is the main class that contains the game logic and the graphical user interface. It provides the following methods:

- `drawBoard()`: Draws the game board and the chips on the screen.
- `detectMouseColumn()`: Detects the column where the mouse cursor is located.
- `highlightColumn(Integer mouseColumn)`: Highlights the selected column.
- `enterChip(int player, int mouseColumn)`: Drops the chip in the selected column for the current player.
- `isWinner()`: Checks if a player has won the game.
- `isFullBoard()`: Checks if the game board is completely filled.
- `main(String[] args)`: The main method that runs the game.

### Circle

The `Circle` class represents the chips on the game board. It provides methods to set the position, size, and color of the circle.

### Rectangle

The `Rectangle` class represents the game board. It provides methods to set the top-left and bottom-right corners of the rectangle and draw it on the screen.

### Point

The `Point` class represents a point on the game board. It provides methods to set the position and color of the point.

### ExceptionFullColumn

The `ExceptionFullColumn` class represents an exception that is thrown when a player tries to drop a chip in a full column. It provides a constructor to set the error message for the exception.

## Dependencies

This game package requires the `StdDraw` library for graphical rendering.

## Development Environment

- Java 8 or higher.
- Java Development Kit (JDK).
- Integrated Development Environment (IDE) of your choice.

## Credits 

This game was developed by the first-year class of the Web Application Development advanced specific vocational training (I.E.S. María de Zayas y Sotomayor) as a Java exercice.

## License

This Connect Four game package is released under the [MIT License](https://opensource.org/licenses/MIT).
