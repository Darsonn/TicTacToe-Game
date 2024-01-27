package pl.darsonn;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Image image = Toolkit.getDefaultToolkit().createImage("src/main/resources/icon.png");

        JFrame jFrame = new JFrame("TicTacToe Game");
        jFrame.setContentPane(new TicTacToe().panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.put("Button.disabledText", new ColorUIResource(Color.decode("#F84AA7")));
        jFrame.setIconImage(image);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
