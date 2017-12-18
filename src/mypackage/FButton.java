package mypackage;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class FButton extends JPanel {

    private int width;
    private int height;
    private Image icon;
    private String title;
    private boolean opened = true;


    public FButton(int width, int height, String t){
        this.width = width;
        this.height = height;
        this.title = t;
        loadImage("resources/buttons/" + title + ".png");
    }

    public void loadImage(String t){
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(t))).getImage();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(icon, 0, 0, width, height, this);
    }

    public void setTitle(String t){
        this.title = t;
    }

    public String getTitle(){ return title; }

    public void setOpened(boolean opened){
        this.opened = opened;
        loadImage("resources/buttons/locked.png");
    }

    public boolean getOpened(){
        return opened;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

}