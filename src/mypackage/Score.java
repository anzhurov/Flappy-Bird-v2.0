package mypackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Score extends JPanel {

    private int score;
    private int x;
    private int y;
    private Image first_digit;
    private Image second_digit;
    private Image third_digit;
    private Image fourth_digit;

    public Score(int x, int y){
        score = 0;
        this.x = x;
        this.y = y;
        try {
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/0.png"))).getImage();
        } catch (NullPointerException e){ }

    }

    public void tube_is_avoided(){
        score += 1;
        try{
        if(score<10){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
        } else if(score>=10 && score<100){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
        } else if(score>=100 && score<1000){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
            third_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(2)+".png"))).getImage();
        } else if(score>=1000 && score<10000){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
            third_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(2)+".png"))).getImage();
            fourth_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(3)+".png"))).getImage();
        }
        } catch (NullPointerException e){ }
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return score+"";
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try{
        if(score<10){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            g.drawImage(first_digit, x,y,38,50,this);
        } else if(score>=10 && score<100){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
            g.drawImage(first_digit, x-20,y,38,50,this);
            g.drawImage(second_digit, x+21,y,38,50,this);
        } else if(score>=100 && score<1000){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
            third_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(2)+".png"))).getImage();
            g.drawImage(first_digit, x-41,y,38,50,this);
            g.drawImage(second_digit, x,y,38,50,this);
            g.drawImage(third_digit, x+41,y,38,50,this);
        } else if(score>=1000 && score<10000){
            first_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(0)+".png"))).getImage();
            second_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(1)+".png"))).getImage();
            third_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(2)+".png"))).getImage();
            fourth_digit = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/digits/"+toString().charAt(3)+".png"))).getImage();
            g.drawImage(first_digit, x-61,y,38,50,this);
            g.drawImage(second_digit, x-20,y,38,50,this);
            g.drawImage(third_digit, x+21,y,38,50,this);
            g.drawImage(fourth_digit, x+62,y,38,50,this);
        }
        } catch (NullPointerException e){ }
    }

}