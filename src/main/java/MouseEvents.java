import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;

public class MouseEvents extends JFrame{
    static int width = 800, height = 600;
    static Point startPoint;
    static JLayeredPane pane = new JLayeredPane();
    static boolean needToMove=false;
    public MouseEvents() {
        super("MouseEvents");
        createGUI();
    }

    public static void start(MouseEvent e){
        if (e.getButton() == 1) {
            startPoint = e.getPoint();
            needToMove =true;
        }
    }

    public static void stop(MouseEvent e){
        needToMove=false;
    }

    public static void move(MouseEvent e, JLabel lab){
        if (needToMove) {
            Point p = SwingUtilities.convertPoint(lab, e.getPoint(), pane);
            lab.setLocation(p.x - startPoint.x, p.y - startPoint.y);
        }
    }

    public void getMouseAdapters() {
        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel lab = new JLabel();
                if (e.getButton() != 3) {
                    lab.setBackground(Color.black);
                    lab.setText("X: " + e.getX() + ", Y: " + e.getY());
                    lab.setBounds(e.getX(), e.getY(),120,20);
                    lab.setVisible(true);
                    pane.add(lab);
                    lab.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            start(e);
                        }
                        public void mouseReleased(MouseEvent e) {
                            stop(e);
                        }

                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == 3) {
                                pane.remove(e.getComponent().getComponentAt(e.getPoint()));
                                pane.repaint();
                            }
                        }
                    });
                    lab.addMouseMotionListener(new MouseMotionAdapter() {
                        public void mouseDragged(MouseEvent e) {
                            move(e, lab);
                        }
                    });
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                pane.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        setBackground(Color.white);
        pane.setFocusable(true);
        getMouseAdapters();
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
