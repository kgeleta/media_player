package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import player.MyMediaPlayer;


public class Main extends Application {
    private MyMediaPlayer myMediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,275);
        Button open = new Button("OPEN...");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myMediaPlayer = new MyMediaPlayer("\\php\\test.flv");
                try {
                    Stage myChooserStage = new Stage();
                    myMediaPlayer.start(myChooserStage);
                }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
            }
        });
        gridPane.add(open,1,1);

        Button pause = new Button("PAUSE");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myMediaPlayer.pause();
            }
        });
        gridPane.add(pause,1,2);

        Button play = new Button("PLAY");
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myMediaPlayer.play();
            }
        });
        gridPane.add(play,1,3);

        Button forward = new Button(">>");
        forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myMediaPlayer.forward();
            }
        });
        gridPane.add(forward,1,4);

        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
