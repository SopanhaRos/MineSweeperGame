package MineSweeperGame;

import java.util.Random;
import java.util.Scanner;

abstract class Board {
    protected int rows;
    protected int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    abstract void initialize();

    abstract void display();

    abstract boolean isMine(int row, int col);

    abstract boolean isValid(int row, int col);
}

class MinesweeperBoard extends Board {
    private char[][] board;
    private boolean[][] mines;
    private boolean[][] revealed;

    public MinesweeperBoard(int rows, int cols) {
        super(rows, cols);
        board = new char[rows][cols];
        mines = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
    }

        private int countMinesAround(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValid(i, j) && isMine(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
                mines[i][j] = false;
            }
        }
        placeMines();
    }

    private void placeMines() {
        Random random = new Random();
        int totalCells = rows * cols;
        int minesToPlace = totalCells / 8; // Adjust this value based on the desired mine density

        while (minesToPlace > 0) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (!mines[row][col]) {
                mines[row][col] = true;
                minesToPlace--;
            }
        }
    }

    @Override
    void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    int minesAround = countMinesAround(i, j);
                    System.out.print(minesAround + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    @Override
    boolean isMine(int row, int col) {
        return mines[row][col];
    }

    @Override
    boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void updateCell(int row, int col, int minesAround) {
        board[row][col] = (minesAround > 0) ? (char) ('0' + minesAround) : ' ';
    }
}

class MinesweeperGame {
    private MinesweeperBoard board;
    private boolean[][] revealed;

    public MinesweeperGame(int rows, int cols) {
        board = new MinesweeperBoard(rows, cols);
        revealed = new boolean[rows][cols];
    }

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            board.initialize();

            while (true) {
                board.display();
                System.out.print("Enter row and column (e.g., 1 2): ");
                int row = scanner.nextInt() -1;
                int col = scanner.nextInt() -1;

                if (!board.isValid(row, col)) {
                    System.out.println("Invalid input. Try again.");
                    continue;
                }

                if (revealed[row][col]) {
                    System.out.println("Already revealed. Try again.");
                    continue;
                }

                if (board.isMine(row, col)) {
                    System.out.println("Game over! You hit a mine.");
                    break;
                } else {
                    revealCell(row, col);
                }

                if (isGameWon()) {
                    System.out.println("Congratulations! You won!");
                    break;
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        revealed[row][col] = true;
        int minesAround = countMinesAround(row, col);

        if (minesAround == 0) {
            // Auto-reveal adjacent cells if no mines around
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (board.isValid(i, j) && !revealed[i][j]) {
                        revealCell(i, j);
                    }
                }
            }
        }

        // Update the board with the appropriate value
        board.updateCell(row, col, minesAround);
    }

    private int countMinesAround(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (board.isValid(i, j) && board.isMine(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isGameWon() {
        for (int i = 0; i < board.rows; i++) {
            for (int j = 0; j < board.cols; j++) {
                if (!revealed[i][j] && !board.isMine(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class MinesweeperDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("other. Quit");
        int choice = sc.nextInt();
        sc.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                registerUser(sc);
                break;

            case 2:
                loginUser(sc);
                break;

            default:
                System.out.println("Quitting...");
                break;
        }

        sc.close();
    }

    private static void registerUser(Scanner scanner) {
        String tmpName;
        String tmpPassword;

        do {
            System.out.println("Input name: ");
            tmpName = scanner.nextLine();
        } while (LogIn.VerifyUserName(tmpName));

        System.out.println("Input password: ");
        tmpPassword = scanner.nextLine();
        Register.registerUser(tmpName, tmpPassword);
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("Input name: ");
        String tmpName = scanner.nextLine();
        System.out.println("Input password: ");
        String tmpPassword = scanner.nextLine();
        User tmpUser = new User(tmpName, tmpPassword);

        if (LogIn.loginUser(tmpUser)) {
            System.out.println("Login successful. Starting Minesweeper game...");
            playMinesweeper(scanner);
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    private static void playMinesweeper(Scanner scanner) {
        System.out.println("Enter the number of rows for the Minesweeper board: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns for the Minesweeper board: ");
        int cols = scanner.nextInt();

        MinesweeperGame game = new MinesweeperGame(rows, cols);
        game.play();
    }
}
