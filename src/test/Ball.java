package test;

import java.awt.*;

public class Ball{

    private double xVelocity, yVelocity;
    private double xPosition, yPosition;

    public Ball() {
        this.xPosition = 350;
        this.yPosition = 250;
        this.xVelocity = -1;
        this.yVelocity = 1;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval((int)xPosition-10,(int)yPosition-10,20,20);
    }

    public void checkPaddleCollision(Paddle paddlePlayer, Paddle paddleAI) {
        if (xPosition <= 50){
            if(yPosition >= paddlePlayer.getY() && yPosition <= paddlePlayer.getY() + 80)
                xVelocity = -xVelocity;
        } else if (xPosition >= 650) {
            if(yPosition >= paddleAI.getY() && yPosition <= paddleAI.getY() + 80)
                xVelocity = -xVelocity;
        }
    }

    public void move() {
        xPosition += xVelocity;
        yPosition += yVelocity;

        if(yPosition < 10)
            yVelocity = -yVelocity;
        if(yPosition > 490)
            yVelocity = -yVelocity;
    }

    public int getX() {
        return (int)xPosition;
    }

    public int getY() {
        return(int)yPosition;
    }
}
