package com.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {

    private List<Point> body;
    private Point end;
    private Direction direction;
    static Random random = new Random();
    int randomX = random.nextInt(2, 5);
    int randomY = random.nextInt(GameField.Y_FIELDS);

    public Snake() {
        body = new ArrayList<>();
        end = new Point();
        direction = Direction.R;

        body.add(new Point(randomX, randomY));
        body.add(new Point(randomX - 1, randomY));
        body.add(new Point(randomX - 2, randomY));
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 000));

        for (Point point : getTail()) {
            g.fillRect(point.x * GameField.CELL_SIZE, point.y * GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
        }

        g.setColor(new Color(0, 125, 0));
        g.fillRect(getHead().x * GameField.CELL_SIZE, getHead().y * GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }

    public void move() {
        end.setLocation(body.get(body.size() - 1));
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setLocation(body.get(i - 1));
        }

        switch (direction) {
            case U -> getHead().y--;
            case D -> getHead().y++;
            case L -> getHead().x--;
            case R -> getHead().x++;
        }
    }

    public boolean eatApple(Apple apple) {
        if (getHead().equals(apple)) {
            body.add(new Point(end));
            MainFrame.score.setText("Score: " + (body.size() - 3));
            return true;
        }

        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getHead() {
        return body.get(0);
    }

    public List<Point> getTail() {
        return body.subList(1, body.size());
    }

    public boolean isCollision() {
        Point head = getHead();

        for (Point point : getTail()) {
            if (head.equals(point))
                return true;
        }

        return head.x < 0 || head.x >= GameField.X_FIELDS || head.y < 0 || head.y >= GameField.Y_FIELDS;
    }
}

enum Direction {
    U, D, L, R;
}
