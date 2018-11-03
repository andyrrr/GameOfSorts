package formacion;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
public class Frame {
 
        public Frame() throws FileNotFoundException, IOException{
                JFrame frame = new JFrame();
                frame.add(new side());
                frame.setTitle("2-D Test Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900,500);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                Oleada o1 = new Oleada(20);
                
        }
        public static void main(String[] args) throws FileNotFoundException, IOException{
                new Frame();
        }
}