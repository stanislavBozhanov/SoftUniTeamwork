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
		this.startY = y;
	}

	public void update() {
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
		return new Rectangle(x, y, 15, 15); //getHoleObstacleImg().SCALE_DEFAULT,getHoleObstacleImg().SCALE_DEFAULT);
        //VELIO: 15, 15 намаля малко размера на дупката, който отчита сблъсък. Иначе при по-трудни нива става прекалено трудно да се избегне удар.
    }
	
	//Bebbo: make holes repeat;
    //Velio: when all obstacles are off the screen - level is up, gameSpeed++
    public void checkOffScreen(){
		
		if (y >= 950) {
			GameFrame.removeHoleObstacle(this);
		}
	}

}
