import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEvents extends JFrame{
    static int width = 800, height = 600;
    static int x = 0, y = 0;
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
                JLabel lab = new JLabel();
                if (e.getButton() != 3) {
                    lab.setBackground(Color.black);
                    x = e.getX();
                    y = e.getY();
                    lab.setText("X: " + x + ", Y: " + y);
                    lab.setBounds(x, y,120,20);
                    lab.setVisible(true);
                    pane.add(lab);
                } else {
                    pane.remove(e.getComponent().getComponentAt(e.getPoint()));
                    pane.repaint();
                }
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
