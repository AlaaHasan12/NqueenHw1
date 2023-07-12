package com.example.nqueenhw1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class board4 {

        @FXML
        private Button back;

        @FXML
        private GridPane board;

        @FXML
        private Button exite;

        @FXML
        private Button playagain;

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

    public static Scene getScene() {
        return null;
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
    void back(MouseEvent event) throws IOException {
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

}