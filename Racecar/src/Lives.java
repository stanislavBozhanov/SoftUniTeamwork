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
        g2d.drawImage(getLivesImg(), x, y, null);
    }
	
	public static Image getSkullImg() {
		ImageIcon ic = new ImageIcon("media/skull.png");
		return ic.getImage();
	}

    public Image getLivesImg() {
        ImageIcon ic = new ImageIcon("media/lives.png");
        return ic.getImage();
    }
}