package player;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;


public class MyMediaPlayer extends Application {

    private String mediaSource = "\\Users\\Leslie\\Downloads\\test.flv";

    public void setMediaSource(String _mediaSource)
    {
        mediaSource = _mediaSource;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My Player");

        //create media player
        File file = new File(mediaSource);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setPreserveRatio(true);
        StackPane root = new StackPane();

        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,960,540);
        scene.setFill(Color.BLACK);


        final DoubleProperty width = mediaView.fitWidthProperty();
        final DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));



        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
