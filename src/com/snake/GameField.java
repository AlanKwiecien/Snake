package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel {

    public static final int CELL_SIZE = 15;
    public static final int X_FIELDS = 600/CELL_SIZE;
    public static final int Y_FIELDS = 600/CELL_SIZE;
    private Snake snake = new Snake();
    private Apple apple = new Apple();
    private boolean gameOver = false;

    private MainTimer mainTimer = new MainTimer();

    public GameField() {
        setPreferredSize(new Dimension(X_FIELDS * CELL_SIZE, Y_FIELDS * CELL_SIZE));

        mainTimer.start();

        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.GRAY);
        apple.draw(g);
        snake.draw(g);
    }


    private class MainTimer extends Timer {
        public static int delay = 90;

        public MainTimer() {
            super(delay, e -> {
                if (!gameOver) {
                    snake.move();

                    if (snake.eatApple(apple)) {
                        apple = new Apple();
                        mainTimer.setDelay(delay -= 3);
                    }

                    if (snake.isCollision()) {
                        gameOver = true;

                        int restart = JOptionPane.showConfirmDialog(getParent(), "Gamve Over\n" + MainFrame.score.getText() + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (restart == JOptionPane.YES_OPTION){
                            MainFrame.score.setText("Score: 0");
                            mainTimer.setDelay(delay=90);
                            apple = new Apple();
                            snake = new Snake();
                            gameOver = false;
                        } else if (restart == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    }
                    repaint();
                }
            });
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.D) {
                        snake.setDirection(Direction.U);
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.U) {
                        snake.setDirection(Direction.D);
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.R) {
                        snake.setDirection(Direction.L);
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.L) {
                        snake.setDirection(Direction.R);
                    }
                    break;
            }
        }
    }
}
