/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import view.JavaFacade;
import view.JavaCodedFacadeShitIsDone;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Users;

public class RestSthis {
    static int port = 3333;
    static String ip = "localhost";
    static String publicFolder = "src/htmlFiles/";
    static JavaCodedFacadeShitIsDone facade;
    private static final boolean DEVELOPMENT_MODE = true;
    
    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
    //REST Routes

        //HTTP Server Routes
        server.createContext("/pages", new HandlerTheUserByEmail());

        server.start();

    }
    
    public static void main(String[] args) throws Exception {
        if (args.length >= 3) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
            publicFolder = args[2];
        }
        new RestSthis().run();
    }
    
    
    private static class HandlerTheUserByEmail implements HttpHandler {

        public void handle(HttpExchange he) throws IOException {
            String requestedURI = he.getRequestURI().toString();
            String id = requestedURI.substring(requestedURI.lastIndexOf("/") + 1);

            int idGotten = Integer.parseInt(id);
            try {
                String response;
                response = facade.getPersonAsJson(idGotten);
                he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                he.getResponseHeaders().add("Content-Type", "application/json");
                he.sendResponseHeaders(200, response.length());
                try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
                    pw.print(response);
                } catch (Exception e) {
                    e.getMessage();

                }
            } catch (NumberFormatException nfe) {
                nfe.getMessage();

            }
        }
    }
    
}
