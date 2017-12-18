package mypackage.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mypackage.FButton;
import mypackage.adapters.*;

public class SettingsPanel extends JPanel{

    private FButton tomenu_button;
    private FButton sounds_button;
    private FButton easy_button;
    private FButton medium_button;
    private FButton hard_button;
    private FButton themes_button;
    private Image background;
    private Image settings;
    private JPanel menu_panel;
    private Window jFrame;
    private ThemesPanel themes_panel;
    public static boolean sounds_mode;

    public void setParameters(JPanel menu_panel, ThemesPanel themes_panel, Window jFrame){
        this.menu_panel = menu_panel;
        this.jFrame = jFrame;
        this.themes_panel = themes_panel;
    }

    public SettingsPanel(){
        add(themes_button = new FButton(175,90, "themes"));
        add(tomenu_button = new FButton(55,90,"back"));
        add(sounds_button = new FButton(45,45,"tick_true"));
        add(easy_button = new FButton(45,45,"tick_false"));
        add(medium_button = new FButton(45,45,"tick_true"));
        add(hard_button = new FButton(45,45,"tick_false"));

        List<FButton> modes_array = new ArrayList<>(3);
        modes_array.add(easy_button);
        modes_array.add(medium_button);
        modes_array.add(hard_button);

        tomenu_button.addMouseListener(new FButtonAdapter("back"));
        themes_button.addMouseListener(new FButtonAdapter("themes"));
        sounds_button.addMouseListener(new FTickAdapter("tick_true", false, modes_array));
        sounds_mode = true;
        easy_button.addMouseListener(new FTickAdapter("tick_false", true, modes_array));
        medium_button.addMouseListener(new FTickAdapter("tick_true", true, modes_array));
        hard_button.addMouseListener(new FTickAdapter("tick_false", true, modes_array));
        tomenu_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                menu_panel.setVisible(true);
                jFrame.add(menu_panel);
            }
        });
        themes_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                themes_panel.setVisible(true);
                jFrame.add(themes_panel);
            }
        });
        settings = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/settings.png"))).getImage();
        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/settings_background.jpg"))).getImage();
    }

    public int get_complexity(){
        if(easy_button.getTitle().equals("tick_true")){
            return 150;
        } else if(medium_button.getTitle().equals("tick_true")){
            return 135;
        } else {
            return 120;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,540,720, this);
        g.drawImage(settings,92,52,340,72,this);
        tomenu_button.setBounds(443,26,55,90);
        themes_button.setBounds(180,570,175,90);
        sounds_button.setBounds(351,189,45,45);
        easy_button.setBounds(312,292,45,45);
        medium_button.setBounds(312,372,45,45);
        hard_button.setBounds(312,452,45,45);
    }

}