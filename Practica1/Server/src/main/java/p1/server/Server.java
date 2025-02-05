package p1.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.IllegalBlockingModeException;
import utils.ComUtils;  

public class Server {
    public static final String INIT_ERROR = "Server should be initialized with -p <port>";
    Socket socket;
    ServerSocket ss;
    int port;
    ComUtils comutils;

    public Server(int port) {
        this.port = port;
        setConnection();
    }

    public ComUtils getComutils(Socket socket) {
        if (comutils == null) {
            try {
                comutils = new ComUtils(socket.getInputStream(), socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException("I/O Error when creating the ComUtils:\n"+e.getMessage());
            }
        }    
        return comutils;
    }

    public void setConnection() {
        if (this.ss == null) {
            try {
                ss = new ServerSocket(port);
                System.out.println("Server up & listening on port "+port+"...\nPress Cntrl + C to stop.");
            } catch (IOException e) {
                throw new RuntimeException("I/O error when opening the Server Socket:\n" + e.getMessage());
            }
        }   
    }
    
    public Socket getSocket() {
        return this.socket;
    }    
    
    public void init() {  
        while(true) { 
            try {
                socket = ss.accept();
                comutils = getComutils(socket);
                System.out.println("Client accepted");
                new GameHandler(comutils).start();
                /*
                TO DO:
                Create a new GameHandler for every client.
                */
            } catch (IOException e) {
                throw new RuntimeException("I/O error when accepting a client:\n" + e.getMessage());
            } catch (SecurityException e) {
                throw new RuntimeException("Operation not accepted:\n"+e.getMessage());
            } catch (IllegalBlockingModeException e) {
                throw new RuntimeException("There is no connection ready to be accepted:\n"+e.getMessage());
            }
            
     
        }
    }



    public static void main(String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong amount of arguments.\n" + INIT_ERROR);
        }

        if (!args[0].equals("-p")) {
            throw new IllegalArgumentException("Wrong argument keyword.\n"+INIT_ERROR);
        }

        int port;

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("<port> should be an Integer.");
        }

        Server server = new Server(port);
        server.init();
       

    }
}
