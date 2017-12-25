package connection;

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
    //private MultimediaPlayer;

    public String processInput(String input)
    {
        if ((matcher = askPattern.matcher(input)).find())
            return MySerializer.serializeToString(new File(matcher.group(1)).listFiles(new MyFileFilter()));

        else if ((matcher = openPattern.matcher(input)).find())
        {
            //TO DO:
            //open multimedia
        }
        else if (input.equals("PLAY"))
        {
            //TO DO:
            //play multimedia
            return "playing";
        }
        else if (input.equals("PAUSE"))
        {
            //TO DO:
            //pause multimedia
        }
        else if (input.equals("FORWARD"))
        {
            //TO DO:
            //skip multimedia 5sec forward
        }
        else if (input.equals("BACKWARD"))
        {
            //TO DO:
            //skip multimedia 5sec backward
        }
        else if (input.equals("EXIT"))
        {
            return "EXIT";
        }
        return null;
    }
}
