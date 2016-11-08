import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Пользователь on 06.11.2016.
 */
public class Graph extends JComponent implements Serializable {
    public Graphics g;

    Graph(Graphics g){
        this.g = g;
    }

    Graph(){
    }
}
