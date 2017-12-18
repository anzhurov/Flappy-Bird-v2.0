package mypackage.panels;

import mypackage.*;
import mypackage.Window;
import mypackage.adapters.FButtonAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class NewRecordPanel extends JPanel {

    private FButton ok;
    private Image gameover_image;
    private Image record_field;
    private Image confetti;
    private JTextField textField;
    private LosePanel lose_panel;
    private Window jframe;
    private RecordsArray array;
    private File f;

    public NewRecordPanel(File f){
        setLayout(null);
        this.f = f;
        add(ok = new FButton(262,45,"ok_record"));
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300,20));
        Font font = new Font("ArcadeClassic", Font.BOLD,30);
        textField.setFont(font);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        add(textField);
        ok.addMouseListener(new FButtonAdapter("ok_record"));
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String username = textField.getText();
                array.get(0).setUsername(username);
                array.write_to_file(f);
                setVisible(false);
                lose_panel.setVisible(true);
                jframe.add(lose_panel);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if(textField.getText().length()>=12&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }
        });

        gameover_image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/game_over.png"))).getImage();
        record_field = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/record_field.png"))).getImage();
        confetti = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/confetti.png"))).getImage();
        setBackground(new Color(255, 62, 62, 140));
    }

    public void initialize_record(RecordsArray array, File f){
        this.array = array;
        this.f = f;
    }

    public void clean_field(){
        textField.setText("");
        textField.setFocusable(true);
    }

    public void setParameters(LosePanel lose_panel, GamePanel game_panel, File f,  Window jframe){
        this.jframe = jframe;
        this.lose_panel = lose_panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(confetti, 0,0, 540,583, this);
        g.drawImage(gameover_image, 50, 73, 424,100,this );
        g.drawImage(record_field, 62, 226, 400,239,this );
        ok.setBounds(131, 518,262,45);
        textField.setBounds(128,331,268,34);
    }
}