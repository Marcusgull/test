package test;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener{
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    boolean gameStarted;
    public void init(){
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        thread = new Thread(this);
        thread.start();
        gameStarted = false;
        b1 = new Ball();
        p2 = new AIPaddle(2, b1);
    }
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        if(b1.getX() < -10 || b1.getX() > 710){
            g.setColor(Color.red);
            g.drawString("Game Over!", 350,250);
        } else {
            b1.draw(g);
            p1.draw(g);
            p2.draw(g);
        }
        p1.draw(g);
        b1.draw(g);
    }
    public void update(Graphics g) {
        paint(g);
    }
    @Override
    public void run() {
        for(;;){
            if(gameStarted) {

                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddlecollision(p1, p2);
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
        if(e.getKeyCode()== KeyEvent.VK_UP) {
            p1.setUpAccel(true);
        } else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
        gameStarted = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        } else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
    }
    public void keyTyped(KeyEvent e) {

    }
}
