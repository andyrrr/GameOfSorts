package estados;

import java.awt.Graphics2D;

public interface Estado {

	public void init();

	public void enter();

	public void tick(ManejadorEstados estadoManager);

	public void render(Graphics2D g);

	public void exit();

	public String getName();

}
