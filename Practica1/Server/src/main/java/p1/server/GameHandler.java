package p1.server;
import utils.ComUtils;

public class GameHandler {

    /*
    TO DO
    Protocol dynamics from Server.
    Methods: run(), init(), play().
     */
    ComUtils comutils;

    public GameHandler(ComUtils comutils) {
        this.comutils = comutils;
    }

    public void start() {
     System.out.println("GameHandler started");
    }
    
    
}
