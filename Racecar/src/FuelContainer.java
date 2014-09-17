import javax.swing.*;
import java.awt.*;

public class FuelContainer {

	int x, y;
	//Bebbo: make FuelContainer repeat;
		private int startY;
		
	public FuelContainer(int x, int y) {
		this.x = x;
		this.y = y;
		//Bebbo: make FuelContainer repeat
		startY = y;
	}

    public void update() {
    	//y += 1; //edited by vlado - continues move
    	
    	//Bebbo: make FuelContainer repeat
    	checkOffScreen();
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
        return new Rectangle(x, y, 30, 30); //getFuelContainerImg().getWidth(null), getFuelContainerImg().getHeight(null));
    }
    
  //Bebbo: make FuelContainer repeat;
    public void checkOffScreen(){
		
		if (y >= 900) {
			y = startY;
		}
	}
}
