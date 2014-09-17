import javax.swing.*;
import java.awt.*;

public class Road extends GameFrame{
	
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
