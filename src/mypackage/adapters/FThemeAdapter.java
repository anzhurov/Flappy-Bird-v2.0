package mypackage.adapters;

import mypackage.FButton;
import mypackage.Sound;
import mypackage.panels.SettingsPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

public class FThemeAdapter implements MouseListener{

    private String title;
    private boolean is_entered;
    private List<FButton> array;
    private Sound click_sound = new Sound(getClass().getResourceAsStream("resources/sounds/click.wav"));

    public FThemeAdapter(String title, List<FButton> array){
        this.title = title;
        this.array = array;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        if(fButton.getOpened()){
            title = fButton.getTitle();
            fButton.loadImage("resources/buttons/"+title+"_entered.png");
        } else {
            fButton.loadImage("resources/buttons/locked.png");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        FButton fButton = (FButton) e.getSource();
        if(fButton.getOpened()) {
            if(fButton.getTitle().contains("true")){
                return;
            }
            for (FButton fb : array) {
                if (fb.getOpened()) {
                    title = fb.getTitle();
                    title = title.substring(0,6);
                    fb.loadImage("resources/buttons/" + title + "_false.png");
                    fb.setTitle(title + "_false");
                }
            }
            title = fButton.getTitle();
            title = title.substring(0,6);
            if (is_entered) {
                fButton.loadImage("resources/buttons/" + title + "_true_entered.png");
                fButton.setTitle(title + "_true");
                if (SettingsPanel.sounds_mode) {
                    click_sound.play();
                }
            } else {
                fButton.loadImage("resources/buttons/" + title + "_true.png");
                fButton.setTitle(title + "_true");
                if (SettingsPanel.sounds_mode) {
                    click_sound.play();
                }
            }
        } else {
            fButton.loadImage("resources/buttons/locked.png");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        if(fButton.getOpened()){
            title = fButton.getTitle();
            fButton.loadImage("resources/buttons/"+title+"_entered.png");
            is_entered = true;
        } else {
            fButton.loadImage("resources/buttons/locked.png");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        if(fButton.getOpened()){
            title = fButton.getTitle();
            fButton.loadImage("resources/buttons/"+title+".png");
            is_entered = false;
        } else {
            fButton.loadImage("resources/buttons/locked.png");
        }
    }
}