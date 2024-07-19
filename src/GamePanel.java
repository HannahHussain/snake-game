package src;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
        if (running) {
            
            // drawing an apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // drawing the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {  // head of the snake
                    g.setColor(new Color(27, 126, 60));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {  // body of the snake
                    g.setColor(new Color(4, 149, 52));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
        }
        else {
            gameOver(g);
        }
        

    }

    public void newApple() {
        // generate the coords of a new apple - every time game starts or an apple is eaten
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        // method to move the snake
        for (int i=bodyParts; i>0; i--)  {  // iterate through all body parts of snake
            x[i] = x[i-1];  // shifting all coordinates in array by 1
            y[i] = y[i-1];

        }

        switch(direction) {
            case 'U':  // Up
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D': // Down
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L': // Left
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R': // Right
                x[0] = x[0] + UNIT_SIZE;
                break;
        } 
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++; // score
            newApple();  // generate new apple

        }

    }

    public void checkCollisions() {
        // check if head of snake collides with its body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                // head collided with body
                running = false;  // game over
            }
        }

        // check if snake collided with any border
        if (x[0] < 0) {  // left border
            running = false;
        }

        if (x[0] > SCREEN_WIDTH) {  // right border
            running = false;
        }

        if (y[0] < 0) { // top border
            running = false;
        }

        if (y[0] > SCREEN_HEIGHT) {  // bottom border
            running = false;
        }

        if (!running) {
            timer.stop();
        }

    }

    public void gameOver(Graphics g) {
        // game over text
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, 75));
        // aliging text in centre of screen
        FontMetrics metrics = getFontMetrics(g.getFont());
        String msg = "GAME OVER!";
        int x = (SCREEN_WIDTH - metrics.stringWidth(msg))/2;
        int y = SCREEN_HEIGHT/2;
        g.drawString(msg, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }   
        repaint();    
    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:  // left arrow
                    if (direction != 'R') {  // limit user to 90 degree turns
                        direction = 'L';
                        break;
                    }

                case KeyEvent.VK_RIGHT:  // right arrow
                if (direction != 'L') {  // limit user to 90 degree turns
                    direction = 'R';
                    break;
                }

                case KeyEvent.VK_UP:  // up arrow
                    if (direction != 'D') {  // limit user to 90 degree turns
                        direction = 'U';
                        break;
                    }
                
                case KeyEvent.VK_DOWN:  // down arrow
                if (direction != 'U') {  // limit user to 90 degree turns
                    direction = 'D';
                    break;
                }
            }
        }
    }

}
