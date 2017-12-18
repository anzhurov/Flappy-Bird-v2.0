package mypackage.adapters;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import mypackage.FButton;
import mypackage.Sound;
import mypackage.panels.SettingsPanel;

public class FTickAdapter implements MouseListener {

    private String title;
    private boolean is_entered;
    private boolean is_arraypart;
    private List<FButton> modes_array;
    private Sound click_sound = new Sound(getClass().getResourceAsStream("resources/sounds/click.wav"));

    public FTickAdapter(String title, boolean is_arraypart, List<FButton> modes_array){
        this.title = title;
        this.is_arraypart = is_arraypart;
        this.modes_array = modes_array;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        title = fButton.getTitle();
        fButton.loadImage("resources/buttons/"+title+"_entered.png");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        if(!is_arraypart){
            title = fButton.getTitle();
            if(is_entered){
                if (title.contains("true")){
                    fButton.loadImage("resources/buttons/tick_false_entered.png");
                    fButton.setTitle("tick_false");
                } else {
                    fButton.loadImage("resources/buttons/tick_true_entered.png");
                    fButton.setTitle("tick_true");
                }
            } else {
                if (title.contains("true")){
                    fButton.loadImage("resources/buttons/tick_false.png");
                    fButton.setTitle("tick_false");
                } else {
                    fButton.loadImage("resources/buttons/tick_true.png");
                    fButton.setTitle("tick_true");
                }
            }
            if (SettingsPanel.sounds_mode){
                SettingsPanel.sounds_mode = false;
            } else {
                SettingsPanel.sounds_mode = true;
            }
            if (SettingsPanel.sounds_mode) {
                click_sound.play();
            }
            return;
        }
        if(is_arraypart && title.contains("true")){
            return;
        }
        for(FButton fb : modes_array){
            fb.loadImage("resources/buttons/tick_false.png");
            fb.setTitle("tick_false");
        }
        if(is_entered){
            fButton.loadImage("resources/buttons/tick_true_entered.png");
            fButton.setTitle("tick_true");
            if (SettingsPanel.sounds_mode) {
                click_sound.play();
            }
        } else {
            fButton.loadImage("resources/buttons/tick_true.png");
            fButton.setTitle("tick_true");
            if (SettingsPanel.sounds_mode) {
                click_sound.play();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        title = fButton.getTitle();
        fButton.loadImage("resources/buttons/"+title+"_entered.png");
        is_entered = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        title = fButton.getTitle();
        fButton.loadImage("resources/buttons/"+title+".png");
        is_entered = false;
    }

}