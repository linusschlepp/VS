package app;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import service.ExamService;
import service.StudentService;

import javax.swing.*;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {

    public static Connection connection;

    public static void main(String[] args) throws IOException, SQLException {


      connection = DriverManager.getConnection("jdbc:mysql://im-vm-011/vs-08?enabledTLSProtocols=TLSv1.2", "vs-08", "vs-08-pw");
        // Create configuration object for webserver instance
        ResourceConfig config = new ResourceConfig();
        // Register REST-resources (i.e. service classes) with the webserver
        config.register(ServerExceptionMapper.class);
        config.register(StudentService.class);
        config.register(ExamService.class);
        // add further REST-resources like this:
        // config.register(XyzService.class);

        // Create webserver instance and start it
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        // Context is part of the URI directly after  http://domain.tld:port/
        server.createContext("/restapi", handler);
        server.start();

        // Show dialogue in order to prevent premature ending of server(s)
        System.out.println("Server running!");
        JOptionPane.showMessageDialog(null, "Stop server...");
        server.stop(0);
    }
}
