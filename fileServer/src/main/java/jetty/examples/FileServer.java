package jetty.examples;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

/**
 * Created by brendan on 6/22/15.
 */
public class FileServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setResourceBase("./classes");

        //Deflates http.GET calls by default this can be configured with includedMethods
        //Will check to see if pre-compressed content exists to save memory and CPU
        GzipHandler gzip = new GzipHandler();
        server.setHandler(gzip);
        //Will call each handler in the collection until a success (positive response status, response is commited) or failure (exception) occurs.
        HandlerList handlers = new HandlerList();
        //Set handlers is the batch version of addHandler, accepts an array of handlers
        //We are passing it our custom resource handler as well as the default handler to handle any requests we cannot resolve.
        handlers.setHandlers( new Handler[]{ resourceHandler, new DefaultHandler() } );
        gzip.setHandler(handlers);

        server.start();
        server.join();
    }
}
