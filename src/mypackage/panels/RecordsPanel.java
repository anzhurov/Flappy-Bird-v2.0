package mypackage.panels;

import mypackage.FButton;
import mypackage.FTableModel;
import mypackage.RecordsArray;
import mypackage.adapters.FButtonAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class RecordsPanel extends JPanel{

    private RecordsArray recordsArray;
    private Window jframe;
    private MenuPanel menu_panel;
    private Image background;
    private Image records;
    private JTable table;
    private FButton tomenu_button;

    public RecordsPanel(RecordsArray array){
        setLayout(null);
        recordsArray = array;
        table = new JTable(new FTableModel(recordsArray));
        add(table);

        add(tomenu_button = new FButton(55,90,"back"));
        tomenu_button.addMouseListener(new FButtonAdapter("back"));
        tomenu_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                menu_panel.setVisible(true);
                jframe.add(menu_panel);
            }
        });

            background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/records_background.jpg"))).getImage();
            records = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/backgrounds/records.png"))).getImage();
    }

    public void setParameters(MenuPanel menu_panel, Window jframe){
        this.jframe = jframe;
        this.menu_panel = menu_panel;
    }

    public void refresh(){
        for(int i = 0; i < recordsArray.size(); i++){
            table.setValueAt(recordsArray.get(i).getUsername(),i,0);
            table.setValueAt(recordsArray.get(i).getScore(),i,1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,540,720,this);
        g.drawImage(records,102,52,320,72,this);
        tomenu_button.setBounds(443,26,55,90);
        table.setBounds(118,176,288,288);
    }
}