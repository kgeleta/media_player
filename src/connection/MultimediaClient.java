package connection;

import java.io.*;
import java.net.*;

public class MultimediaClient
{
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public void start(String host, int port) throws IOException
    {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public boolean isConnected()
    {
        return socket!=null && socket.isConnected();
    }

    public void close() throws IOException
    {
        if (socket != null && out != null && in != null)
        {
            out.println("EXIT");
            out.close();
            in.close();
            socket.close();
        }
    }

    public String ask(String path) throws IOException
    {
        out.println("ASK " + path);
        return in.readLine();
    }

    public String open(String path) throws IOException
    {
        out.println("OPEN " + path);
        return in.readLine();
    }

    public String play() throws IOException
    {
        out.println("PLAY");
        return in.readLine();
    }

    public String pause() throws IOException
    {
        out.println("PAUSE");
        return in.readLine();
    }

    public String forward() throws IOException
    {
        out.println("FORWARD");
        return in.readLine();
    }

    public String backward() throws IOException
    {
        out.println("BACKWARD");
        return in.readLine();
    }
}
