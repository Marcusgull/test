package test;
import java.awt.*;

public class HumanPaddle implements Paddle {

    private double xVelocity, yVelocity;
    private boolean upAccel, downAccel;

    private double yPosition;
    private int xPosition;
    private int player;

    private final double GRAVITY = 0.94;


    public HumanPaddle (int player){
        upAccel = false; downAccel = false;
        yPosition = 210; yVelocity = 0;

        if (player == 1) {
            xPosition = 20;
        }
        else {
            xPosition = 660;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition,(int)yPosition, 20, 80);
    }

    public void move() {
        if (upAccel){
            yVelocity -= 2;
        }
        else if (downAccel){
            yVelocity += 2;
        }
        else if (!upAccel && !downAccel){
            yVelocity *= GRAVITY;
        }

        if (yVelocity >= 5) {
            yVelocity = 5;
        }
        else if (yVelocity <= -5) {
            yVelocity = -5;
        }

        yPosition += yVelocity;

        if (yPosition < 0) {
            yPosition = 0;
        }
        if (yPosition > 420) {
            yPosition = 420;
        }
    }

    public void setUpAccel(boolean input){
        upAccel = input;
    }

    public void setDownAccel(boolean input){
        downAccel = input;
    }

    public int getY() {
        return (int)yPosition;
    }
}
