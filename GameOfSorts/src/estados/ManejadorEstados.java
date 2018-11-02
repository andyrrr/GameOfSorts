package estados;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class ManejadorEstados {

	private Map<String, Estado> mapa;
	private Estado currentState;

	/** Crea un mapa con todos los estados */
	public ManejadorEstados() {
		mapa = new HashMap<String, Estado>();
	}

	/** Agrega los estados al mapa */
	public void agregarEstado(Estado estado) {
		mapa.put(estado.getName().toUpperCase(), estado);
		estado.init();

		if (currentState == null) {
			estado.enter();
			currentState = estado;
		}
	}

	public void setEstado(String name) {
		Estado estado = mapa.get(name.toUpperCase());
		if (estado == null) {
			System.err.println("El estado <" + name + "> no existe.");
			System.exit(0);
		}
		currentState.exit();
		estado.enter();
		currentState = estado;
	}

	public void tick() {
		currentState.tick(this);
	}

	public void render(Graphics2D g) {
		currentState.render(g);
	}
}
