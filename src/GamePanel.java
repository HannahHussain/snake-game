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
    static final int UNIT_SIZE = 20;
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
        newApple();  // creates a new apple on the screen
        running = true;
        timer = new Timer(DELAY, this); // dictates how fast the game will be running
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // drawing a grid:
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);  // lines drawn on x axis
            g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i* UNIT_SIZE);  // lines drawn on y axis
        }
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

    }

    public void newApple() {
        // generate the coords of a new apple - every time game starts or an apple is eaten
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;


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
