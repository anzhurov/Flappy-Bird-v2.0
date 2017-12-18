package mypackage.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mypackage.FButton;
import mypackage.adapters.FButtonAdapter;

public class MenuPanel extends JPanel{

    private FButton play_button;
    private FButton settings_button;
    private FButton records_button;
    private FButton exit_button;
    private Image background;
    private Window jframe;
    private GamePanel game_panel;
    private SettingsPanel settings_panel;
    private RecordsPanel records_panel;
    private ThemesPanel themes_panel;

    public void setParameters(GamePanel game_panel, SettingsPanel settings_panel, RecordsPanel records_panel, ThemesPanel themes_panel, Window jframe){
        this.jframe = jframe;
        this.game_panel = game_panel;
        this.settings_panel = settings_panel;
        this.records_panel = records_panel;
        this.themes_panel = themes_panel;
    }

    public MenuPanel(){
        add(play_button = new FButton(399,90,"play"));
        add(settings_button = new FButton(127,90,"settings"));
        add(records_button = new FButton(127,90,"records"));
        add(exit_button = new FButton(127,90,"exit"));

        play_button.addMouseListener(new FButtonAdapter("play"));
        settings_button.addMouseListener(new FButtonAdapter("settings"));
        records_button.addMouseListener(new FButtonAdapter("records"));
        exit_button.addMouseListener(new FButtonAdapter("exit"));

        play_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jframe.add(game_panel);
                game_panel.set_complexity(settings_panel.get_complexity());
                setVisible(false);
                game_panel.setVisible(true);
                game_panel.setShown(true);
                game_panel.reset();
                game_panel.timer_start();
                game_panel.set_theme(themes_panel.get_theme());
            }
        });
        settings_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                settings_panel.setVisible(true);
                jframe.add(settings_panel);
            }
        });
        records_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                records_panel.refresh();
                setVisible(false);
                records_panel.setVisible(true);
                jframe.add(records_panel);
            }
        });
        exit_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/menu_background.jpg"))).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
        play_button.setBounds(62,470,399,90);
        settings_button.setBounds(62,569,127,90);
        records_button.setBounds(198,569,127,90);
        exit_button.setBounds(334,569,127,90);
    }
}