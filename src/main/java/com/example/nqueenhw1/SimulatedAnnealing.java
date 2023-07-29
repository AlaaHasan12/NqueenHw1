package com.example.nqueenhw1;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class SimulatedAnnealing {
    public double initial_temperture = User.initial_temperture();
    public double cooling_rate = User.cooling_rate();
    public double final_temperture = User.final_temperture();
    public String tempfuncname = User.tempfuncname();

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

    public void simulatedAnnealing(int[][] board, int[] state, GridPane pane, int num) throws IOException, InterruptedException {
        System.out.println(" ------------------------------SIMULATED ANNEALING------------------------");
        System.out.println("| the initial temperature: " + formatValue(String.valueOf(initial_temperture), 46) + "|");
        System.out.println("| the final temperature:   " + formatValue(String.valueOf(final_temperture), 46) + "|");
        System.out.println("| the cooling rate:        " + formatValue(String.valueOf(cooling_rate), 46) + "|");
        System.out.println("| the equation is:         " + formatValue(tempfuncname, 46) + "|");
        System.out.println("--------------------------------------------------------------------------");

        int moves = 0;
        double temperature = initial_temperture;

        int[] bestState = new int[num];
        copyState(bestState, state, num);

        int currentObjective = calculateObjective(board, state, num);
        int bestObjective = calculateObjective(board, bestState, num);
        for (double t = temperature; temperature > final_temperture; t--){

            if (tempfuncname.equals("temperature = temperature/(1.0+Math.log(1.0+t)")){
                temperature = temperature/(1.0+Math.log(1.0+t));
            }
            else if(tempfuncname.equals("temperature = temperature * (1-cooling_rate)")){
                temperature = temperature * (1-cooling_rate);
            }
            if (currentObjective == 0) {
                System.out.println("------------------------------Found a solution:-------------------------------------");
                printBoard(board, num);
                justClearBoard(pane, num);
                printBoardNQueen(pane, board, num);
                break;
            }

            int[] nextState = new int[num];
            copyState(nextState, state, num);
            getNeighbour(board, nextState, num);

            int nextObjective = calculateObjective(board, nextState, num);


            if (nextObjective <= currentObjective || Math.random() < acceptanceProbability(currentObjective, nextObjective, temperature)) {
                copyState(state, nextState, num);
                currentObjective = nextObjective;

                if (currentObjective < bestObjective) {
                    copyState(bestState, state, num);
                    bestObjective = currentObjective;
                }
            }
            moves++;
            System.out.println("Moves: " + moves + ", Temperature: " + temperature + "  the best: " +bestObjective );
            printBoard(board, num);
            justClearBoard(pane, num);
            Thread.sleep(10);
            printBoardNQueen(pane, board, num);
        }
    }

    private double acceptanceProbability(int currentObjective, int nextObjective, double temperature) {
        double delta = nextObjective -currentObjective;
        return Math.exp((-delta) / temperature);
    }
    public static String formatValue(String value, int totalLength) {
        int valueLength = value.length();
        int spacesToAdd = totalLength - valueLength;
        StringBuilder formattedValue = new StringBuilder(value);

        for (int i = 0; i < spacesToAdd; i++) {
            formattedValue.append(" ");
        }
        return formattedValue.toString();
    }

    public void printBoardNQueen(GridPane gridPane, int[][] board, int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (board[i][j] == 1) {
                    int index = (i * num) + j; // index of queen
                    ObservableList<Node> node = gridPane.getChildren();
                    Node pane = node.get(index);
                    AnchorPane anchorPane = (AnchorPane) pane;
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