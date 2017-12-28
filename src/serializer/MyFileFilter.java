package serializer;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter
{
    private final String extension1 = ".mp4", extension2 = ".flv";

    @Override
    public boolean accept(File pathname)
    {
            return pathname.isDirectory() || pathname.getName().toLowerCase().endsWith(extension1) || pathname.getName().toLowerCase().endsWith(extension2);
    }
}
