package sample;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller
{
    private final FileChooser fileChooser = new FileChooser();

    public void openFile(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if(file != null)
            System.out.println(file.getPath());
    }
}
