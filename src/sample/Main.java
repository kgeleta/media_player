package sample;

import filechooser.MyChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,275);
        Button open = new Button("OPEN...");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyChooser myChooser = new MyChooser();
                try {
                    Stage myChooserStage = new Stage();
                    myChooser.start(myChooserStage);
                }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
            }
        });
        gridPane.add(open,1,1);
        Scene scene = new Scene(gridPane);

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
