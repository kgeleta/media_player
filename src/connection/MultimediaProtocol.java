package connection;

import gui.ServerApplication;
import javafx.application.Platform;
import serializer.MyFileFilter;
import serializer.MySerializer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultimediaProtocol
{
    private final Pattern askPattern = Pattern.compile("ASK (.+)");
    private final Pattern openPattern = Pattern.compile("OPEN (.+)");
    private Matcher matcher;
    private ServerApplication serverApplication;

    public MultimediaProtocol(ServerApplication _serverApplication)
    {
        serverApplication = _serverApplication;
    }


    public String processInput(String input)
    {
        if ((matcher = askPattern.matcher(input)).find())
            return MySerializer.serializeToString(new File(matcher.group(1)).listFiles(new MyFileFilter()));

        else if ((matcher = openPattern.matcher(input)).find())
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverApplication.open(matcher.group(1));
                }
            });
            return "true";
        }
        else if (input.equals("PLAY"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverApplication.play();
                }
            });
            return "true";
        }
        else if (input.equals("PAUSE"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverApplication.pause();
                }
            });
            return "true";
        }
        else if (input.equals("FORWARD"))
        {
            //TO DO:
            //skip multimedia 5sec forward
            return "true";
        }
        else if (input.equals("BACKWARD"))
        {
            //TO DO:
            //skip multimedia 5sec backward
            return "true";
        }
        else if (input.equals("ISPLAYING"))
        {
            return serverApplication.isPlaying();

        }
        else if (input.equals("EXIT"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverApplication.close();
                }
            });
            return "EXIT";
        }
        return null;
    }
}
