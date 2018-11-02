package rendering.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.gameOfSorts.Juego;
import utilidades.Fonts;

@SuppressWarnings("serial")

/** Clase para los botones que se van a usar */
public class Button extends Rectangle {

	private Font font, selectedFont; // Se crea un objeto de la clase font de Java
	private Color color, selectedColor; // Se asigna el color a la fuente

	private boolean selected; // botón seleccionado
	private String text; // texto
	private int textY; // posición en Y del texto

	/** Constructor del botón que vamos a usar */
	public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
		this.text = text;
		this.textY = textY;
		this.font = font;
		this.selectedFont = selectedFont;
		this.color = color;
		this.selectedColor = selectedColor;

	}

	/** Hace un set del botón seleccionado */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/** Dibuja el botón */
	public void render(Graphics g) {
		/** Si se seleccionó */
		if (selected) {
			Fonts.drawString(g, selectedFont, selectedColor, text, textY);
		} else {
			Fonts.drawString(g, font, color, text, textY);
		}
		FontMetrics fm = g.getFontMetrics();
		this.x = (Juego.ANCHO - fm.stringWidth(text)) / 2;
		this.y = textY - fm.getHeight();
		this.width = fm.stringWidth(text);
		this.height = fm.getHeight();
		g.drawRect(x, y, width, height);
	}

}
