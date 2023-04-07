package Basic_Java;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class RequestHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        String response = "Hello World";
        byte result[] = new byte[100];
        InputStream is = httpExchange.getRequestBody();
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String results = s.hasNext() ? s.next() : "";
        System.out.println(results);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}