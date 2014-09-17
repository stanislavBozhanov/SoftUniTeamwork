
import javax.swing.*;
import java.awt.*;

public class FuelMeter {
    int x, y;

    int fuel = 100;

    public int getTempFuel() {
        return tempFuel;
    }

    int tempFuel = fuel;
    public void setFuel(int f) {
        this.tempFuel = f;
    }

    public void setTempFuel(int tf) {
        this.tempFuel -= tf;
    }

    public FuelMeter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

    }

    public void draw (Graphics2D g2d) {
        g2d.setColor(Color.orange);

        switch (this.tempFuel) {
            case 100 : g2d.fillRect(x, y, 100, 20);
                break;
            case 90 : g2d.fillRect(x, y, 90, 20);
                break;
            case 80 : g2d.fillRect(x, y, 80, 20);
                break;
            case 70 : g2d.fillRect(x, y, 70, 20);
                break;
            case 60 : g2d.fillRect(x, y, 60, 20);
                break;
            case 50 : g2d.fillRect(x, y, 50, 20);
                break;
            case 40 : g2d.fillRect(x, y, 40, 20);
                break;
            case 30 : g2d.fillRect(x, y, 30, 20);
                break;
            case 20 : g2d.fillRect(x, y, 20, 20);
                break;
            case 10 : g2d.fillRect(x, y, 10, 20);
                break;
            case 0 : g2d.fillRect(x, y, 0, 20);
                break;
        }

        g2d.drawImage(getFuelContainerImg(), x, y - 25, null);
    }

    public Image getFuelContainerImg() {
        ImageIcon ic = new ImageIcon("media/fuelmeter.png");
        return ic.getImage();
    }

}

