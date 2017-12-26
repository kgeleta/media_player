package filechooser;

import connection.MultimediaClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import serializer.MyFile;
import serializer.MySerializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MyChooser extends Application
{
    private MultimediaClient multimediaClient;
    private GridPane gridPane = new GridPane();
    private VBox vBox = new VBox(8);
    private ToggleGroup radioButtons = new ToggleGroup();
    private boolean chosen = false;

    //Icons:
    private Image folderView = new Image(new File("icons\\folder.png").toURI().toString());
    private Image fileView = new Image(new File("icons\\file.png").toURI().toString());
    private Image backView = new Image(new File("icons\\back.png").toURI().toString());

    //Paths:
    private final String rootPath = "\\";
    private String path = "\\";

    public MyChooser(MultimediaClient _multimediaClient)
    {
        multimediaClient = _multimediaClient;
    }

    public boolean isChosen()
    {
        return chosen;
    }

    private String getName()
    {
        return ((RadioButton) radioButtons.getSelectedToggle()).getText();
    }

    private int getImageViewHashCode()
    {
        return ((ImageView) ((RadioButton) radioButtons.getSelectedToggle()).getGraphic()).getImage().hashCode();
    }

    private void clearVbox()
    {
        vBox.getChildren().clear();
    }

    private ArrayList<MyFile> askServer(String path)
    {
        String serversAnswer =  null;
        try
        {
            serversAnswer = multimediaClient.ask(path);
        }catch (IOException ioe)
            {
                //maybe later
            }
        return MySerializer.serializeToMyFile(serversAnswer);
    }

    private void listFiles(ArrayList<MyFile> files)
    {
        //Create "BACK" button:
        RadioButton back = new RadioButton("BACK");
        back.setGraphic(new ImageView(backView));
        back.setSelected(true);
        back.setToggleGroup(radioButtons);
        vBox.getChildren().add(back);

        //List all files and directories in path:
        if (files == null)
            return;
        for(MyFile file : files)
        {
                RadioButton button = new RadioButton(file.getName());
                if (file.isFile())
                    button.setGraphic(new ImageView(fileView));
                else if (file.isDirectory())
                    button.setGraphic(new ImageView(folderView));
                button.setToggleGroup(radioButtons);
                vBox.getChildren().add(button);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Choose file");
        gridPane.setMinSize(500,300);

        //Scroll Pane:
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVmax(500);
        scrollPane.setHmax(250);
        scrollPane.setPrefSize(500,250);

        //First open:
        listFiles(askServer(rootPath));
        scrollPane.setContent(vBox);
        gridPane.add(scrollPane,0,0);

        //Open button:
        Button open = new Button("OPEN");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //if it's a file:
                if (getImageViewHashCode() == fileView.hashCode())
                {
                    path += ("\\" + getName());
                    String answer = "";
                    try
                    {
                        answer = multimediaClient.open(path);
                    } catch (IOException ioe)
                        {
                            //ignore
                        }
                        if (answer.equals("true"))
                        {
                            chosen = true;
                            primaryStage.close();
                        }
                }
                //if it's a BACK:
                else if (getImageViewHashCode() == backView.hashCode())
                {
                    if (!path.equals(rootPath))
                    {
                        path = path.substring(0, path.lastIndexOf("\\"));
                        if (path.equals(""))
                            path = rootPath;
                    }
                }
                //if it's a folder:
                else if (getImageViewHashCode() == folderView.hashCode())
                {
                    path += path.equals(rootPath) ? getName() : ("\\" + getName());
                }

                //list files:
                clearVbox();
                listFiles(askServer(path));
                scrollPane.setContent(vBox);
            }
        });
        gridPane.add(open,1,3);

        //cancel button:
        Button cancel = new Button("CANCEL");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        gridPane.add(cancel, 2,3);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
