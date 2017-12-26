package gui;

import connection.MultimediaClient;
import filechooser.MyChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerApplication extends Application
{
    private MultimediaClient multimediaClient;

    public ControllerApplication(MultimediaClient _multimediaClient)
    {
        multimediaClient = _multimediaClient;
    }

    //delete later:
    public ControllerApplication(){}

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Controller");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,500);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Button open = new Button("OPEN");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                MyChooser myChooser = new MyChooser(multimediaClient);
                try
                {
                    myChooser.start(new Stage());
                } catch (Exception e)
                    {
                        //ignore
                    }
            }
        });

        Button play = new Button("PLAY");
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    multimediaClient.play();
                } catch (IOException ioe)
                    {
                        //deal with it
                    }
            }
        });

        Button pause = new Button("PAUSE");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    multimediaClient.pause();
                } catch (IOException ioe)
                    {
                        //deal with it
                    }
            }
        });

        Button back = new Button("<<");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    multimediaClient.backward();
                } catch (IOException ioe)
                {
                    //deal with it
                }
            }
        });

        Button forward = new Button(">>");
        forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    multimediaClient.forward();
                } catch (IOException ioe)
                {
                    //deal with it
                }
            }
        });


        //Add buttons:

        gridPane.add(open,0,0);
        gridPane.add(play,0,1);
        gridPane.add(pause,1,1);
        gridPane.add(back,0,2);
        gridPane.add(forward,1,2);
        Scene scene = new Scene(gridPane);
        //style:
        String style = getClass().getResource("controllerapplication.css").toExternalForm();
        scene.getStylesheets().add(style);
        gridPane.setStyle("-fx-background-color: #373737");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
