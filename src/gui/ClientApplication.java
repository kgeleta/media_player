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

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(300,275);

        Label hostLabel = new Label("host:");
        TextField hostTextField = new TextField("localhost");
        Button connect = new Button("CONNECT");

        //Style:
        hostTextField.setStyle("-fx-max-width: 150;");
        connect.setStyle("-fx-background-color:" +
                "        linear-gradient(#f0ff35, #a9ff00)," +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);" +
                "    -fx-background-radius: 6, 5;" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );" +
                "    -fx-text-fill: #395306;-fx-font-size: 30px;");
        vBox.setStyle("-fx-background-color: #373737;");
        hostLabel.setStyle("-fx-text-fill: #b8ee36;-fx-font-size: 25px;");


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
                    ControllerApplication controllerApplication = new ControllerApplication(multimediaClient);
                    try
                    {
                    controllerApplication.start(new Stage());
                    } catch (Exception e)
                        {
                            //deal with it
                        }
                    primaryStage.hide();
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
