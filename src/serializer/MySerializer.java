package serializer;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySerializer
{
    public static String serializeToString(File[] files)
    {
        String serialized = "";
        if (files == null)
            return serialized;

        for (File file : files)
        {
            if (!file.isHidden())
            {
                if (file.isFile())
                    serialized += ("<F>" + file.getName() + "<>");
                else if (file.isDirectory())
                    serialized += ("<D>" + file.getName() + "<>");
            }
        }
        return serialized;
    }

    public static ArrayList<MyFile> serializeToMyFile(String serialized)
    {
        //List of MyFiles:
        ArrayList<MyFile> myFileArrayList = new ArrayList<>();
        if (serialized.equals(""))
            return myFileArrayList;

        //Split:
        String[] parts = serialized.split("<>");
        //Regex:
        Pattern filePattern = Pattern.compile("<F>(.+)");
        Pattern directoryPattern = Pattern.compile("<D>(.+)");
        Matcher matcher;

        for (String part : parts)
        {
            if ((matcher = filePattern.matcher(part)).find())
                myFileArrayList.add(new MyFile(matcher.group(1), true));
            else if((matcher = directoryPattern.matcher(part)).find())
                myFileArrayList.add(new MyFile(matcher.group(1), false));
        }

        return myFileArrayList;
    }
}
