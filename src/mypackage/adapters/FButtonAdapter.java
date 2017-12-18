package mypackage.adapters;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import mypackage.FButton;
import mypackage.Sound;
import mypackage.panels.SettingsPanel;

public class FButtonAdapter implements MouseListener {

    private String title;
    private boolean is_entered;
    private Sound click_sound = new Sound(getClass().getResourceAsStream("resources/sounds/click.wav"));

    public FButtonAdapter(String title){
        this.title = title;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        fButton.loadImage("resources/buttons/"+title+"_pressed.png");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        if(!is_entered) {
            fButton.loadImage("resources/buttons/" + title + ".png");
        } else {
            fButton.loadImage("resources/buttons/"+title+"_entered.png");
        }
        if(SettingsPanel.sounds_mode) {
            click_sound.play();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        is_entered = true;
        fButton.loadImage("resources/buttons/"+title+"_entered.png");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        FButton fButton = (FButton)e.getSource();
        is_entered = false;
        fButton.loadImage("resources/buttons/"+title+".png");
    }
}