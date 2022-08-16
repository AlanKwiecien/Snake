package com.snake;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static JLabel score;
    public MainFrame() throws HeadlessException{

        JPanel scorePanel = new JPanel();

        score = new JLabel("Score: 0", SwingConstants.CENTER);

        scorePanel.add(score);

        this.add(new GameField());
        this.add(scorePanel, BorderLayout.NORTH);
        pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
