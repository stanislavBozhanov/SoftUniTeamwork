import javax.swing.*;

import java.awt.*;

public class MovingObstacles {

	int x, y;
	boolean rightdir;
	private int startY;
	
	public MovingObstacles(int x, int y, boolean rightdir) {
		this.x = x;
		this.y = y;
		this.rightdir = true;
		
		this.startY = y;
	}

	public void update() {
		checkOffScreen();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getMovingObstacleImg(), x, y, null);
				
		//g2d.draw(getBounds());
	}

	public Image getMovingObstacleImg() {

		ImageIcon ic = new ImageIcon("media/movingobstacle.png");
		return ic.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15); //getHoleObstacleImg().SCALE_DEFAULT,getHoleObstacleImg().SCALE_DEFAULT);
    }
	
	
    public void checkOffScreen(){
		
		if (y >= 950) {
			GameFrame.removeMovingObstacles(this);
		}
	}
	
	
}
