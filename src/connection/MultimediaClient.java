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

    public void close() throws IOException
    {
        out.println("EXIT");
        out.close();
        in.close();
        socket.close();
    }

    public String ask(String path) throws IOException
    {
        out.println("ASK " + path);
        return in.readLine();
    }

    public static void main(String[] args) {
        MultimediaClient mc = new MultimediaClient();
        try {
            mc.start("localhost", 6666);
            System.out.println(mc.ask("\\"));
            mc.close();
        } catch (IOException ioe)
            {
                System.out.println("jeblo");
            }
    }
}
