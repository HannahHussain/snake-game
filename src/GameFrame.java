package src;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();  // fit the Jframe around all the components being added to it
        this.setVisible(true);
        this.setLocationRelativeTo(null);  // window appears in centre of screen
    }

}
