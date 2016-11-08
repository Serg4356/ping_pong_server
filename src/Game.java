import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Game extends JFrame {
    public GameComponent gcomp;

    Game(){
        super("Ping-pong");
        JPanel panel = new JPanel();
        gcomp = new GameComponent();
        panel.add(gcomp);
        add(panel);
        keys();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setGcomp(GameComponent gcomp){
        this.gcomp = gcomp;
    }

    public void keys(){
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_LEFT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = -2;
                } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = 2;
                }
                /**
                if(e.getKeyCode() == 'A') {
                    gcomp.plate2ismovinglr = -2;
                } else if(e.getKeyCode() == 'D') {
                    gcomp.plate2ismovinglr = 2;
                }
                 */
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gcomp.ballismoving = true;
                }
                if(KeyEvent.VK_UP == e.getKeyCode()) {
                    gcomp.plate1ismovingud = -2;
                } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 2;
                }
                /**
                if(e.getKeyCode() == 'W') {
                    gcomp.plate2ismovingud = -2;
                } else if(e.getKeyCode() == 'S') {
                    gcomp.plate2ismovingud = 2;
                }
                 */
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(KeyEvent.VK_LEFT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = 0;
                } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = 0;
                }
                /**
                if(e.getKeyCode() == 'A') {
                    gcomp.plate2ismovinglr = 0;
                } else if(e.getKeyCode() == 'D') {
                    gcomp.plate2ismovinglr = 0;
                }
                 */
                if(KeyEvent.VK_UP == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 0;
                } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 0;
                }
                /**
                if(e.getKeyCode() == 'W') {
                    gcomp.plate2ismovingud = 0;
                } else if(e.getKeyCode() == 'S') {
                    gcomp.plate2ismovingud = 0;
                }
                 */
            }
        });
    }
}



