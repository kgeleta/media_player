package serializer;

import java.io.File;

public class MySerializer
{
    public static String serializeToString(File[] files)
    {
        String serialized = "";
        for (File file : files)
        {
            if (!file.isHidden())
            {
                if (file.isFile())
                    serialized += ("<F>" + file.getName() + "</F>");
                else if (file.isDirectory())
                    serialized += ("<D>" + file.getName() + "</D>");
            }
        }
        return serialized;
    }

    public static MyFile[] serializeToMyFile(String serialized)
    {
        return null;
    }


    public static void main(String[] args)
    {
        System.out.println(MySerializer.serializeToString(new File("\\").listFiles()));
    }
}
