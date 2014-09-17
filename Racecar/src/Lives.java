import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Lives extends Player {

	public Lives(int x, int y) {
		super(x, y);
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(GetLivesImg(), x, y, null);
	}
	
	public Image GetLivesImg() {
		ImageIcon ic = new ImageIcon("media/skull.png");
		return ic.getImage();
	}
}