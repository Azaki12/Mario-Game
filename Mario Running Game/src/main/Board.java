package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import jdk.javadoc.internal.tool.Start;

public class Board extends JPanel implements ActionListener {

    private static Graphics graph;
    private static final int XAXIS = 600;
    private static final int YAXIS = 240;
    private Timer timer;
    private static Mario mario;
    private static ArrayList<Goomba> goombaList;
    private static boolean inGame;
    private static boolean beforeGame;
    private static boolean playedBefore;
    private int time;
    private static int score;
    private double goombaSpeed = 3.5;


    private int[] rnd = {300, XAXIS};
    JLabel label = new JLabel();
    ImageIcon clouds = new ImageIcon("src\\resources\\clouds.gif");
    ImageIcon pixels = new ImageIcon("src\\resources\\Logo Black.png");
    ImageIcon backGround = new ImageIcon("src\\resources\\bush_cloud_ingame.png");

    Board() {
        playedBefore = false;
        initializeBoard();

    }

    private void initializeBoard() {
        setBackground(Color.red);


        addKeyListener(new KeyManager());
        setFocusable(true);
        inGame = false;
        playedBefore = false;
        beforeGame = true;
        setPreferredSize(new Dimension(XAXIS, 300));
        newGoombaList();
        mario = new Mario(50, YAXIS);
        timer = new Timer(1, this);
        timer.start();
        time = 0;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(backGround.getImage(), 0, 0, this);
        g.drawImage(clouds.getImage(), 0, 0, this);

        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        g.setColor(Color.red);
        g.drawString("Score: " + score, 0, 20);
        g.drawImage(mario.getImage(), (int) mario.getX(), (int) mario.getY(), this);

        g.setColor(Color.black);

        g.drawImage(pixels.getImage(), 490, -30, this);

        if (beforeGame) {
            drawGameScreen(g, true);
        }
        if (inGame) {
            drawGame(g);
        }
        if (playedBefore && !inGame) {
            drawGameScreen(g, false);
        }
    }

    private void drawGame(Graphics g) {

        Iterator goombaIterator = goombaList.iterator();

        while (goombaIterator.hasNext()) {
            Goomba goomba = (Goomba) goombaIterator.next();
            g.drawImage(goomba.getImage(), (int) goomba.getX(), (int) goomba.getY(), this);
        }
    }

    private void drawGameScreen(Graphics g, boolean beforeGame) {
        Font font = new Font("Helvetica", 1, 25);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g.setColor(Color.BLACK);
        g.setFont(font);

        String line;
        String secondLine = null;
        int centerY = 3;
        if (beforeGame) {
            centerY = 2;
        }

        if (beforeGame) {
            line = "Press SPACE to start";
        } else {
            line = "GAME OVER";
            secondLine = "Press SPACE to restart";
        }

        g.drawString(line, (XAXIS - fontMetrics.stringWidth(line)) / 2, 300 / centerY);
        if (!beforeGame) {
            g.drawString(secondLine, (XAXIS - fontMetrics.stringWidth(secondLine)) / 2, (300 / 3) * 2 - 20);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (beforeGame) {
            startGame();
        } else {
            if (inGame) {
                updateGoombaList();
                updateTRex();
                time++;
                if (time % 20 == 0) {
                    score++;
                    goombaSpeed += 0.06;
                }
                checkCollision();
            } else {
                gameOver();
            }
        }

        repaint();

    }

    private void checkCollision() {
        Rectangle marioBounds = mario.getBounds();

        for (Goomba goomba : goombaList) {
            Rectangle goombaBounds = goomba.getBounds();
            if (marioBounds.intersects(goombaBounds)) {
                goomba.setVisible(false);
                inGame = false;
                playedBefore = true;
            }
        }
    }

    private void updateGoombaList() {
        Random random = new Random();
        int rndNum = random.nextInt(2);
        int currentFrequency = rnd[rndNum];

        if (goombaList.isEmpty()) {
            newGoombaList();
        }

        if (time % currentFrequency == 0 && score > 20 && goombaList.get(goombaList.size() - 1).getX() <= 450) {
            goombaList.add(new Goomba(XAXIS, YAXIS));
        }

        for (int i = 0; i < goombaList.size(); i++) {
            Goomba goomba = goombaList.get(i);

            if (goomba.getX() < 0 - goomba.getBounds().getWidth()) {
                goombaList.remove(goomba);
            } else if (goomba.isVisible() && time % 2 == 0) {
                goomba.move(goombaSpeed);
            }
        }
    }


    private void newGoombaList() {
        goombaList = new ArrayList<>();

        goombaList.add(new Goomba(XAXIS, YAXIS));
    }


    private void updateTRex() {
        if (mario.isVisible()) {
            mario.getInput();
            mario.updatePosition();
        }
    }

    private void startGame() {
        boolean keyPressed = KeyManager.getKey();

        if (keyPressed) {
            beforeGame = false;
            inGame = true;
        }
    }

    private void gameOver() {
        boolean keyPressed = KeyManager.getKey();

        if (keyPressed) {
            setDefault();
        }
    }

    private void setDefault() {
        timer.stop();
        score = 0;
        goombaSpeed = 3.5;

        initializeBoard();
    }
}
