import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Game_Frame extends JFrame implements ActionListener {
    Font font = loadFont();
    boolean swap = true;
    JButton [][] tik = new JButton[3][3];
    Game_Frame() {
        this.setLayout(new GridLayout(3, 3));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        for (int i = 0; i < tik.length; i++) {
            for (int j = 0; j < tik[i].length; j++) {
                tik[i][j] = new JButton();
                tik[i][j].setPreferredSize(new Dimension(200, 200));
                tik[i][j].addActionListener(this);
                tik[i][j].setFont(font);
                tik[i][j].setOpaque(true);
                tik[i][j].setForeground(Color.WHITE);
                tik[i][j].setFocusable(false);
                tik[i][j].setBackground(Color.DARK_GRAY);
                this.add(tik[i][j]);
            }
        }
        setAll();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    void setAll() {
        for (JButton[] jButtons : tik) {
            for (JButton jButton : jButtons) {
                jButton.setText("");
                jButton.setEnabled(true);
            }
        }
        swap = true;
    }
    private void makeRandomAIMove() {
        int emptyCells = 0;
        for (int i = 0; i < tik.length; i++) {
            for (int j = 0; j < tik[i].length; j++) {
                if (tik[i][j].getText().isEmpty()) {
                    emptyCells++;
                }
            }
        }

        if (emptyCells > 0) {
            int randomIndex = (int) (Math.random() * emptyCells) + 1;
            int count = 0;
            for (JButton[] jButtons : tik) {
                for (int j = 0; j < jButtons.length; j++) {
                    if (jButtons[j].getText().isEmpty()) {
                        count++;
                        if (count == randomIndex) {
                            jButtons[j].setText("O");
                            jButtons[j].setEnabled(false);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText("X");
            clickedButton.setEnabled(false);
            //clickedButton.setText("O");
        }
        if (!checkGameOver()) {
            makeRandomAIMove();
            checkGameOver();
        }
        /*
        code for real player
            if (checkWin("X")) {
                JOptionPane.showMessageDialog(this, "Player X wins!");
                setAll();
            } else if (checkWin("O")) {
                JOptionPane.showMessageDialog(this, "Player O wins!");
                setAll();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                setAll();
            }
         */
    }
    private boolean checkGameOver() {
        for (int i = 0; i < tik.length; i++) {
            if (!tik[i][0].getText().isEmpty() &&
                    tik[i][0].getText().equals(tik[i][1].getText()) &&
                    tik[i][0].getText().equals(tik[i][2].getText())) {
                JOptionPane.showMessageDialog(this, tik[i][0].getText() + " wins!");
                setAll();
                return true;
            }
            if (!tik[0][i].getText().isEmpty() &&
                    tik[0][i].getText().equals(tik[1][i].getText()) &&
                    tik[0][i].getText().equals(tik[2][i].getText())) {
                JOptionPane.showMessageDialog(this, tik[0][i].getText() + " wins!");
                setAll();
                return true;
            }
        }
        if (!tik[0][0].getText().isEmpty() &&
                tik[0][0].getText().equals(tik[1][1].getText()) &&
                tik[0][0].getText().equals(tik[2][2].getText())) {
            JOptionPane.showMessageDialog(this, tik[0][0].getText() + " wins!");
            setAll();
            return true;
        }

        if (!tik[0][2].getText().isEmpty() &&
                tik[0][2].getText().equals(tik[1][1].getText()) &&
                tik[0][2].getText().equals(tik[2][0].getText())) {
            JOptionPane.showMessageDialog(this, tik[0][2].getText() + " wins!");
            setAll();
            return true;
        }
        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            setAll();
            return true;
        }

        return false;
    }
    /*
    private boolean checkWin(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (tik[i][0].getText().equals(symbol) && tik[i][1].getText().equals(symbol) && tik[i][2].getText().equals(symbol)) {
                return true;
            }
            if (tik[0][i].getText().equals(symbol) && tik[1][i].getText().equals(symbol) && tik[2][i].getText().equals(symbol)) {
                return true;
            }
        }
        if (tik[0][0].getText().equals(symbol) && tik[1][1].getText().equals(symbol) && tik[2][2].getText().equals(symbol)) {
            return true;
        }
        return tik[0][2].getText().equals(symbol) && tik[1][1].getText().equals(symbol) && tik[2][0].getText().equals(symbol);
    }
code for real player
     */
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tik[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
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
