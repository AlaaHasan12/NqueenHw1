package com.example.nqueenhw1;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class SimulatedAnnealing {

    private static final int N = 4; // Number of Queens
    private static final int MAX_MOVES = 10000;
    private static final double INITIAL_TEMPERATURE = 100.0;
    private static final double COOLING_RATE = 2;
    private static final long[][] bitBoard = new long[N][N];
    private static final long[] rowBits = new long[N];


    public void initializeStateAndBoard(int[][] matrix, int[] state, int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = matrix[i][j];
                if (matrix[i][j] == 1) {
                    state[j] = i;
                }
            }
        }

    }
    private void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printState(int[] state) {
        for (int i = 0; i < N; i++) {
            System.out.print(" " + state[i] + " ");
        }
        System.out.println();
    }

    private static boolean compareStates(int[] state1, int[] state2) {
        for (int i = 0; i < N; i++) {
            if (state1[i] != state2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int calculateObjective(int[][] board, int[] state) {
        int attacking = 0;

        for (int i = 0; i < N; i++) {
            int row, col;

            // To the left of the same row (col decreases)
            row = state[i];
            col = i - 1;
            while (col >= 0 && board[row][col] != 1) {
                col--;
            }
            if (col >= 0 && board[row][col] == 1) {
                attacking++;
            }

            // To the right of the same row (col increases)
            row = state[i];
            col = i + 1;
            while (col < N && board[row][col] != 1) {
                col++;
            }
            if (col < N && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the left up (row and col decrease)
            row = state[i] - 1;
            col = i - 1;
            while (col >= 0 && row >= 0 && board[row][col] != 1) {
                col--;
                row--;
            }
            if (col >= 0 && row >= 0 && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the right down (row and col increase)
            row = state[i] + 1;
            col = i + 1;
            while (col < N && row < N && board[row][col] != 1) {
                col++;
                row++;
            }
            if (col < N && row < N && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the left down (col decreases and row increases)
            row = state[i] + 1;
            col = i - 1;
            while (col >= 0 && row < N && board[row][col] != 1) {
                col--;
                row++;
            }
            if (col >= 0 && row < N && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the right up (col increases and row decreases)
            row = state[i] - 1;
            col = i + 1;
            while (col < N && row >= 0 && board[row][col] != 1) {
                col++;
                row--;
            }
            if (col < N && row >= 0 && board[row][col] == 1) {
                attacking++;
            }
        }

        return attacking / 2;
    }

    private static void generateBoard(int[][] board, int[] state) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            board[state[i]][i] = 1;
        }
    }

    private static void copyState(int[] state1, int[] state2) {
        System.arraycopy(state2, 0, state1, 0, N);
    }

    private static void getNeighbour(int[][] board, int[] state) {
        int[][] opBoard = new int[N][N];
        int[] opState = new int[N];

        copyState(opState, state);
        generateBoard(opBoard, opState);

        int opObjective = calculateObjective(opBoard, opState);

        int[][] neighbourBoard = new int[N][N];
        int[] neighbourState = new int[N];

        copyState(neighbourState, state);
        generateBoard(neighbourBoard, neighbourState);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != state[i]) {
                    neighbourState[i] = j;
                    neighbourBoard[neighbourState[i]][i] = 1;
                    neighbourBoard[state[i]][i] = 0;

                    int temp = calculateObjective(neighbourBoard, neighbourState);

                    if (temp <= opObjective) {
                        opObjective = temp;
                        copyState(opState, neighbourState);
                        generateBoard(opBoard, opState);
                    }

                    neighbourBoard[neighbourState[i]][i] = 0;
                    neighbourState[i] = state[i];
                    neighbourBoard[state[i]][i] = 1;
                }
            }
        }

        copyState(state, opState);
        generateBoard(board, state);
    }

   public void simulatedAnnealing(int[][] board, int[] state, GridPane pane) throws IOException, InterruptedException {
        int[][] neighbourBoard = new int[N][N];
        int[] neighbourState = new int[N];
        copyState(neighbourState, state);
        generateBoard(neighbourBoard, neighbourState);

        int moves = 0;
        double initialTemperature = 100.0; // Initial temperature
        double coolingRate = 0.01; // Cooling rate
        int maxIterations = 10000; // Maximum number of iterations
        double finalTemperature = 0.01; // Final temperature

        double temperature = initialTemperature;

        do {
            copyState(state, neighbourState);
            generateBoard(board, state);
            getNeighbour(neighbourBoard, neighbourState);

            int currentEnergy = calculateEnergy(board);
            int neighbourEnergy = calculateEnergy(neighbourBoard);

            if (compareStates(state, neighbourState) || acceptanceProbability(currentEnergy, neighbourEnergy, temperature) > Math.random()) {
                copyState(state, neighbourState);
                System.out.println("Move " + moves + ":");
                printBoard(board);
                System.out.println("before thread");
                justClearBoard(pane, N);
                printBoardNQueen(pane, board, N);
                Thread.sleep(3000);
                System.out.println("\n after thread ");
            }

            // Reduce the temperature
            temperature *= (1.0 - coolingRate);

            // You can use either a maximum number of iterations or a final temperature as the stopping criterion
            // Here, we will use the final temperature as the stopping criterion
            if (temperature <= finalTemperature || currentEnergy == 0) {
                System.out.println("");
                System.out.println("Final Result");
                System.out.println("Final Temperature: " + temperature);
                System.out.println("Final Iteration: " + moves);
                printBoard(board);
                justClearBoard(pane, N);
                printBoardNQueen(pane, board, N);
                break;
            }

            moves++;
        } while (moves < maxIterations);
    }
    private double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0; // Accept the new state if it's better than the current state
        }
        return Math.exp((energy - newEnergy) / temperature);
    }
    private int calculateEnergy(int[][] board) {
        int energy = 0;
        int N = board.length;

        // Check for attacks in each row
        for (int row = 0; row < N; row++) {
            for (int i = 0; i < N; i++) {
                if (board[row][i] == 1) {
                    // Check for attacks in the same row
                    for (int j = i + 1; j < N; j++) {
                        if (board[row][j] == 1) {
                            energy++;
                        }
                    }
                    // Check for attacks along diagonals
                    for (int k = 1; row + k < N && i + k < N; k++) {
                        if (board[row + k][i + k] == 1) {
                            energy++;
                        }
                    }
                    for (int k = 1; row + k < N && i - k >= 0; k++) {
                        if (board[row + k][i - k] == 1) {
                            energy++;
                        }
                    }
                    break; // Only one queen per row, so break after finding the queen in this row
                }
            }
        }

        return energy;
    }


    private void copyBoard(int[][] dest, int[][] src) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, N);
        }
    }
    private int[] generateRandomState() {
        int[] state = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            state[i] = random.nextInt(N);
        }

        return state;
    }

    private int[] getNeighbour(int[] state) {
        int[] neighbourState = Arrays.copyOf(state, N);
        Random random = new Random();
        int randomRow = random.nextInt(N);
        int randomCol = random.nextInt(N);
        neighbourState[randomRow] = randomCol;
        return neighbourState;
    }


    private int calculateCost(int[] state) {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == j - i) {
                    cost++;
                }
            }
        }
        System.out.println("the cost for the state is  "+cost);
        return cost;
    }

    private boolean acceptWorseMove(int currentCost, int neighbourCost, double temperature) {
        if (neighbourCost < currentCost) {
            return true;
        } else {
            double probability = Math.exp((currentCost - neighbourCost) / temperature);
            return Math.random() < probability;
        }
    }

    private void printBoard(int[] state) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (state[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void printBoardNQueen(GridPane gridPane , int [][] matrix , int num) {
        for (int i = 0 ; i < num ; i++) {
            for (int j = 0; j < num ; j++) {
                if (matrix[i][j] == 1) {
                    int index = (i*num) + j; // index of queen
                    ObservableList<Node> node = gridPane.getChildren();
                    Node pane = node.get(index);
                    AnchorPane anchorPane = (AnchorPane)pane;
                    Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("crown.png")));
                    ImageView imageView = new ImageView(img);
                    imageView.fitWidthProperty().bind(anchorPane.widthProperty());
                    imageView.fitHeightProperty().bind(anchorPane.heightProperty());
                    anchorPane.getChildren().add(imageView);
                }
            }
        }
    }

    public void justClearBoard (GridPane board, int num) {

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                anchorPane.getChildren().clear();
            }
        }

    }
}