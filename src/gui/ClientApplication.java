package gui;

import connection.MultimediaClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application
{
    private MultimediaClient multimediaClient = new MultimediaClient();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Client");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300,275);

        Label hostLabel = new Label("host:");
        TextField hostTextField = new TextField("localhost");
        Button connect = new Button("Connect");

        //Style:
        hostTextField.setStyle("-fx-max-width: 150;");


        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    multimediaClient.start(hostTextField.getText(), 6666);
                } catch(IOException ioe)
                    {
                        //ignore
                    }
                if (multimediaClient.isConnected())
                {
                    System.out.println("Connected!");
                }
            }
        });

        vBox.getChildren().addAll(hostLabel,hostTextField,connect);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        multimediaClient.close();
        super.stop();
    }
}
