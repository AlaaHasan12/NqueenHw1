package com.example.nqueenhw1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class board4 {
    int queenArray[][] = new int[4][4];
    GenerateRandomMap generateRandomMap = new GenerateRandomMap();
    HillClimbing hillClimbing = new HillClimbing();

    @FXML
    private Button back;

    @FXML
    public GridPane board;

    @FXML
    private Button exite;

    @FXML
    private AnchorPane seq0to0;

    @FXML
    private AnchorPane seq0to1;

    @FXML
    private AnchorPane seq0to2;

    @FXML
    private AnchorPane seq0to3;

    @FXML
    private AnchorPane seq1to0;

    @FXML
    private AnchorPane seq1to1;

    @FXML
    private AnchorPane seq1to2;

    @FXML
    private AnchorPane seq1to3;

    @FXML
    private AnchorPane seq2to0;

    @FXML
    private AnchorPane seq2to1;

    @FXML
    private AnchorPane seq2to2;

    @FXML
    private AnchorPane seq2to3;

    @FXML
    private AnchorPane seq3to0;

    @FXML
    private AnchorPane seq3to1;

    @FXML
    private AnchorPane seq3to2;
    //lemaraaaaaaa
    @FXML
    private AnchorPane seq3to3;
    private static final int N = 4;


    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.close();
    }

    @FXML
    void exite(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Stage regstage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        regstage.resizableProperty().setValue(false);
        regstage.initStyle(StageStyle.UNDECORATED);
        regstage.setScene(new Scene(root));
        regstage.show();
    }


    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.close();
        Stage regstage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        regstage.resizableProperty().setValue(false);
        regstage.initStyle(StageStyle.UNDECORATED);
        regstage.setScene(new Scene(root));
        new animatefx.animation.Swing(root).play();
        regstage.show();
    }

    @FXML
    public void generateRandomMap(ActionEvent actionEvent) {
        generateRandomMap.generatNewMap(board, 4, queenArray);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(queenArray[i][j] + " ");
            System.out.print("\n");
        }

    }

    @FXML
    void startPlay(ActionEvent event) throws Exception {

        int[] state = new int[N];
        int[][] board1 = new int[N][N];

        hillClimbing.initializeStateAndBoard(queenArray, state, board1 ,4);
       // Do hill climbing on the board obtained
        hillClimbing.hillClimbing(board1, state , board, 4);
    }



}