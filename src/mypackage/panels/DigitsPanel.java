package mypackage.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DigitsPanel extends JPanel implements ActionListener{

    private Image image;
    private Timer timer;
    private boolean one_boolean;
    private boolean two_boolean;
    private boolean run;
    private int DELAY;

    public DigitsPanel(int DELAY){
        this.DELAY = DELAY;
        timer = new Timer(DELAY,this);
        reset();
    }

    public void start(){
        repaint();
        timer.start();
        run = false;
    }

    private void stop(){
        timer.stop();
        run = true;
    }

    private void reset(){
        run = true;
        image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/3.png"))).getImage();
        one_boolean = false;
        two_boolean = false;
    }

    public boolean is_stopped(){
        return run;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (one_boolean) {
            image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/1.png"))).getImage();
            one_boolean = false;
            repaint();
            return;
        }
        if (!two_boolean) {
            image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/2.png"))).getImage();
            one_boolean = true;
            two_boolean = true;
            repaint();
            return;
        }
        image = null;
        repaint();
        stop();
        reset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 192, 179, 140, 250, this );
    }
}