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
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerApplication extends Application
{
    private MultimediaClient multimediaClient;

    public ControllerApplication(MultimediaClient _multimediaClient)
    {
        multimediaClient = _multimediaClient;
    }

   // public ControllerApplication(){}

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Controller");

        VBox vbox = new VBox(10);
        vbox.setMinSize(250,275);
        vbox.setAlignment(Pos.CENTER);

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

        Button play = new Button("\u23F5");
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

        Button pause = new Button("\u23F8");
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

        Button back = new Button("\u23EA");
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

        Button forward = new Button("\u23E9");
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
        vbox.getChildren().add(hbox1);

        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(pause, play);
        vbox.getChildren().add(hbox2);

        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER);
        hbox3.getChildren().addAll(back, forward);
        vbox.getChildren().add(hbox3);

        Scene scene = new Scene(vbox);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            if (event.getCode() == KeyCode.SPACE)
            {
                event.consume();
                try {
                    if (multimediaClient.isPlaying().equals("true"))
                        multimediaClient.pause();
                    else if (multimediaClient.isPlaying().equals("false"))
                        multimediaClient.play();
                } catch (IOException ioe){}
            }
        }
        );

        //style:
        String style = getClass().getResource("controllerapplication.css").toExternalForm();
        scene.getStylesheets().add(style);
        vbox.setStyle("-fx-background-color: #373737");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        multimediaClient.close();
        super.stop();
    }
}
