package gui;

import connection.MultimediaClient;
import filechooser.MyChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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
        gridPane.setMinSize(300,600);

        Button open = new Button("OPEN");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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

    }
}
