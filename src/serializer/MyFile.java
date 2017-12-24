package serializer;

public class MyFile
{
    private String name;
    private boolean file;

    public MyFile(String _name, boolean _file)
    {
        name = _name;
        file = _file;
    }

    public String getName()
    {
        return name;
    }

    public boolean isFile()
    {
        return file;
    }

    public boolean isDirectory()
    {
        return !file;
    }
}
