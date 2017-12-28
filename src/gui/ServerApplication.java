package gui;

import connection.RunnableServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.MyMediaPlayer;

//TO DO
//open mymediaplayer

public class ServerApplication extends Application
{
    private ServerApplication myself = this;
    private boolean serverStarted = false;
    private Button start = new Button("START");
    private Label statusLabel = new Label("Not connected");
    private Stage playerStage = new Stage();
    private MyMediaPlayer myMediaPlayer = null;

    public synchronized void open(String path)
    {
        if (myMediaPlayer == null)
        {
            myMediaPlayer = new MyMediaPlayer(path);
            try
            {
                myMediaPlayer.start(playerStage);
            } catch (Exception e)
            {
                //ignore
            }
        }
    }

    public synchronized String isPlaying()
    {
        return myMediaPlayer.isPlaying();
    }


    public synchronized void close()
    {
        if (myMediaPlayer != null)
        {
            try{
            myMediaPlayer.stopVideo();
            myMediaPlayer.closeWindow();
            myMediaPlayer.stop();
            } catch (Exception e)
            {}
            myMediaPlayer =  null;
        }
    }

    public synchronized void play()
    {
        if (myMediaPlayer != null)
            myMediaPlayer.play();
    }

    public synchronized void pause()
    {
        if (myMediaPlayer != null)
            myMediaPlayer.pause();
    }

    public synchronized void forward()
    {
        if (myMediaPlayer != null)
            myMediaPlayer.forward();
    }

    public synchronized void backward()
    {
        if (myMediaPlayer != null)
            myMediaPlayer.backward();
    }

    public synchronized void reset()
    {
        serverStarted = false;
        start.setVisible(true);
        statusLabel.setText("Not connected");
    }

    public synchronized void setStatus(String status)
    {
        statusLabel.setText(status);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Server");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300,275);

        //Set style:
        start.setStyle("-fx-background-color:" +
                "        linear-gradient(#f0ff35, #a9ff00)," +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);" +
                "    -fx-background-radius: 6, 5;" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
                "    -fx-text-fill: #395306;-fx-font-size: 30px;");
        vBox.setStyle("-fx-background-color: #373737");
        statusLabel.setStyle("-fx-text-fill: #b8ee36;-fx-font-size: 45px;");


        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!serverStarted)
                {
                    serverStarted = true;
                    Thread thread = new Thread(new RunnableServer(myself));
                    thread.start();
                    start.setVisible(false);
                }
            }
        });

        vBox.getChildren().add(statusLabel);
        vBox.getChildren().add(start);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}
