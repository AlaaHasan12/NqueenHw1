package com.example.nqueenhw1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class board4 {
    int queenArray [][] = new int[4][4];
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
        generateRandomMap.generatNewMap(board , 4 , queenArray);
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0 ;j < 4 ; j++)
                System.out.print(queenArray[i][j] + " ");
            System.out.print("\n");
        }

    }

    @FXML
    void startPlay(ActionEvent event) {

        int[][] copy2 = Arrays.stream(queenArray).map(int[]::clone).toArray(int[][]::new);
        int heuristic = 100;
        int atack = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                if (queenArray[i][j] == 1) { // find a queen
                    for (int location = 0 ; location < 1 ; location++ ) { // using to change the location of queen
                        int[][] copy = Arrays.stream(queenArray).map(int[]::clone).toArray(int[][]::new);
//                        copy[i][j] =0 ; // making a move
//                        copy[i][Math.abs(i-location)] = 1; // for a queen

                        // left Direction
                        int tested = j-1;
                        for (int test = 0 ; test < 4 ; test++) {
                            if (tested >= 0 && copy[i][tested]!=1) {
                                tested--;
                                System.out.println("Left test");
                            }
                            else {
                                if (tested >= 0 && copy[i][tested]==1) {
                                    atack++;
                                    tested--;
                                    System.out.println("Left atack");
                                }
                            }
                        }
                        //Right Direction
                        tested = j+1;
                        for (int test = 0 ; test < 4 ; test++) {
                            if (tested < 4 && copy[i][tested]!=1) {
                                tested++;
                                System.out.println("right test");
                            }
                            else if(tested <4 && copy[i][tested]==1) {
                                atack++;
                                tested++;
                                System.out.println("right atack");
                            }

                            }

                        //Up Direction
                        tested = i-1;
                        for (int test = 0 ; test < 4 ; test++) {
                            if (i == 0)
                                break; // in first row its not matter to chack right up
                            if (tested >=0 && copy[tested][j]!=1) {
                                tested--;
                                System.out.println("Up test");
                            }
                            else if (tested >=0 && copy[i][tested]==1) {
                                atack++;
                                tested--;
                                System.out.println("up atack");
                            }
                        }

                        //Down Direction
                        tested = i+1;
                        for (int test = 0 ; test < 4 ; test++) {
                            if (tested < 4  && copy[tested][j] != 1) {
                                tested++;
                                System.out.println("Down test");
                            }
                            else if (tested < 4 && copy[tested][j] == 1) {
                                atack++;
                                tested++;
                                System.out.println("Down Atack");
                            }

                        }

                        int testedi = i-1;
                        int testedj = j-1;
                        //Left Up Direction
                        for (int test = 0 ; test < 4 ; test++) {
                            if (i == 0)
                                break; // in first row its not matter to chack right up
                            if (testedi >=0 && testedj >= 0 && copy[testedi][testedj]!=1) {
                                testedi--;
                                testedj--;
                                System.out.println("Left up test");
                            }
                          else  if (testedi >=0 && testedj >= 0  && copy[testedi][testedj]==1) {
                                atack++;
                                testedi--;
                                testedj--;
                                System.out.println("Left up atack");
                            }
                        }

                        testedi = i+1;
                        testedj = j-1;
                        //Left down Direction
                        for (int test = 0 ; test < 4 ; test++) {
                            if (testedi <4  && testedj >=0 && copy[testedi][testedj]!=1) {
                                testedi++;
                                testedj--;
                                System.out.println("Left down test");
                            }
                           else if (testedi >= 0 && testedj >=0 && copy[testedi][testedj]==1) {
                                atack++;
                                testedi++;
                                testedj--;
                                System.out.println("Left down atack");
                            }
                        }
                        testedi = i-1;
                        testedj = j+1;
                        //Right Up Direction
                        for (int test = 0 ; test < 4 ; test++) {
                            if (i == 0)
                                 break; // in first row its not matter to chack right up
                            if (testedi >=0  && testedj < 4 && copy[testedi][testedj]!=1) {
                                testedi--;
                                testedj++;
                                System.out.println("right up test");
                            }
                           else if (testedi <4 && testedj <4 && copy[testedi][testedj]==1) {
                                atack++;
                                testedi--;
                                testedj++;
                                System.out.println("right up atack");
                            }
                        }
                        testedi = i+1;
                        testedj = j+1;
                        //Right Down Direction
                        for (int test = 0 ; test < 4 ; test++) {
                            if (testedi <4  && testedj < 4 && copy[testedi][testedj]!=1) {
                                testedi++;
                                testedj++;
                                System.out.println("right down test");
                            }
                           else if (testedi > 0 && testedj < 4 && copy[testedi][testedj]==1) {
                                atack++;
                                testedi++;
                                testedj++;
                                System.out.println("right down atack");
                            }
                        }
                    }
                }
            }
        }
        System.out.println(atack);
    }
}