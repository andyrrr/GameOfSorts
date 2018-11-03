package game;
import javax.swing.ImageIcon;

public class Disparo extends Sprite {

	private final String shotImg = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\PixelArt - copia - copia.png";
	private final int H_SPACE = 6;
	private final int V_SPACE = 1;

	public Disparo() {
	}

	public Disparo(int x, int y) {

		initDisparo(x, y);
	}

	private void initDisparo(int x, int y) {

		ImageIcon ii = new ImageIcon(shotImg);
		setImage(ii.getImage());

		setX(x + H_SPACE);
		setY(y - V_SPACE);
	}
}