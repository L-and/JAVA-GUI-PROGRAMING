package ch09;
import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;

public class BoxLayoutDemo extends JFrame{

    public BoxLayoutDemo() {
        setTitle("box Layout Test");

        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl,BoxLayout.X_AXIS));

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");

        pnl.add(btn1);
        pnl.add(btn2);
        pnl.add(btn3);
        pnl.add(btn4);
        pnl.add(btn5);

        setSize(300,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BoxLayoutDemo();

    }

}