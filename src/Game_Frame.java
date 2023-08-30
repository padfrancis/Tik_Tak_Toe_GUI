import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Game_Frame extends JFrame implements ActionListener {
    Font font = loadFont();
    JPanel pan = new JPanel();
    boolean swap = true;
    JButton [][] tik = new JButton[3][3];
    Game_Frame() {
        SetAll();
        this.setLayout(new GridLayout(3,3));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        for (int i = 0; i < tik.length; i++) {
            for (int j = 0; j < tik[i].length; j++) {
                tik[i][j] = new JButton();
                tik[i][j].setPreferredSize(new Dimension(200,200));
                tik[i][j].addActionListener(this);
                tik[i][j].setFont(font);
                tik[i][j].setOpaque(true);
                tik[i][j].setFocusable(false);
                tik[i][j].setBackground(Color.WHITE);
                this.add(tik[i][j]);
            }
        }
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    void SetAll(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().isEmpty()) {
            if (swap) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            swap = !swap;
        }
    }
    private Font loadFont() {
        try {
            File fontFile = new File("src/fonts/PressStart2P-Regular.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) 100);
        } catch (FontFormatException | IOException e) {
            e.fillInStackTrace();
            return new Font("Press Start 2P", Font.ITALIC, (int) (float) 50);
        }
    }
}
