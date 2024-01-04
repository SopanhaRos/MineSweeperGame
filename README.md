# MineSweeperGame

This is a simple implementation of the classic Minesweeper game in Java. The project consists of several classes that work together to create the game, including the game board, Minesweeper board, and the main game logic.

Overview
Board: An abstract class that defines the basic structure of the game board. It contains methods for board initialization, display, checking if a cell contains a mine, and validating cell coordinates.

MinesweeperBoard: A concrete implementation of the Board class representing the Minesweeper game board. It includes methods for counting mines around a cell, placing mines randomly, and updating cells based on the number of mines around them.

MinesweeperGame: The main game class that orchestrates the gameplay. It handles user input, board initialization, displaying the board, revealing cells, and checking game conditions (win/lose).

MinesweeperDemo: The entry point for the Minesweeper game. It provides a simple console-based interface for user registration, login, and starting the Minesweeper game.

How to Play
Run the MinesweeperDemo class.
Choose to either register or login.
If registering, enter a unique username and password.
If logging in, provide the registered username and password.
Enter the number of rows and columns for the Minesweeper board.
The game will display the initial board, and you can start playing by entering row and column coordinates.
If you hit a mine, the game ends. If you reveal all non-mine cells, you win.
Implementation Details
Mines are randomly placed on the board at the beginning of the game.
The user interacts with the game by entering coordinates to reveal cells.
The game automatically reveals adjacent cells if the selected cell has no mines around it.
The game ends when a mine is revealed, or all non-mine cells are revealed.
Contributors
[Your Name]
Feel free to contribute, report issues, or suggest improvements to make this Minesweeper game even better!
