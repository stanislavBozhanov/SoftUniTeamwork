import javax.swing.*;
import java.awt.*;

public class HoleObstacle{

	int x, y;
	//Bebbo: make holes repeat;
	private int startY;
	
	public HoleObstacle(int x, int y) {
		this.x = x;
		this.y = y;
		
		//Bebbo: make holes repeat
		startY = y;
	}

	public void update() {
		y += 1; //edited by vlado - continues move
		//Bebbo: make holes repeat
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getHoleObstacleImg(), x, y, null);
		//g2d.draw(getBounds());
	}

	public Image getHoleObstacleImg() {

		ImageIcon ic = new ImageIcon("media/holeobstacle.png");
		return ic.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getHoleObstacleImg().SCALE_DEFAULT,getHoleObstacleImg().SCALE_DEFAULT);
	}
	
	//Bebbo: make holes repeat;
    public void checkOffScreen(){
		
		if (y >= 680) {
			y = startY;
		}
	}

}
