package game2;

import javax.swing.*;
import java.awt.*;

public class View extends JComponent {
    public static final Color BG_COLOR = Color.BLACK;
    private Game game;

    public View(Game game){
        this.game = game;

    }
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.setColor(BG_COLOR);
        g.fillRect(0,0,getWidth(), getHeight());
        for (GameObject b:game.objects)
            b.draw(g);

    }

    public Dimension getPreferredSize(){
        return Constants.FRAME_SIZE;
    }
}
