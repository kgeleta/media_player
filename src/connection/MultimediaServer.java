package connection;


import gui.ServerApplication;
import javafx.application.Platform;

import java.io.*;
import java.net.*;

public class MultimediaServer
{
    private ServerApplication serverApplication;

    public MultimediaServer(ServerApplication serverApplication)
    {
        this.serverApplication = serverApplication;
    }

    public void start(int port) throws IOException
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                serverApplication.setStatus("Waiting...");
            }
        });

        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        MultimediaProtocol multimediaProtocol = new MultimediaProtocol(serverApplication);
        String inputLine, outputLine;


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                serverApplication.setStatus("Connected!");
            }
        });

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                serverApplication.reset();
            }
        });
    }
}
