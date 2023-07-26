package com.example.nqueenhw1;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HillClimbing{
    public void initializeStateAndBoard(int[][] matrix, int[] state, int[][] board ,int num) throws IOException {
        System.out.println("number: " +num);
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                board[i][j] = matrix[i][j];
                if (matrix[i][j] == 1) {
                    state[j] = i;
                }
            }
        }
    }
    private void printBoard(int[][] board , int num) throws IOException {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printState(int[] state , int num) throws IOException {
        for (int i = 0; i < num; i++) {
            System.out.print(" " + state[i] + " ");
        }
        System.out.println();
    }

    private boolean compareStates(int[] state1, int[] state2 , int num) throws IOException {
        for (int i = 0; i < num; i++) {
            if (state1[i] != state2[i]) {
                return false;
            }
        }
        return true;
    }

    private int calculateObjective(int[][] board, int[] state , int num) throws IOException {
        int attacking = 0;

        for (int i = 0; i < num; i++) {
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
            while (col < num && board[row][col] != 1) {
                col++;
            }
            if (col < num && board[row][col] == 1) {
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
            while (col < num && row < num && board[row][col] != 1) {
                col++;
                row++;
            }
            if (col < num && row < num && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the left down (col decreases and row increases)
            row = state[i] + 1;
            col = i - 1;
            while (col >= 0 && row < num && board[row][col] != 1) {
                col--;
                row++;
            }
            if (col >= 0 && row < num && board[row][col] == 1) {
                attacking++;
            }

            // Diagonally to the right up (col increases and row decreases)
            row = state[i] - 1;
            col = i + 1;
            while (col < num && row >= 0 && board[row][col] != 1) {
                col++;
                row--;
            }
            if (col < num && row >= 0 && board[row][col] == 1) {
                attacking++;
            }
        }

        return attacking / 2;
    }

    private void generateBoard(int[][] board, int[] state , int num) throws IOException {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < num; i++) {
            board[state[i]][i] = 1;
        }
    }

    private void copyState(int[] state1, int[] state2 , int num) throws IOException {
        System.arraycopy(state2, 0, state1, 0, num);
    }

    private void getNeighbour(int[][] board, int[] state ,int num) throws IOException {
        int temp2 =0;
        int[][] opBoard = new int[num][num];
        int[] opState = new int[num];

        copyState(opState, state , num);
        generateBoard(opBoard, opState, num);

        int opObjective = calculateObjective(opBoard, opState , num);

        int[][] neighbourBoard = new int[num][num];
        int[] neighbourState = new int[num];

        copyState(neighbourState, state , num);
        generateBoard(neighbourBoard, neighbourState , num);

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num ; j++) {
                if (j != state[i]) {
                    neighbourState[i] = j;
                    neighbourBoard[neighbourState[i]][i] = 1;
                    neighbourBoard[state[i]][i] = 0;

                    int temp = calculateObjective(neighbourBoard, neighbourState , num);
                    if (temp <= opObjective) {
                        opObjective = temp;
                        copyState(opState, neighbourState , num);
                        generateBoard(opBoard, opState , num);
                    }
                    neighbourBoard[neighbourState[i]][i] = 0;
                    neighbourState[i] = state[i];
                    neighbourBoard[state[i]][i] = 1;
                }
            }
        }

        copyState(state, opState , num);
        generateBoard(board, state , num);
    }

    public void hillClimbing(int[][] board, int[] state , GridPane pane , int num) throws IOException, InterruptedException {
        int[][] neighbourBoard = new int[num ][num];
        int[] neighbourState = new int[num];
        copyState(neighbourState, state , num);
        generateBoard(neighbourBoard, neighbourState , num);
        int moves = 0;

        do {
            copyState(state, neighbourState , num);
            generateBoard(board, state , num);
            getNeighbour(neighbourBoard, neighbourState , num);
            if (compareStates(state, neighbourState , num)) {
                System.out.println("Final Result");
                printBoard(board , num);
                justClearBoard(pane , num);
                printBoardNQueen(pane, board, num);
                break;
            } else {
                moves++;
                System.out.println("Moves: " + moves);
                printBoard(board , num);
                justClearBoard(pane, num );
                Thread.sleep(1000);
                printBoardNQueen(pane, board, num);
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
