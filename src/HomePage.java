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
    Font PixFont = loadFont();
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
        this.add(title);
        this.add(Bg);
        this.setIconImage(null);
        this.setVisible(true);
    }
    void Setup(){
        Bg.setIcon(home);

        Log.setBounds(185,300, 100,30);
        //Log.setOpaque(false);
        Log.addActionListener(this);
        Log.setBorderPainted(false);
        Log.setBackground(new Color(207, 167, 126));
        Log.setForeground(new Color(128, 90, 60));
        Log.setFont(PixFont);

        text.setFont(PixFont);
        text.setBounds(80,200, 300,50);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBackground(new Color(207, 167, 126));
        text.setForeground(Color.BLACK);
        text.addKeyListener(this);
        text.setForeground(new Color(134,69,23));

        title.setBounds(60,100, 500,50);
        title.setFont(PixFont);
        title.setForeground(new Color(134,69,23));

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
    private Font loadFont() {
        try {
            File fontFile = new File("src/fonts/PressStart2P-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) 16);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Press Start 2P", Font.ITALIC, (int) (float) 50);
        }
    }
}
