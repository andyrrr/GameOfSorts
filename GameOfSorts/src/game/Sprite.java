package game;
import java.awt.Image;

public class Sprite {

    private boolean visible;
    private Image image;
    protected int x;
    protected int y;
    protected boolean muriendo;
    protected int dY;
    protected int dX;

    public Sprite() {
    
        visible = true;
    }

    public void matar() {
    
        visible = false;
    }

    public boolean isVisible() {
    
        return visible;
    }

    protected void setVisible(boolean visible) {
    
        this.visible = visible;
    }

    public void setImage(Image image) {
    
        this.image = image;
    }

    public Image getImage() {
    
        return image;
    }

    public void setX(int x) {
    
        this.x = x;
    }

    public void setY(int y) {
    
        this.y = y;
    }

    public int getY() {
    
        return y;
    }

    public int getX() {
    
        return x;
    }

    public void setMuriendo(boolean muriendo) {
    
        this.muriendo = muriendo;
    }

    public boolean isMuriendo() {
    
        return this.muriendo;
    }
}