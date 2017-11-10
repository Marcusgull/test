package test;
import java.awt.*;

public class AIPaddle implements Paddle {

    private double xVelocity, yVelocity;
    private boolean upAccel, downAccel;

    private double yPosition;
    private int xPosition;
    private int player;

    private final double GRAVITY = 0.94;
    private Ball ball;


    public AIPaddle (int player, Ball b){
        upAccel = false;
        downAccel = false;

        ball = b;
        yPosition = 210;
        yVelocity = 0;

        if (player == 1) {
            xPosition = 20;
        }
        else {
            xPosition = 660;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, (int)yPosition, 20, 80);
    }

    public void move() {
        yPosition = ball.getY() - 40;

        if (yPosition < 0) {
            yPosition = 0;
        }

        if (yPosition > 420) {
            yPosition = 420;
        }
    }

    public int getY() {
        return (int)yPosition;
    }
}