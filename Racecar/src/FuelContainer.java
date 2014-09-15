import javax.swing.*;
import java.awt.*;

public class FuelContainer {

	int x, y;

	public FuelContainer(int x, int y) {
		this.x = x;
		this.y = y;
	}

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getFuelContainerImg(), x, y, null);
        //g2d.draw(getBounds());
    }

    public Image getFuelContainerImg() {
        ImageIcon ic = new ImageIcon("media/fuelcontainer.png");
        return ic.getImage();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getFuelContainerImg().getWidth(null), getFuelContainerImg().getHeight(null));
    }
}
