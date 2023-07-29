package com.example.nqueenhw1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class User implements Initializable {

    public static double initial_temperture ;
    public static double cooling_rate ;
    public static double final_temperture;
    public static String algorithemname;
    public static String tempfuncname;

    public static double initial_temperture(){
        return initial_temperture;
    }
    public static double cooling_rate(){
        return cooling_rate;

    }
    public static double final_temperture(){
        return final_temperture;

    }
    public static String algorithemname(){
        return algorithemname;

    }
    public static String tempfuncname(){
        return tempfuncname;

    }
    int number = 0;
    HillClimbing hillClimbing = new HillClimbing();
    SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();

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
    private ComboBox<String> tempfunc;
    @FXML
    public TextField txt1;

    @FXML
    private  TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private Text tt1;

    @FXML
    private Text tt2;

    @FXML
    private Text tt3;

    @FXML
    private Text tt4;



    @FXML
    void back(MouseEvent event) {
        size.setValue(" ");
        algorithm.setValue(" ");
        tempfunc.setValue("");
        tt1.setVisible(false);
        tt2.setVisible(false);
        tt3.setVisible(false);
        tt4.setVisible(false);
        txt1.setVisible(false);
        txt2.setVisible(false);
        txt3.setVisible(false);
    }

    @FXML
    void clear(MouseEvent event) {
        size.setValue(" ");
        algorithm.setValue(" ");
        tempfunc.setValue("");
        tt1.setVisible(false);
        tt2.setVisible(false);
        tt3.setVisible(false);
        tt4.setVisible(false);
        txt1.setVisible(false);
        txt2.setVisible(false);
        txt3.setVisible(false);
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void complete(MouseEvent event) throws IOException {
        if (algorithm.getValue() == null || size.getValue() == null){
            Stage regstage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ERRORREG.fxml")));
            regstage.resizableProperty().setValue(false);
            regstage.initStyle(StageStyle.UNDECORATED);
            regstage.setScene(new Scene(root));
            regstage.show();

            }
        else if(algorithm.getValue().equals("Simulated annealing")){
            if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("")||tempfunc.getValue().isEmpty()){
                Stage regstage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ERRORREG.fxml")));
                regstage.resizableProperty().setValue(false);
                regstage.initStyle(StageStyle.UNDECORATED);
                regstage.setScene(new Scene(root));
                regstage.show();
            }
            else {
                algorithemname = algorithm.getValue();
                initial_temperture= Double.parseDouble(txt1.getText());
                cooling_rate = Double.parseDouble(txt2.getText());
                final_temperture = Double.parseDouble(txt3.getText());
                tempfuncname = tempfunc.getValue();
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
                } else if (size.getValue().equals("5")) {
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
                } else if (size.getValue().equals("6")) {
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
                } else if (size.getValue().equals("7")) {
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
                } else if (size.getValue().equals("8")) {
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] sizenum = {"4","5","6","7","8"};
        size.getItems().addAll(sizenum);

        String[] algorithmname = {"Hill Climbing", "Simulated annealing"};
        algorithm.getItems().addAll(algorithmname);

        String[]  tempfuncnme = {"temperature = temperature/(1.0+Math.log(1.0+t)", "temperature = temperature * (1-cooling_rate)"};
        tempfunc.getItems().addAll(tempfuncnme);

        tt1.setVisible(false);
        tt2.setVisible(false);
        tt3.setVisible(false);
        tt4.setVisible(false);
        txt1.setVisible(false);
        txt2.setVisible(false);
        txt3.setVisible(false);
        tempfunc.setVisible(false);
    }
    @FXML
    public void algorithmname(){
        if (algorithm.getValue().equals("Simulated annealing")){
            tt1.setVisible(true);
            tt2.setVisible(true);
            tt3.setVisible(true);
            tt4.setVisible(true);
            txt1.setVisible(true);
            txt2.setVisible(true);
            txt3.setVisible(true);
            tempfunc.setVisible(true);

        }
        else if (algorithm.getValue().equals("Hill Climbing")){
            tt1.setVisible(false);
            tt2.setVisible(false);
            tt3.setVisible(false);
            tt4.setVisible(false);
            txt1.setVisible(false);
            txt2.setVisible(false);
            txt3.setVisible(false);
            tempfunc.setVisible(false);
        }


}


}
