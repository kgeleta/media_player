package connection;


import java.io.*;
import java.net.*;

public class MultimediaServer
{
    //TO DO:
    //make gui

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

    public static void main(String[] args) {
        MultimediaServer ms = new MultimediaServer();
        try
        {
            ms.start(6666);
        }catch (IOException ioe)
        {
            System.out.println("jeblo");
        }
    }
}
