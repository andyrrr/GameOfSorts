package formacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Board extends JPanel implements ActionListener, Runnable {
        Dude p;
        public Image img;
        Timer time;
        int v = 172;
        Thread animator;
 
        boolean a = false;
        boolean done2 = false;
       
        public Board() {
                p = new Dude();
                addKeyListener(new AL());
                setFocusable(true);
                ImageIcon i = new ImageIcon("C:/Users/maxta/Pictures/fondoreal.png");
                img = i.getImage();
                time = new Timer(5, this);
                time.start();
        }
 
        public void actionPerformed(ActionEvent e) {
                p.move();
                repaint();
        }
 
        public void paint(Graphics g) {
                if (p.dy == 1 && done2 == false) {
                        done2 = true;
                        animator = new Thread(this);
                        animator.start();
                }
 
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
 
                if ((p.getX() - 590) % 2400 == 0)// p.getX() == 590 || p.getX() == 2990)
                        p.nx = 0;
                if ((p.getX() - 1790) % 2400 == 0)// p.getX() == 1790 || p.getX() == 4190)
                        p.nx2 = 0;
 
                g2d.drawImage(img, 685 - 1, 0, null);
                if (p.getX() > 590) {
                        g2d.drawImage(img, 685 - 1, 0, null);
                }
                g2d.drawImage(p.getImage(), p.left, v, null);
 
                if (p.getdx() == -1) {
                        g2d.drawImage(img, 685 - 1, 0, null);
                        g2d.drawImage(p.getImage(), p.left, v, null);
                }
        }
 
        private class AL extends KeyAdapter {
                public void keyReleased(KeyEvent e) {
                        p.keyReleased(e);
                }
 
                public void keyPressed(KeyEvent e) {
                        p.keyPressed(e);
                }
        }
 
        boolean h = false;
        boolean done = false;
 
        public void cycle() {
 
                if (h == false)
                        v--;
                if (v == 125)
                        h = true;
                if (h == true && v <= 172) {
                        v++;
                        if (v == 172) {
                                done = true;
                        }
                }
        }
 
        public void run() {
 
                long beforeTime, timeDiff, sleep;
 
                beforeTime = System.currentTimeMillis();
 
                while (done == false) {
 
                        cycle();
 
                        timeDiff = System.currentTimeMillis() - beforeTime;
                        sleep = 10 - timeDiff;
 
                        if (sleep < 0)
                                sleep = 2;
                        try {
                                Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                                System.out.println("interrupted");
                        }
 
                        beforeTime = System.currentTimeMillis();
                }
                done = false;
                h = false;
                done2 = false;
        }
 
}