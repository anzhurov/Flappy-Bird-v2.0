package mypackage.panels;
import mypackage.FButton;
import mypackage.Score;
import mypackage.Window;
import mypackage.adapters.FButtonAdapter;
import mypackage.Record;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class LosePanel extends JPanel  {

    private FButton playagain_button;
    private FButton tomenu_button;
    private Image gameover_image;
    private Image score_panel;
    private Image mem_image;
    private Score score_keeper;
    private Score best_keeper;
    private Window window;
    private GamePanel game_panel;
    private MenuPanel menu_panel;
    private ThemesPanel theme_panel;

    public LosePanel(){
        setLayout(null);
        add(playagain_button = new FButton(244,90,"continue"));
        add(tomenu_button = new FButton(175,90,"to_menu"));
        score_keeper = new Score(143,294);
        best_keeper = new Score(143,382);

        playagain_button.addMouseListener(new FButtonAdapter("continue"));
        tomenu_button.addMouseListener(new FButtonAdapter("to_menu"));
        playagain_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                game_panel.setVisible(true);
                game_panel.setShown(true);
                game_panel.reset();
                game_panel.set_theme(theme_panel.get_theme());
                game_panel.timer_start();
                window.add(game_panel);
            }
        });
        tomenu_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                menu_panel.setVisible(true);
                window.add(menu_panel);
            }
        });

        gameover_image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/game_over.png"))).getImage();
        score_panel = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/score_panel.png"))).getImage();
        setBackground(new Color(3, 228, 43, 140));
    }

    public void setParameters(MenuPanel menu_panel, GamePanel game_panel, ThemesPanel theme_panel, Window window){
        this.menu_panel = menu_panel;
        this.game_panel = game_panel;
        this.theme_panel = theme_panel;
        this.window = window;
    }

    public void setScore_to_losepanel(int score1, int score2){
        this.score_keeper.setScore(score1);
        this.best_keeper.setScore(score2);
        int random;
        if(score_keeper.getScore() < 20){
            random = (int)(1+Math.random()*5);
            mem_image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/bad_memes/"+random+".jpg"))).getImage();
        } else {
            random = (int)(1+Math.random()*5);
            mem_image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/good_memes/"+random+".jpg"))).getImage();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameover_image, 50, 73, 424,100,this );
        g.drawImage(score_panel, 62, 226, 400,239,this );
        g.drawImage(mem_image, 254, 258, 175,175,this );
        playagain_button.setBounds(42,518, 244,90);
        tomenu_button.setBounds(307,518,175,90);
        score_keeper.paintComponent(g);
        best_keeper.paintComponent(g);
    }
}