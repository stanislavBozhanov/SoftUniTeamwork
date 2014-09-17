<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;

public class Road{
	
	int x, y;
	
	public Road(int x ,int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		y += 1;
	}
	
	public void draw(Graphics g2d){
		g2d.drawImage(getroadImg(), x, y, null);
	
	}
	
	public Image getroadImg(){
		ImageIcon roadIcon = new ImageIcon("media/Road.jpg");
		return roadIcon.getImage();
	}

}
=======
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
>>>>>>> 0c744f60eebbfbc6dcfd7a67c1eca2cf0388f572
