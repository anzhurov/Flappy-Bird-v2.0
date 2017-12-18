package mypackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import mypackage.panels.GamePanel;

public class Tube extends JPanel implements ActionListener {

    private int x;
    private int dx = 2;
    private int bottom_line;
    private final int DELAY = 10;
    private GamePanel game_panel;
    private Timer timer;
    private Image lid_background;
    private Image tube_background;
    private final int SPACE;
    private int theme_number=1;

    public Tube(GamePanel game_panel, int x, int SPACE) {
        this.game_panel = game_panel;
        bottom_line = (int) (100 + Math.random() * 270);
        timer = new Timer(DELAY, this);
        this.x = x;
        this.SPACE = SPACE;
    }

    public void set_theme(int i){
        theme_number = i;
        lid_background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/themes/tube_lid"+theme_number+".png"))).getImage();
        tube_background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/themes/tube"+theme_number+".png"))).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x <= -81){
            reset();
        }
        x -= dx;
    }

    public void start(){
        timer.start();
    }

    public void stop() { timer.stop(); }

    void reset(){
        bottom_line = (int) (130 + Math.random() * 200);
        x = 541;
    }

    public int getx(){ return x; }

    public int get_bottom_line(){ return bottom_line; }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tube_background,x,0,70, bottom_line-34,this);
        g.drawImage(lid_background,x-3,bottom_line-34,76,34,this);
        g.drawImage(lid_background, x-3, bottom_line+SPACE, 76,34,this);
        g.drawImage(tube_background, x, bottom_line+SPACE+34, 70, game_panel.getHeight()-bottom_line-SPACE-135, this);
    }

}
