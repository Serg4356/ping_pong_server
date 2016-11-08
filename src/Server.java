import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Пользователь on 06.11.2016.
 */
public class Server {
    Plate plate2;
    ServerSocket ss;
    Game a;
    Socket s;
    Graph graph;

    Server() throws IOException{
        ss = new ServerSocket(3000);
        a = new Game();
        plate2 = new Plate(0,0,false);
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Server server = new Server();

            Timer timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        server.s = server.ss.accept();
                        System.out.println("server running");
                        ObjectInputStream is = new ObjectInputStream(server.s.getInputStream());
                        ObjectOutputStream os = new ObjectOutputStream(server.s.getOutputStream());
                        server.plate2 = (Plate) is.readObject();
                        server.a.gcomp.setPlate2Coord(server.plate2.plate2x, server.plate2.plate2y);
                        os.writeObject(new Plate(server.a.gcomp.plate1Xpos, server.a.gcomp.plate1Ypos, server.a.gcomp.ballXpos, server.a.gcomp.ballYpos, server.plate2.plate2x, server.plate2.plate2y));
                        os.flush();
                        server.s.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    server.a.gcomp.repaint();
                }
            });
            timer.start();
    }
}
