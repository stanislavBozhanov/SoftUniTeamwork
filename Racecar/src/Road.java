import javax.swing.*;
import java.awt.*;

public class Road{
	
	private int x, y;
    private int startY;
    private int gameSpeed;


    public Road(int x ,int y){
		this.x = x;
		this.y = y;

        this.startY = y;
	}

    public void setGameSpeed(int speed) {
        this.gameSpeed = speed;
    }

	public void update(){
		y += gameSpeed;
        checkOffScreen();
	}
	
	public void draw(Graphics g2d){
		g2d.drawImage(getroadImg(), x, y, null);
	}
	
	public Image getroadImg(){
		ImageIcon roadIcon = new ImageIcon("media/road.png");
		return roadIcon.getImage();
	}

    public void checkOffScreen() {
        if (y >= 0)
            this.y = startY;
    }

}
