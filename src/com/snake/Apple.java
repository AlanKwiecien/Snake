package com.snake;

import java.awt.*;
import java.util.Random;

public class Apple extends Point {

    static Random random = new Random();

    public Apple(){
        super(random.nextInt(GameField.X_FIELDS),random.nextInt(GameField.Y_FIELDS));
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x*GameField.CELL_SIZE, y*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }
}
