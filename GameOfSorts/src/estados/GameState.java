package estados;

import java.awt.Graphics2D;
import java.util.ArrayList;

import GoS.mundo.Tile;
import me.gameOfSorts.Juego;
import me.personajes.Jugador;
import me.personajes.Personaje;
import rendering.textures.Sprite;
import rendering.textures.SpriteSheet;
import rendering.textures.Texture;

/** Clase para la creación del juego */
public class GameState implements Estado {

	private ArrayList<Personaje> personajes;
	private ArrayList<Tile> bloques;
	Texture tex = new Texture("terreno - copia");
	SpriteSheet hoja = new SpriteSheet(tex, 64);

	/** Crea un array para agregar los personajes y crea el jugador principal */
	@Override
	public void init() {
		personajes = new ArrayList<Personaje>();
		bloques = new ArrayList<Tile>();
		new Jugador(new Sprite("deadpool - copia"), 100, 100, this);
		float x = 0;
		float y = Juego.ALTURA - 64;
		for (int i = 0; i < 18; i++) {
			bloques.add(new Tile(x, y, new Sprite(hoja, 1, 1)));
			x += 64;
		}
		bloques.add(new Tile(100, Juego.ALTURA - 300, new Sprite(hoja, 1, 1)));

	}

	@Override
	public void enter() {

	}

	/** Devuelve y actualiza las acciones de los personajes */
	@Override
	public void tick(ManejadorEstados estadoManager) {
		for (Personaje p : personajes) {
			p.tick();
		}

	}

	/** Con esto dibuja a los personajes creados */
	@Override
	public void render(Graphics2D g) {
		for (Personaje p : personajes) {
			p.render(g);
		}

		for (Tile bloque : bloques) {
			bloque.render(g);
		}

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	/** Devuelve el nombre del estado, en este caso, nivel1 */
	public String getName() {
		return "nivel1";
	}

	/** Se agregan los personajes al vector */
	public void addPersonaje(Personaje personaje) {
		personajes.add(personaje);

	}

	public ArrayList<Tile> getTiles() {
		return bloques;
	}

}
