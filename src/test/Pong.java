package test;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener{

    private final int WIDTH = 700, HEIGHT = 500;
    private Thread thread;
    private boolean gameStarted;

    private HumanPaddle paddlePlayer;
    private AIPaddle paddleAI;

    private Ball ball;


    public void init(){
        // Set window size and add keyListener
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);

        // Instantiate thread from this and start
        thread = new Thread(this);
        thread.start();
        gameStarted = false;

        // Instantiate player, AI and ball
        paddlePlayer = new HumanPaddle(1);
        paddleAI = new AIPaddle(2, ball);
        ball = new Ball();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        if (ball.getX() < -10 || ball.getX() > 710){
            g.setColor(Color.red);
            g.drawString("Game Over!", 350,250);
        }
        else {
            ball.draw(g);
            paddlePlayer.draw(g);
            paddleAI.draw(g);
        }

        paddlePlayer.draw(g);
        ball.draw(g);
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    @Override
    public void run() {
        // Main game-loop
        for(;;){
            if (gameStarted) {
                paddlePlayer.move();
                paddleAI.move();
                ball.move();
                ball.checkPaddleCollision(
                        paddlePlayer,
                        paddleAI
                );
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void keyPressed(KeyEvent e) {

        System.out.println("Key pressed");

        if (e.getKeyCode()== KeyEvent.VK_UP) {
            System.out.println("VK_UP \n");
            paddlePlayer.setUpAccel(true);
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            paddlePlayer.setDownAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
        }

    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode()== KeyEvent.VK_UP) {
            paddlePlayer.setUpAccel(false);
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            paddlePlayer.setDownAccel(true);
        }

    }

    public void keyTyped(KeyEvent e) {

    }
}
