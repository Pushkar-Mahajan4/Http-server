package Basic_Java;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class RequestHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        String response = "Hello World";
        
        // Capturing request Body with InputStream
        InputStream is = httpExchange.getRequestBody();
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String results = s.hasNext() ? s.next() : "";
        System.out.println(results);

        // Capturing HTTP request headers
        // httpserver.headers can be used for this as well implements the same : Map(String, List<String>)
        Map<String, List<String>> map = new HashMap<>();
        map = httpExchange.getRequestHeaders();
        System.out.println(map.keySet());

        // Printing Http request Type
        System.out.println(httpExchange.getRequestMethod());

        // Setup respond headers and response Body 
        httpExchange.sendResponseHeaders(201, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());

        // Closing resources
        os.close();
        s.close();
    }

}