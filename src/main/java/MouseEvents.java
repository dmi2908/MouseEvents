import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEvents extends JFrame{
    static int width = 800, height = 600;
    public MouseEvents() {
        super("MouseEvents");
        createGUI();
    }

    public void createGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        setBackground(Color.white);
        JLayeredPane pane = new JLayeredPane();
        pane.setFocusable(true);
        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel lab = new JLabel("X: " + e.getX() + ", Y: " + e.getY());
                lab.setBackground(Color.black);
                lab.setBounds(e.getX(),e.getY(),120,20);
                lab.setVisible(true);
                pane.add(lab);
            }
        });
        setPreferredSize(new Dimension(width, height));
        setContentPane(pane);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                MouseEvents frame = new MouseEvents();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
