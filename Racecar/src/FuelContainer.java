import javax.swing.*;
import java.awt.*;

public class FuelContainer extends Entity{

    public FuelContainer(int x, int y) {
        super(x, y);
    }

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getFuelContainerImg(), x, y, null);
        //g2d.draw(getBounds());
    }

    public Image getFuelContainerImg() {
        ImageIcon ic = new ImageIcon("Racecar/media/fuelcontainer.png");
        return ic.getImage();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getFuelContainerImg().getWidth(null), getFuelContainerImg().getHeight(null));
    }
}
