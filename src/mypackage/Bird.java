package mypackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mypackage.panels.GamePanel;
import mypackage.panels.SettingsPanel;

public class Bird extends JPanel implements ActionListener {

    //change x here
    private final int x = 70;
    //change y here
    private int y = 300;
    private double g = 1.4;
    private double dt = 0.3;
    private double V = -13;
    private GamePanel game_panel;
    private Timer timer;
    private Image background;
    private final int DELAY = 9;
    private int theme_number=1;
    private Sound wings_sound;

    public Bird(GamePanel game_panel) {
        this.game_panel = game_panel;
        timer = new Timer(DELAY, this);
        wings_sound =  new Sound(getClass().getResourceAsStream("wing.wav"));
//        resources/sounds/
    }

    public void set_theme(int i){
        theme_number = i;
        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/themes/bird"+theme_number+".png"))).getImage();
        repaint();
    }

    public void jump(){
        V = -13;
        if (SettingsPanel.sounds_mode) {
            wings_sound.play();
        }
    }

    public void start(){
        timer.start();
    }

    public void stop() { timer.stop(); }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (y >= 552){
            y = 552;
        } else {
            y +=  V * dt;
            V += g *  dt;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,x,y,42,26,this);
        g.setColor(Color.red);
    }

    public int gety(){ return y; }

    private Rectangle2D top_tube_front;
    private Rectangle2D top_front;
    private Rectangle2D top_tube_down;
    private Rectangle2D bottom_tube_up;
    private Rectangle2D bottom_front;
    private Rectangle2D bottom_tube_front;

    private Rectangle2D left_top;
    private Rectangle2D right_top;
    private Rectangle2D left_bottom;
    private Rectangle2D right_bottom;

    public boolean crash(Tube jPanel){
         top_tube_front = new Rectangle(jPanel.getx(),0,1,jPanel.get_bottom_line()-35);
         top_front = new Rectangle(jPanel.getx()-3,jPanel.get_bottom_line()-34,1,33);
         top_tube_down = new Rectangle(jPanel.getx()-3, jPanel.get_bottom_line()-2,75,1);
         bottom_tube_up = new Rectangle(jPanel.getx()-3, jPanel.get_bottom_line()+150, 75,1);
         bottom_front = new Rectangle(jPanel.getx()-3, jPanel.get_bottom_line()+150,1,33);
         bottom_tube_front = new Rectangle(jPanel.getx(),jPanel.get_bottom_line()+150+34,1,720-(jPanel.get_bottom_line()+150+34));

         left_top = new Rectangle(x-1,gety()-2,4,4);
         right_top = new Rectangle(x+37,gety()-2,4,4);
         left_bottom = new Rectangle(x-1,gety()+22, 4,4);
         right_bottom = new Rectangle(x+37,gety()+22,4,4);

        if(right_top.intersects(top_tube_front)||top_tube_front.intersects(right_bottom)||top_front.intersects(right_top)||top_front.intersects(right_bottom)||
                top_tube_down.intersects(right_top)||top_tube_down.intersects(left_top)||
                bottom_tube_front.intersects(right_top)||bottom_tube_front.intersects(right_bottom)||bottom_front.intersects(right_top)||
                bottom_front.intersects(right_bottom)||bottom_tube_up.intersects(right_bottom)||bottom_tube_up.intersects(left_bottom)||
                (y<0 && jPanel.getx() == 111)){
            return true;
        } else {
            return false;
        }
    }

}