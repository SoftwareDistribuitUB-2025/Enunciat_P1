import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import utils.ComUtils;
import p1.server.Server;

public class ServerTest {

    @Test
    public void example_server_test() {
        
        (new Thread() {
            public void run() {
                Server server = new Server(8081);
                server.init();
            }
           }).start();

        try {
            Socket connection = new Socket("localhost", 8081);
            assertNotNull(connection);
            ComUtils comUtils = new ComUtils(connection.getInputStream(), connection.getOutputStream());
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
