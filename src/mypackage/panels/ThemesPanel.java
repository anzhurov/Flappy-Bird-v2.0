package mypackage.panels;

import mypackage.Challenge;
import mypackage.ChallengesArray;
import mypackage.FButton;
import mypackage.Window;
import mypackage.adapters.FButtonAdapter;
import mypackage.adapters.FThemeAdapter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThemesPanel extends JPanel {

    private Image background;
    private Image themes;
    private FButton theme_1;
    private FButton theme_2;
    private FButton theme_3;
    private FButton tomenu_button;
    private SettingsPanel settings_panel;
    private Window jframe;
    private ArrayList<FButton> array;
    private ChallengesArray chalanges_array;

    public ThemesPanel(){
        add(theme_1 = new FButton(175,90,"theme1_true"));
        add(theme_2 = new FButton(175,90,"theme2_false"));
        theme_2.setOpened(false);
        add(theme_3 = new FButton(175,90,"theme3_false"));
        add(tomenu_button = new FButton(55,90,"back"));

        array = new ArrayList<FButton>(3);
        array.add(theme_1);
        array.add(theme_2);
        array.add(theme_3);

        theme_1.addMouseListener(new FThemeAdapter("theme1_true",  array));
        theme_2.addMouseListener(new FThemeAdapter("theme2_false",  array));
        theme_3.addMouseListener(new FThemeAdapter("theme3_false",  array));
        tomenu_button.addMouseListener(new FButtonAdapter("back"));
        tomenu_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                settings_panel.setVisible(true);
                jframe.add(settings_panel);
            }
        });
        try{
        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/themes_background.jpg"))).getImage();
        themes = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/themes.png"))).getImage();
        } catch (NullPointerException e){ }
    }

    public void setParameters(SettingsPanel settings_panel, ChallengesArray array, Window jframe){
        this.settings_panel = settings_panel;
        this.chalanges_array = array;
        this.jframe = jframe;
    }

    public int get_theme(){
        int i = 1;
        for(FButton fb : array){
            if(fb.getTitle().contains("true")){
                return i;
            }
            i++;
        }
        return 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(chalanges_array.check()){
            theme_2.setOpened(true);
            theme_2.loadImage("resources/buttons/theme2_false.png");
            background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/themes_background_opened.jpg"))).getImage();

        }
        g.drawImage(background,0,0,540,720,this);
        g.drawImage(themes,102,52,320,72,this);
        theme_1.setBounds(85,280,175,90);
        theme_2.setBounds(265,280,175,90);
        theme_3.setBounds(175,375,175,90);
        tomenu_button.setBounds(443,26,55,90);

    }
}