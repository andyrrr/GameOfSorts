package formacion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class dibujarDragon {
	public Image img;
    int x,y;

	public dibujarDragon(int x, int y) {
		ImageIcon i = new ImageIcon("C:/Users/maxta/Pictures/fondoreal.png");
        img = i.getImage();
        this.x=x;
        this.y=y;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, x, y, null);
		
	}
}
