import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame implements ActionListener, KeyListener {
    String lmusic = "src/audios/lobby-music.wav";
    File music = new File(lmusic);
    AudioInputStream m = AudioSystem.getAudioInputStream(music);
    Clip clip = AudioSystem.getClip();
    JTextField text = new JTextField(10);
    ImageIcon home = new ImageIcon("src/icons/home.gif");
    JLabel title = new JLabel("Welcome To Tic Tac Toe!");
    JButton Log = new JButton("Play");
    JLabel Bg = new JLabel();
    HomePage() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Loop_Music();
        Setup();
        this.setSize(500,500);
        Bg.setBounds(0, 0, getWidth(), getHeight());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tik Tak Toe");
        this.setLayout(null);
        this.add(Log);
        this.add(text);
        this.add(Bg);
        this.setIconImage(null);
        this.setVisible(true);
    }
    void Setup(){
        Bg.setIcon(home);

        Log.setBounds(185,300, 100,30);
        //Log.setOpaque(false);
        Log.addActionListener(this);
        Log.addKeyListener(this);
        Log.setFocusable(false);
        Log.setBorderPainted(false);
        Log.setBackground(new Color(134,69,23));

        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setBounds(80,200, 300,50);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBackground(Color.lightGray);
        text.setForeground(Color.BLACK);
    }
    void Loop_Music() throws LineUnavailableException, IOException {
        clip.open(m);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Log) {
            if(text.getText().equals("")){
                Log.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Please enter a name to proceed.", "ERROR!", JOptionPane.WARNING_MESSAGE);
            }else{
                clip.stop();
                clip.close();
                try {
                    m.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            Log.setEnabled(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            Log.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
