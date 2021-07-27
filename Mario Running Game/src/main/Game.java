package main;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game extends JFrame {

    public Game() {
        this.initUI();
    }

    private void initUI() {
        add(new Board());
        setResizable(true);
        setTitle("Mario");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(
                () -> {
                    Game ex = new Game();
                    ex.setVisible(true);
                }
        );
    }
}
