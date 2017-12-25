package serializer;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter
{
    private final String extension = ".mp4";

    @Override
    public boolean accept(File pathname)
    {
            return pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(extension);
    }
}
