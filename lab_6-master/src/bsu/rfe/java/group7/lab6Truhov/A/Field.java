package bsu.rfe.java.group7.lab6Truhov.A;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class Field extends JPanel {

    private  boolean paused1;
    private boolean paused;
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });

    public Field() {
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball: balls) {
            ball.paint(canvas);
        }
    }

    public void addBall() {
        balls.add(new BouncingBall(this));
    }

    public synchronized void pause() {
        paused = true;
    }
    public  synchronized  void bigPause() {
        paused1 = true;
    }
    public synchronized void resume() {
        paused = false;
        paused1 = false;
        notifyAll();
    }
    public synchronized void canMove(BouncingBall ball) throws
            InterruptedException {
        if (paused1) {
            if (ball.getColor().getBlue() > ball.getColor().getGreen() + ball.getColor().getRed()) {
                wait();
            }
        }
        if (paused) {
            wait();
        }
    }
}
