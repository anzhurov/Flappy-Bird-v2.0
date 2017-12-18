package mypackage.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Window;
import java.awt.event.*;
import java.io.*;

import mypackage.*;
import mypackage.adapters.FButtonAdapter;

public class GamePanel extends JPanel implements ActionListener {

    private FButton pause_button;
    private Image background;
    private Bird bird;
    private Tube tube1;
    private Tube tube2;
    private Strip strip1;
    private Strip strip2;
    private Timer timer;
    private boolean game_is_started;
    private boolean game_is_losed;
    private boolean game_is_paused;
    private boolean reset_from_pause;
    private boolean new_record;
    private final int DELAY = 10;
    private PausePanel pause_panel;
    private LosePanel lose_panel;
    private NewRecordPanel newRecord_panel;
    private Window jframe;
    private Score score_keeper;
    private DigitsPanel digits_panel;
    private int complexity;
    private File records_file;
    private File challenges_data_file;
    private RecordsArray records_array;
    private ChallengesArray challenges_array;
    private File challenges_file;
    private int theme_number = 1;
    private Challenge challenge;
    private int jumps;
    private int tubes_avoided;
    private int left_to_label;
    private int needed_to_jlabel;
    private int jumps_from_file = 0;
    private int tubes_avoided_from_file = 0;
    private boolean shown;
    private Sound die_sound;
    private Sound tube_avoided_sound;


    public void setParameters(PausePanel pause_panel, LosePanel lose_panel, ChallengesArray challenges_array, NewRecordPanel newRecord_panel, File f2, File challenges_file, RecordsArray records_array,  Window jframe) {
        this.jframe = jframe;
        this.pause_panel = pause_panel;
        this.lose_panel = lose_panel;
        this.newRecord_panel = newRecord_panel;
        this.challenges_array = challenges_array;
        this.records_array = records_array;
        this.records_file = f2;
        this.challenges_file  = challenges_file;
    }

    public GamePanel() {
        pause_button = new FButton(524, 30, "pause");
        add(pause_button);

        challenges_data_file = new File("challenges_data.txt");
        challenges_file = new File("challenges.txt");

        die_sound =  new Sound(getClass().getResourceAsStream("resources/sounds/die.wav"));
        tube_avoided_sound = new Sound(getClass().getResourceAsStream("resources/sounds/point.wav"));

        pause_button.addMouseListener(new FButtonAdapter("pause"));
        pause_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (digits_panel.is_stopped() && game_is_started) {
                    turn_pause();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (digits_panel.is_stopped()) {
                    if (!game_is_losed) {
                        if (!game_is_started) {
                            start();
                        }
                        bird.jump();
                        jumps++;
                    }
                }
            }
        });
    }

    public void key_pressed(KeyEvent e){
        if (digits_panel.is_stopped()) {
            if (!game_is_losed) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && game_is_started) {
                    turn_pause();
                    mypackage.Window.pause_allowed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!game_is_started) {
                        start();
                    }
                    bird.jump();
                    jumps++;
                }
            }
        }
    }

    public void set_theme(int i) {
        theme_number = i;
        bird.set_theme(i);
        tube1.set_theme(i);
        tube2.set_theme(i);
        strip1.set_theme(i);
        strip2.set_theme(i);
        background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/themes/game_background" + theme_number + ".jpg"))).getImage();
    }

    public void setShown(boolean b){
        this.shown = b;
    }

    public boolean getShown(){
        return shown;
    }

    public void turn_pause(){
        if (!game_is_losed) {
            if(challenge == null){
                pause_panel.set_image(5);
            } else if(challenge.getScreen_taps() != 0) {
                needed_to_jlabel = challenge.getScreen_taps();
                left_to_label = challenge.getScreen_taps() - jumps_from_file-jumps;
                pause_panel.set_labels_content(needed_to_jlabel + "", left_to_label + "");
                pause_panel.set_image(1);
            } else if(challenge.getTubs_avoided() != 0){
                needed_to_jlabel = challenge.getTubs_avoided();
                left_to_label = challenge.getTubs_avoided() - tubes_avoided_from_file-tubes_avoided;
                pause_panel.set_labels_content(needed_to_jlabel + "", left_to_label + "");
                pause_panel.set_image(2);
            } else if(challenge.getScore() != 0) {
                pause_panel.set_labels_content(challenge.getScore()+"");
                pause_panel.set_image(3);
            } else if(challenge.getNew_best_score()) {
                pause_panel.set_image(4);
            }
            setShown(false);
            setVisible(false);
            stop_on_pause();
            pause_panel.setVisible(true);
            pause_panel.setShown(true);
            jframe.add(pause_panel);
        }
    }

    public void timer_start() {
        timer.start();
    }

    public void timer_stop() {
        timer.stop();
    }

    public void start() {
        game_is_started = true;
        game_is_losed = false;
        bird.start();
        tube1.start();
        tube2.start();
    }

    public void stop_on_pause() {
        game_is_paused = true;
        bird.stop();
        tube1.stop();
        tube2.stop();
        strip1.stop();
        strip2.stop();
    }

    public void play_from_pause() {
        game_is_paused = false;
        digits_panel.start();
        reset_from_pause = true;
    }

    public void reset() {
        //change x and x here
        challenge = challenges_array.get_current();
        add(pause_button);
        game_is_started = false;
        game_is_losed = false;
        reset_from_pause = false;
        game_is_paused = false;
        new_record = false;
        bird = new Bird(this);
        tube1 = new Tube(this, 541, complexity);
        tube2 = new Tube(this, 851, complexity);
        strip1 = new Strip(0);
        strip2 = new Strip(540);
        strip1.start();
        strip2.start();
        timer = new Timer(DELAY, this);
        score_keeper = new Score(70, 625);
        digits_panel = new DigitsPanel(1000);
        jumps = 0;
        tubes_avoided = 0;

        if(challenge != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(challenges_data_file));
                jumps_from_file = Integer.parseInt(reader.readLine());
                tubes_avoided_from_file = Integer.parseInt(reader.readLine());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void set_complexity(int complexity) {
        this.complexity = complexity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        if (!game_is_losed) {
            pause_button.setBounds(0, 0, 540, 30);
            score_keeper.paintComponent(g);
        }
        tube1.paintComponent(g);
        tube2.paintComponent(g);
        strip1.paintComponent(g);
        strip2.paintComponent(g);
        bird.paintComponent(g);
        if (!digits_panel.is_stopped()) {
            digits_panel.paintComponent(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (digits_panel.is_stopped() && reset_from_pause) {
            bird.start();
            bird.jump();
            tube1.start();
            tube2.start();
            strip1.start();
            strip2.start();
            reset_from_pause = false;
        }
        if (game_is_losed && bird.gety() == 552) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            timer.stop();
            if(score_keeper.getScore() == 0 && records_array.is_empty()){
                lose_panel.setScore_to_losepanel(score_keeper.getScore(), 0);
            } else {
                lose_panel.setScore_to_losepanel(score_keeper.getScore(), records_array.get(0).getScore());
            }
            if (new_record) {
                newRecord_panel.initialize_record(records_array, records_file);
                setVisible(false);
                setShown(false);
                newRecord_panel.clean_field();
                newRecord_panel.setVisible(true);
                jframe.add(newRecord_panel);
                new_record = false;
                return;
            }
            setShown(false);
            setVisible(false);
            lose_panel.setVisible(true);
            jframe.add(lose_panel);
        }
        if (!game_is_losed) {
            if (bird.crash(tube1) || bird.crash(tube2) || bird.gety() == getHeight() - 129) {
                if (SettingsPanel.sounds_mode) {
                    die_sound.play();
                }
                bird.jump();
                tube1.stop();
                tube2.stop();
                strip1.stop();
                strip2.stop();
                remove(pause_button);
                game_is_losed = true;
                if ((records_array.size() == 0 || score_keeper.getScore() > records_array.get(0).getScore()) && score_keeper.getScore() != 0) {
                    new_record = true;
                    records_array.add(new Record(score_keeper.getScore(), "untitled"), 0);
                }
                if(challenge != null) {

                    int current_jumps = jumps + jumps_from_file;
                    int current_tubes_avoided = tubes_avoided + tubes_avoided_from_file;

                    if (challenge.check(current_jumps, current_tubes_avoided, score_keeper.getScore(), new_record)) {
                        challenge.setIs_done(true);
                        current_jumps = 0;
                        current_tubes_avoided = 0;
                    }

                    PrintWriter fos;
                    try {
                        fos = new PrintWriter(new FileWriter(challenges_data_file));
                        fos.println(current_jumps);
                        fos.println(current_tubes_avoided);
                        fos.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    challenges_array.write_to_file(challenges_file);
                }
            }
            if (((tube1.getx() == 51) || tube2.getx() == 51) && (!game_is_paused) && (digits_panel.is_stopped()) && (!game_is_losed)) {
                score_keeper.tube_is_avoided();
                tubes_avoided++;
                if (SettingsPanel.sounds_mode) {
                    tube_avoided_sound.play();
                }
            }
        }
        repaint();
    }
}