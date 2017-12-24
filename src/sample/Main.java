package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Stop method");
    }

    public static void main(String[] args) {
        launch(args);
       /* File file = new File("\\Projects");
        File[] listOfFiles = file.listFiles();

        for (File f : listOfFiles)
        {
            if (f.isDirectory())
                System.out.println("Dir: " + f.getName());
            else if (f.isFile())
                System.out.println("File: " + f.getName());
        }
        System.exit(0);*/
    }
}
