package connection;


import gui.ServerApplication;

import java.io.*;
import java.net.*;

public class MultimediaServer
{
    public void start(int port) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        MultimediaProtocol multimediaProtocol = new MultimediaProtocol();
        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null)
        {
            outputLine = multimediaProtocol.processInput(inputLine);
            out.println(outputLine);
            if (outputLine.equals("EXIT"))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
