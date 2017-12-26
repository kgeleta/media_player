package connection;

import gui.ServerApplication;

import java.io.IOException;

public class RunnableServer implements Runnable
{
    private ServerApplication serverApplication;

    public RunnableServer(ServerApplication serverApplication)
    {
        this.serverApplication = serverApplication;
    }

    @Override
    public void run()
    {
        MultimediaServer multimediaServer = new MultimediaServer(serverApplication);
        try
        {
            multimediaServer.start(6666);
        } catch (IOException ioe)
            {
                //ignore
            }
    }
}
