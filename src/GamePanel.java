package src;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;


    final int x[] = new int[GAME_UNITS];  // holds all the x coordinates of the body of the snake
    final int y[] = new int[GAME_UNITS];  // holds all the y coordinates

    int bodyParts = 6;  // starting number of body parts of the snake
    int applesEaten = 0;

    int appleX;  // x coordinate of where the apple is - appears randomly every time the snake eats an apple
    int appleY; // y coordinate of the apples position

    char direction = 'R';  // snake begins by moving to the right

    boolean running = false;

    Timer timer;
    Random random;


    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();
    }

    public void startGame() {

    }

    public void paintComponent(Graphics g) {


    }

    public void draw(Graphics g) {

    }

    public void newApple() {
        
    }

    public void move() {

    }

    public void checkApple() {


    }

    public void checkCollisions() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }

}
