import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Пользователь on 06.11.2016.
 */
public class GameClient2 {
    Game a;
    GameComponent gcompClient;
    static Plate plate2;
    static Plate plate;
    ObjectOutputStream os;
    ObjectInputStream  is;


    GameClient2(){
        a = new Game();
        plate2 = new Plate(0,0,false);
        gcompClient = a.gcomp;
        a.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 'A') {
                    plate2.plate2x = -2;
                } else if(e.getKeyCode() == 'D') {
                    plate2.plate2x = 2;
                }

                if(e.getKeyCode() == 'W') {
                    plate2.plate2y = -2;
                } else if(e.getKeyCode() == 'S') {
                    plate2.plate2y = 2;
                }
                if(e.getKeyCode() == 'A') {
                    gcompClient.plate2ismovinglr = -2;
                } else if(e.getKeyCode() == 'D') {
                    gcompClient.plate2ismovinglr = 2;
                }
                if(e.getKeyCode() == 'W') {
                    gcompClient.plate2ismovingud = -2;
                } else if(e.getKeyCode() == 'S') {
                    gcompClient.plate2ismovingud = 2;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == 'A') {
                    plate2.plate2x = 0;
                } else if(e.getKeyCode() == 'D') {
                    plate2.plate2x = 0;
                }
                if(e.getKeyCode() == 'W') {
                    plate2.plate2y = 0;
                } else if(e.getKeyCode() == 'S') {
                    plate2.plate2y = 0;
                }
                if(e.getKeyCode() == 'A') {
                    gcompClient.plate2ismovinglr = 0;
                } else if(e.getKeyCode() == 'D') {
                    gcompClient.plate2ismovinglr = 0;
                }
                if(e.getKeyCode() == 'W') {
                    gcompClient.plate2ismovingud = 0;
                } else if(e.getKeyCode() == 'S') {
                    gcompClient.plate2ismovingud = 0;
                }
            }
        });
    }
    public static void main(String[] args) throws IOException {
        GameClient2 gk = new GameClient2();


        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    gk.startReading(plate2);

                } catch (IOException e1) {
                    System.out.println("IOException");
                } catch (ClassNotFoundException e1){
                    System.out.println("CNFException");
                }

                System.out.println("repaint");
                gk.a.gcomp.repaint();
            }
        });
        timer.start();
    }

    public void startReading(Plate c) throws IOException, ClassNotFoundException{

        Socket s = new Socket("localhost", 3000);
        os = new ObjectOutputStream(s.getOutputStream());
        os.writeObject(plate2);
        os.flush();
        is = new ObjectInputStream(s.getInputStream());
        plate = (Plate) is.readObject();
        this.a.gcomp.ballXpos = plate.ballx;
        this.a.gcomp.ballYpos = plate.bally;
        this.a.gcomp.plate1Xpos = plate.plate1x;
        this.a.gcomp.plate1Ypos = plate.plate1y;

        System.out.println("succesfull2");
        s.close();
    }
}
