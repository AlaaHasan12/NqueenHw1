package com.example.nqueenhw1;

import javafx.collections.ObservableList;
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
import java.util.Objects;

public class board7  {

    int queenArray [][] = new int[7][7];
    GenerateRandomMap generateRandomMap = new GenerateRandomMap();
    @FXML
    private Button back;

    @FXML
    private GridPane board;

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
    private AnchorPane seq0to4;

    @FXML
    private AnchorPane seq0to5;

    @FXML
    private AnchorPane seq0to6;

    @FXML
    private AnchorPane seq1to0;

    @FXML
    private AnchorPane seq1to1;

    @FXML
    private AnchorPane seq1to2;

    @FXML
    private AnchorPane seq1to3;

    @FXML
    private AnchorPane seq1to4;

    @FXML
    private AnchorPane seq1to5;

    @FXML
    private AnchorPane seq1to6;

    @FXML
    private AnchorPane seq2to0;

    @FXML
    private AnchorPane seq2to1;

    @FXML
    private AnchorPane seq2to2;

    @FXML
    private AnchorPane seq2to3;

    @FXML
    private AnchorPane seq2to4;

    @FXML
    private AnchorPane seq2to5;

    @FXML
    private AnchorPane seq2to6;

    @FXML
    private AnchorPane seq3to0;

    @FXML
    private AnchorPane seq3to1;

    @FXML
    private AnchorPane seq3to2;

    @FXML
    private AnchorPane seq3to3;

    @FXML
    private AnchorPane seq3to4;

    @FXML
    private AnchorPane seq3to5;

    @FXML
    private AnchorPane seq3to6;

    @FXML
    private AnchorPane seq4to0;

    @FXML
    private AnchorPane seq4to1;

    @FXML
    private AnchorPane seq4to2;

    @FXML
    private AnchorPane seq4to3;

    @FXML
    private AnchorPane seq4to4;

    @FXML
    private AnchorPane seq4to5;

    @FXML
    private AnchorPane seq4to6;

    @FXML
    private AnchorPane seq5to0;

    @FXML
    private AnchorPane seq5to1;

    @FXML
    private AnchorPane seq5to2;

    @FXML
    private AnchorPane seq5to3;

    @FXML
    private AnchorPane seq5to4;

    @FXML
    private AnchorPane seq5to5;

    @FXML
    private AnchorPane seq5to6;

    @FXML
    private AnchorPane seq6to0;

    @FXML
    private AnchorPane seq6to1;

    @FXML
    private AnchorPane seq6to2;

    @FXML
    private AnchorPane seq6to3;

    @FXML
    private AnchorPane seq6to4;

    @FXML
    private AnchorPane seq6to5;

    @FXML
    private AnchorPane seq6to6;
    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.close();
    }

    @FXML
    void exite(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.close();
        Stage regstage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        regstage.resizableProperty().setValue(false);
        regstage.initStyle(StageStyle.UNDECORATED);
        regstage.setScene(new Scene(root));
        regstage.show();
    }

    @FXML
    void playagain(MouseEvent event) {

    }

    @FXML
    public void generateRandomMap(ActionEvent actionEvent) {
        generateRandomMap.generatNewMap(board , 7 , queenArray);
        for (int i = 0 ; i < 7; i++) {
            for (int j = 0 ;j < 7; j++)
                System.out.print(queenArray[i][j] + " ");
            System.out.print("\n");
    }

    }

    public void startPlay(ActionEvent actionEvent) {
    }
}
