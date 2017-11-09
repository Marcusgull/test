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

    // Skal det være mer enn en ball?
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
        this.paddlePlayer = new HumanPaddle(1);
        this.paddleAI = new AIPaddle(2, this.ball);
        this.ball = new Ball();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        if(this.ball.getX() < -10 || this.ball.getX() > 710){
            g.setColor(Color.red);
            g.drawString("Game Over!", 350,250);
        } else {
            this.ball.draw(g);
            this.paddlePlayer.draw(g);
            this.paddleAI.draw(g);
        }

        this.paddlePlayer.draw(g);
        this.ball.draw(g);
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    @Override
    public void run() {
        // Main game-loop
        for(;;){
            if(gameStarted) {
                this.paddlePlayer.move();
                this.paddleAI.move();
                this.ball.move();
                this.ball.checkPaddlecollision(
                        this.paddlePlayer,
                        this.paddleAI
                );
            }

            this.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()== KeyEvent.VK_UP) {
            this.paddlePlayer.setUpAccel(true);
        } else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            this.paddlePlayer.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
        }

    }

    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()== KeyEvent.VK_UP) {
            this.paddlePlayer.setUpAccel(false);
        } else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
            this.paddlePlayer.setDownAccel(true);
        }

    }

    public void keyTyped(KeyEvent e) {

    }
}
