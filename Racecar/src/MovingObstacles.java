import javax.swing.*;

import java.awt.*;


public class MovingObstacles extends HoleObstacle {

	boolean rightdir;

	public MovingObstacles(int x, int y, boolean rightdir) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.rightdir = true;

	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getMovingObstacleImg(), x, y, null);
		//g2d.draw(getBounds());
	}

	public Image getMovingObstacleImg() {

		ImageIcon ic = new ImageIcon("media/movingobstacle4.png");
		return ic.getImage();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 100); //getHoleObstacleImg().SCALE_DEFAULT,getHoleObstacleImg().SCALE_DEFAULT);
    }
	
	@Override
    public void checkOffScreen(){
		if (y >= 950) {
			GameFrame.removeMovingObstacles(this);
		}
	}
	
	
}
