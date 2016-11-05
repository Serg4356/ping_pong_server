import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame {
    GameComponent gcomp;

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
                if(e.getKeyCode() == 'A') {
                    gcomp.plate2ismovinglr = -2;
                } else if(e.getKeyCode() == 'D') {
                    gcomp.plate2ismovinglr = 2;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gcomp.ballismoving = true;
                }
                if(KeyEvent.VK_UP == e.getKeyCode()) {
                    gcomp.plate1ismovingud = -2;
                } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 2;
                }
                if(e.getKeyCode() == 'W') {
                    gcomp.plate2ismovingud = -2;
                } else if(e.getKeyCode() == 'S') {
                    gcomp.plate2ismovingud = 2;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(KeyEvent.VK_LEFT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = 0;
                } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    gcomp.plate1ismovinglr = 0;
                }
                if(e.getKeyCode() == 'A') {
                    gcomp.plate2ismovinglr = 0;
                } else if(e.getKeyCode() == 'D') {
                    gcomp.plate2ismovinglr = 0;
                }
                if(KeyEvent.VK_UP == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 0;
                } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    gcomp.plate1ismovingud = 0;
                }
                if(e.getKeyCode() == 'W') {
                    gcomp.plate2ismovingud = 0;
                } else if(e.getKeyCode() == 'S') {
                    gcomp.plate2ismovingud = 0;
                }
            }
        });
    }

    public class GameComponent extends JComponent{
        public int plate1Width;
        public int plate2Width;
        public int plate1Height;
        public int plate2Height;
        public int plate1Ypos;
        public int plate2Ypos;
        public int plate1Xpos;
        public int plate2Xpos;
        public int ballYpos;
        public int ballXpos;
        public int ballsize;
        public int plate1ismovinglr = 0;
        public int plate2ismovinglr = 0;
        public int plate1ismovingud = 0;
        public int plate2ismovingud = 0;
        public int plate1score = 0;
        public int plate2score = 0;
        public boolean ballismoving = true;
        public double ballangle = 1;
        public int xDir;
        public int yDir;

        GameComponent(){
            setPreferredSize(new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT));
            startPosition();
        }

        public void startPosition(){
            plate1Width = 40;
            plate2Width = 40;
            plate1Height = 10;
            plate2Height = 10;
            plate1Ypos = 30;
            plate2Ypos = Constants.FRAME_HEIGHT - 40;
            plate1Xpos = Constants.FRAME_WIDTH/2-20;
            plate2Xpos = Constants.FRAME_WIDTH/2-20;
            ballYpos = 50;
            ballXpos = Constants.FRAME_WIDTH/2-5;
            ballsize = 10;
            xDir = 2;
            yDir = 2;
        }

        public void paint(Graphics g){
            //calculation of ball position
            ballmoving();
            //Background
            g.setColor(Color.black);
            g.fillRect(0,0,Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
            //line
            g.setColor(Color.white);
            g.drawLine(0,Constants.FRAME_HEIGHT/2,Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT/2);
            //Plate1
            g.setColor(Color.white);
            g.fillRect(plate1Xpos,plate1Ypos,plate1Width,plate1Height);
            //Plate2
            g.setColor(Color.white);
            g.fillRect(plate2Xpos,plate2Ypos,plate2Width,plate2Height);
            //ball
            g.setColor(Color.white);
            g.fillRect(ballXpos,ballYpos,ballsize,ballsize);
            //score
            g.fillRect(Constants.FRAME_WIDTH-65,0,65,25);
            g.fillRect(Constants.FRAME_WIDTH-65,Constants.FRAME_HEIGHT-25,70,25);

            Font font = new Font("Tahoma", Font.BOLD, 10);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("Player1: " + Integer.toString(plate1score),Constants.FRAME_WIDTH-63,15);
            g.drawString("Player2: " + Integer.toString(plate2score),Constants.FRAME_WIDTH-63,Constants.FRAME_HEIGHT-12);

            platemoving(plate1ismovinglr, plate2ismovinglr, plate1ismovingud, plate2ismovingud);
        }
        public void ballmoving(){

            if(ballismoving){

                if(ballXpos>=Constants.FRAME_WIDTH-ballsize) xDir = -2;
                if(ballXpos<=0) xDir = 2;
                if(((ballYpos<=plate1Ypos+plate1Height)&&(ballYpos>=plate1Ypos))&&((ballXpos<=plate1Xpos+plate1Width+10)&&(ballXpos>=plate1Xpos-10))){
                    yDir = 2;
                    //наворот для геймплея
                    if(((ballXpos<=plate1Xpos+plate1Width+10)&&(ballXpos>=plate1Xpos+plate1Width-10))&&(xDir==-2)) xDir *=-1;
                    if(((ballXpos<=plate1Xpos+10)&&(ballXpos>=plate1Xpos-10))&&(xDir==2)) xDir *=-1;
                }
                if(((ballYpos>=plate2Ypos-ballsize)&&(ballYpos<=plate2Ypos))&&((ballXpos<=plate2Xpos+plate2Width+10)&&(ballXpos>=plate2Xpos-10))) {
                    yDir = -2;
                    if(((ballXpos<=plate2Xpos+plate1Width+10)&&(ballXpos>=plate2Xpos+plate1Width-10))&&(xDir==-2)) xDir *=-1;
                    if(((ballXpos<=plate2Xpos+10)&&(ballXpos>=plate2Xpos-10))&&(xDir==2)) xDir *=-1;
                }
                if(ballYpos==0) {
                    ballismoving = false;
                    plate2score +=1;
                    startPosition();
                }
                if(ballYpos==Constants.FRAME_HEIGHT-ballsize) {
                    ballismoving = false;
                    plate1score +=1;
                    startPosition();
                }
                ballXpos += ballangle*xDir;
                ballYpos += yDir;
            }
        }

        public void platemoving(int firstlr, int secondlr, int firstud, int secondud){
            if((plate2Xpos==0&&secondlr<0)||(((plate2Xpos+plate2Width)==Constants.FRAME_WIDTH)&&(secondlr>0))) secondlr = 0;
            if((plate1Xpos==0&&firstlr<0)||(((plate1Xpos+plate1Width)==Constants.FRAME_WIDTH)&&(firstlr>0))) firstlr = 0;
            plate1Xpos += firstlr;
            plate2Xpos += secondlr;
            if((plate2Ypos==Constants.FRAME_HEIGHT-40&&secondud>0)||(((plate2Ypos+plate2Height)==Constants.FRAME_HEIGHT/2+40)&&(secondud<0))) secondud = 0;
            if((plate1Ypos==30&&firstud<0)||(((plate1Ypos+plate1Height)==Constants.FRAME_HEIGHT/2-40)&&(firstud>0))) firstud = 0;
            plate1Ypos += firstud;
            plate2Ypos += secondud;
        }
    }

    public static void main(String[] args) {
        Game a = new Game();
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.gcomp.repaint();
            }
        });
    timer.start();
    }


}
