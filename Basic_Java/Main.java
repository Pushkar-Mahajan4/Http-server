package Basic_Java;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Main{
    public static void main(String args[]) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8000), 1000);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        server.start();
    }
}
