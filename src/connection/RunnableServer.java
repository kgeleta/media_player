package connection;

import gui.ServerApplication;

import java.io.IOException;

public class RunnableServer implements Runnable
{
    @Override
    public void run()
    {
        MultimediaServer multimediaServer = new MultimediaServer();
        try
        {
            multimediaServer.start(6666);
        } catch (IOException ioe)
            {
                //ignore
            }
    }
}
