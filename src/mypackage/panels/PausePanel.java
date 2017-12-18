package mypackage.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import mypackage.FButton;
import mypackage.Sound;
import mypackage.Window;
import mypackage.adapters.FButtonAdapter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class PausePanel extends JPanel{

    private FButton continue_button;
    private Image paused_image;
    private GamePanel game_panel;
    private Window jFrame;
    private Image img;
    private JLabel jlabe11;
    private JLabel jlabel2;
    private int image_number = 1;
    private boolean shown;

    public void setParameters(GamePanel game_panel, Window jFrame){
        this.game_panel = game_panel;
        this.jFrame = jFrame;
    }

    public PausePanel(){
        setLayout(null);
        paused_image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/paused.png"))).getImage();
        add(continue_button = new FButton(244,90,"continue"));

        continue_button.addMouseListener(new FButtonAdapter("continue"));
        continue_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pause_off();
            }
        });

        jlabe11 = new JLabel();
        jlabel2 = new JLabel();
        jlabe11.setFont(new Font("Arial", Font.BOLD, 20));
        jlabel2.setFont(new Font("Arial", Font.BOLD, 20));
        jlabe11.setHorizontalAlignment(SwingConstants.CENTER);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jlabe11.setVerticalAlignment(SwingConstants.CENTER);
        jlabel2.setVerticalAlignment(SwingConstants.CENTER);
        jlabe11.setPreferredSize(new Dimension(58,20));
        jlabel2.setPreferredSize(new Dimension(58,20));
        jlabe11.setForeground(Color.BLACK);
        jlabel2.setForeground(Color.BLACK);
        add(jlabe11);
        add(jlabel2);
        setBackground(new Color(3, 228, 43, 140));
    }

    public void set_labels_content(String l1, String l2){
        if(Integer.parseInt(l2) <= 0){
            l2 = 0+"";
        }
        jlabe11.setText(l1);
        jlabel2.setText(l2);
    }

    public void set_labels_content(String l1){
        jlabe11.setText(l1);
    }

    public void set_image(int i){
        image_number = i;
        img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/challenge"+i+".png"))).getImage();
    }

    public void setShown(boolean b){
        this.shown = b;
    }

    public boolean getShown(){
        return shown;
    }

    public void key_pressed(KeyEvent e){
        if(!Window.pause_allowed) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                pause_off();
            }
        }
        Window.pause_allowed = false;
    }

    public void pause_off(){
        setShown(false);
        setVisible(false);
        game_panel.setVisible(true);
        game_panel.setShown(true);
        jFrame.add(game_panel);
        game_panel.play_from_pause();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(paused_image, 125, 50, 274,74, this);
        g.drawImage(img, 92,418,340,90,this);
        continue_button.setBounds(140, 226, 244,90);
        if(image_number == 1) {
            jlabe11.setBounds(170,438,58,20);
            jlabel2.setBounds(205,469,58,20);
        } else if(image_number == 2){
            jlabe11.setBounds(182,438,58,20);
            jlabel2.setBounds(205,469,58,20);
        } else if(image_number == 3) {
            jlabe11.setBounds(228,438,58,20);
            jlabel2.setBounds(570,469,58,20);
        } else if(image_number == 4) {
            jlabe11.setBounds(570,438,58,20);
            jlabel2.setBounds(570,469,58,20);
        } else {
            jlabe11.setBounds(570,438,58,20);
            jlabel2.setBounds(570,469,58,20);
        }
    }





}