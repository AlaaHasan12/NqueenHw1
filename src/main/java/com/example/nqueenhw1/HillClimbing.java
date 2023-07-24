package com.example.nqueenhw1;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Random;

public class HillClimbing {
    int [][] testBoard;
   static int N = 4;
   GenerateRandomMap generateRandomMap = new GenerateRandomMap();
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
    private static void printBoard(int[][] board) {
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

    public void hillClimbing(int[][] board, int[] state , GridPane pane) throws IOException, InterruptedException {
        int[][] neighbourBoard = new int[N][N];
        int[] neighbourState = new int[N];
        copyState(neighbourState, state);
        generateBoard(neighbourBoard, neighbourState);

        int moves = 0;

        do {
            copyState(state, neighbourState);
            generateBoard(board, state);
            getNeighbour(neighbourBoard, neighbourState);
            if (compareStates(state, neighbourState)) {
                System.out.println("Final Result");
                printBoard(board);
                justClearBoard(pane , N );
                printBoardNQueen(pane, board, N);
                break;
            } else {
                    moves++;
                    System.out.println("Move " + moves + ":");
                    printBoard(board);
                    System.out.println("before thread");
                justClearBoard(pane, N);

                Thread.sleep(3000);

                System.out.println("after thread");

                printBoardNQueen(pane, board, N);


            }
        } while (true);
    }

    public void printBoardNQueen(GridPane gridPane , int [][] matrix , int num) {
        for (int i = 0 ; i < num ; i++) {
            for (int j = 0; j < num ; j++) {
                if (matrix[i][j] == 1) {
                    int index = (i*num) + j; // index of queen
                    ObservableList<Node> node = gridPane.getChildren();
                    Node pane = node.get(index);
                    AnchorPane anchorPane = (AnchorPane)pane;
                    Image img = new Image(getClass().getResourceAsStream("crown.png"));
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
