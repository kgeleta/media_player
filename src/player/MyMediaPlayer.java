package player;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;


public class MyMediaPlayer extends Application {

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    public MyMediaPlayer(String source)
    {
        media = new Media(new File(source).toURI().toString());
    }

    public void play()
    {
        mediaPlayer.play();
    }

    public void pause()
    {
        mediaPlayer.pause();
    }

    public void stopVideo()
    {
        mediaPlayer.stop();
        mediaPlayer.dispose();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My Player");

        //create media player
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        mediaView = new MediaView(mediaPlayer);

        mediaView.setPreserveRatio(true);
        StackPane root = new StackPane();

        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,960,540);
        root.setStyle("-fx-background-color: #000000");


        final DoubleProperty width = mediaView.fitWidthProperty();
        final DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
