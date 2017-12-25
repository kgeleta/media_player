package connection;

import java.io.*;
import java.net.*;

public class MultimediaServer
{
    private String status = "No connection";

    public void start(int port) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(port);
        status = "Waiting...";
        Socket clientSocket = serverSocket.accept();
        status = "Connected!";

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        MultimediaProtocol multimediaProtocol = new MultimediaProtocol();


    }
}
