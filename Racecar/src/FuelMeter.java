/*
Created by Velio
 */

import javax.swing.*;
import java.awt.*;

public class FuelMeter {
    int x, y;

    public FuelMeter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw (Graphics2D g2d) {
        g2d.setColor(Color.orange);
        g2d.fillRect(x, y, 100, 20);
        g2d.drawImage(getFuelContainerImg(), x, y - 25, null);
    }

    public Image getFuelContainerImg() {
        ImageIcon ic = new ImageIcon("media/fuelmeter.png");
        return ic.getImage();
    }

    
}
