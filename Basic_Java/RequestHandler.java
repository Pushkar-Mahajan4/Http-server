package Basic_Java;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


class RequestHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
      
        
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

        // Extract queryparameters
        String queryParams = httpExchange.getRequestURI().getQuery();
        System.out.println(queryParams);

        // Setup respond headers and response Body
        // Storing a bunch of headers in Map and adding them to response header
        Map<String, List<String>> headerMap = new HashMap<>();
        List<String> stateList = new ArrayList<String>();
        List<String> greekGod = new ArrayList<String>();
        stateList.add(0, "Seneca");
        stateList.add(1, "Caesar");
        stateList.add(2, "Hector");
        
        // Adding "Greeks header"
        greekGod.add(0, "Zeus");
        greekGod.add(1, "Plato");
        headerMap.put("state", stateList);
        headerMap.put("greeks",greekGod);

        // Setup JSON file, unfotunately the Java Standard Library doesn't support JSON natively
        // For the sake of the Project I am avoiding to use any external library
        String jsonString = "Hello World";
        byte[] bittu = jsonString.getBytes();
        for(byte i: bittu){
            System.out.print(i + " ");
        }
        

        // Adding single header 
        httpExchange.getResponseHeaders().add("authentication", "alndnakjs123lnljblkhl8hb6klb4kk8kk2bkbkhj5");
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.getResponseHeaders().putAll(headerMap); 
        httpExchange.sendResponseHeaders(200, jsonString.length());
        
        OutputStream os = httpExchange.getResponseBody();
        os.write(jsonString.getBytes());

        // Closing resources
        os.close();
        s.close();
    }

}