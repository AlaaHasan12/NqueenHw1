package com.example.nqueenhw1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class User implements Initializable {
    int number = 33;
    HillClimbing hillClimbing = new HillClimbing();

    @FXML
    private ComboBox<String> algorithm;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private Button complete;

    @FXML
    private ImageView exite;

    @FXML
    private ComboBox<String> size;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void clear(MouseEvent event) {
        size.setValue(" ");
        algorithm.setValue(" ");
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void complete(MouseEvent event) throws IOException {

       if (size.getValue().equals("4")) {
           number = 4;

           System.out.println(number);
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.close();
           Stage regstage = new Stage();
           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board4.fxml")));
           regstage.resizableProperty().setValue(false);
           regstage.initStyle(StageStyle.UNDECORATED);
           regstage.setScene(new Scene(root));
           new animatefx.animation.RotateInDownRight(root).play();
           regstage.show();
       }
       else if (size.getValue().equals("5")) {
           number = 5;
            System.out.println("5");
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.close();
           stage.close();
            Stage regstage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board5.fxml")));
            regstage.resizableProperty().setValue(false);
            regstage.initStyle(StageStyle.UNDECORATED);
            regstage.setScene(new Scene(root));
           new animatefx.animation.FadeInRight(root).play();
            regstage.show();
        }
       else if (size.getValue().equals("6")) {
           number = 6;
           System.out.println("6");
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.close();
           stage.close();
           Stage regstage = new Stage();
           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board6.fxml")));
           regstage.resizableProperty().setValue(false);
           regstage.initStyle(StageStyle.UNDECORATED);
           regstage.setScene(new Scene(root));
           new animatefx.animation.SlideInLeft(root).play();
           regstage.show();
       }
       else if (size.getValue().equals("7")) {
           number = 7;
           System.out.println("7");
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.close();
           stage.close();
           Stage regstage = new Stage();
           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board7.fxml")));
           regstage.resizableProperty().setValue(false);
           regstage.initStyle(StageStyle.UNDECORATED);
           regstage.setScene(new Scene(root));
           new animatefx.animation.JackInTheBox(root).play();
           regstage.show();
       }
       else if (size.getValue().equals("8")) {
           number = 8;
           System.out.println("8");
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.close();
           Stage regstage = new Stage();
           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board8.fxml")));
           regstage.resizableProperty().setValue(false);
           regstage.initStyle(StageStyle.UNDECORATED);
           regstage.setScene(new Scene(root));
           new animatefx.animation.RollIn(root).play();
           regstage.show();
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] sizenum = {"4","5","6","7","8"};
        size.getItems().addAll(sizenum);

        String[] algorithmname = {"Hill Climbing,", "Simulated annealing"};
        algorithm.getItems().addAll(algorithmname);
    }


}
