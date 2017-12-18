package mypackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Strip extends JPanel implements ActionListener {

    private int x;
    private int dx = 2;
    private final int DELAY = 10;
    private Timer timer;
    private Image strip;
    private int theme_number=1;

    public Strip(int x) {
        timer = new Timer(DELAY, this);
        this.x = x;
    }

    public void set_theme(int i){
        theme_number = i;
        strip = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/themes/strip"+theme_number+".png"))).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x <= -540){
            reset();
        }
        x -= dx;
    }

    void reset(){
        x = 540;
    }

    public void start(){
        timer.start();
    }

    public void stop() { timer.stop(); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(strip, x, 578, 542,39,this);
    }

}