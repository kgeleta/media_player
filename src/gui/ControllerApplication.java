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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerApplication extends Application
{
    private MultimediaClient multimediaClient;

    public ControllerApplication(MultimediaClient _multimediaClient)
    {
        multimediaClient = _multimediaClient;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Controller");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(245,250);
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
        HBox hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().add(open);
        gridPane.add(hbox1,0,0);

        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(play, pause);
        gridPane.add(hbox2,0,1);

        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER);
        hbox3.getChildren().addAll(back, forward);
        gridPane.add(hbox3,0,2);

        Scene scene = new Scene(gridPane);
        //style:
        String style = getClass().getResource("controllerapplication.css").toExternalForm();
        scene.getStylesheets().add(style);
        gridPane.setStyle("-fx-background-color: #373737");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
