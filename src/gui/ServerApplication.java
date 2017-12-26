package gui;

import connection.RunnableServer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerApplication extends Application
{
    private boolean serverStarted = false;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Server");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300,275);

        Button start = new Button("START");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!serverStarted)
                {
                    serverStarted = true;
                    Thread thread = new Thread(new RunnableServer());
                    thread.start();
                }
            }
        });
        vBox.getChildren().add(start);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
