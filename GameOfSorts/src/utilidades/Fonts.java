package utilidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import me.gameOfSorts.Juego;

/** Clase para las letras y fuentes utilizadas */
public class Fonts {

	/** Dibuja el texto en la pantalla */
	public static void drawString(Graphics g, Font f, Color c, String text, int x, int y) {
		g.setColor(c);
		g.setFont(f);
		g.drawString(text, x, y);
	}

	/** Dibuja el texto en la pantalla */
	public static void drawString(Graphics g, Font f, Color c, String text) {
		FontMetrics fm = g.getFontMetrics(f);
		int x = ((Juego.ANCHO - fm.stringWidth(text)) / 2); // centro horizontal
		int y = ((Juego.ALTURA - fm.getHeight()) / 2) + fm.getAscent(); // centro vertical
		drawString(g, f, c, text, x, y);
	}

	/** Dibuja el texto en la pantalla */
	public static void drawString(Graphics g, Font f, Color c, String text, double x) {
		FontMetrics fm = g.getFontMetrics(f);
		int y = ((Juego.ALTURA - fm.getHeight()) / 2) + fm.getAscent(); // centro vertical
		drawString(g, f, c, text, (int) x, y);
	}

	/** Dibuja el texto en la pantalla */
	public static void drawString(Graphics g, Font f, Color c, String text, int y) {
		FontMetrics fm = g.getFontMetrics(f);
		int x = ((Juego.ANCHO - fm.stringWidth(text)) / 2); // centro horizontal
		drawString(g, f, c, text, x, y);
	}

}
