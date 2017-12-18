package mypackage;

import javax.swing.*;
import mypackage.panels.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;

public class Window extends JFrame {

    private int WIDTH = 540;
    private int HEIGHT = 720;
    private MenuPanel menu_panel;
    private GamePanel game_panel;
    private SettingsPanel settings_panel;
    private PausePanel pause_panel;
    private LosePanel lose_panel;
    private NewRecordPanel newrecord_panel;
    private RecordsPanel records_panel;
    private RecordsArray records_array;
    private ThemesPanel themes_panel;
    private ChallengesArray challenges_array;
    private File challenges_file;
    private File records_file;
    public static boolean pause_allowed;

    public Window(){
        super("Flappy Bird");
        setFocusable(true);
        records_file = new File("records.txt");
        challenges_file = new File("challenges.txt");
        game_panel = new GamePanel();
        menu_panel = new MenuPanel();
        settings_panel = new SettingsPanel();
        pause_panel = new PausePanel();
        lose_panel = new LosePanel();
        newrecord_panel = new NewRecordPanel(records_file);
        records_array = new RecordsArray();
        themes_panel = new ThemesPanel();
        records_array.read_from_file(records_file);
        records_panel = new RecordsPanel(records_array);
        challenges_array = new ChallengesArray();
        challenges_array.read_from_file(challenges_file);
        newrecord_panel.setParameters(lose_panel, game_panel, records_file,this);
        game_panel.setParameters(pause_panel, lose_panel,challenges_array, newrecord_panel,records_file,challenges_file,records_array,this);
        menu_panel.setParameters( game_panel, settings_panel, records_panel, themes_panel,this);
        pause_panel.setParameters( game_panel,this);
        settings_panel.setParameters(menu_panel, themes_panel, this);
        lose_panel.setParameters(menu_panel, game_panel, themes_panel,this);
        records_panel.setParameters(menu_panel, this);
        themes_panel.setParameters(settings_panel, challenges_array, this);
        add(menu_panel);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        setIconImage(img.getImage());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(game_panel.getShown()){
                    game_panel.key_pressed(e);
                }
                if(pause_panel.getShown()){
                    pause_panel.key_pressed(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}