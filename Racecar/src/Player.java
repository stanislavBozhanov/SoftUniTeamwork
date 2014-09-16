import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player{

	int velX = 0, velY = 0;
	int speed = 2;

    int x, y;

    public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		y += velY;
		x += velX;

		checkCollisions();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getCarImg(), x, y, null);
		//g2d.draw(getBounds());
	}

	public Image getCarImg() {

		ImageIcon ic = new ImageIcon("media/car.png");
		return ic.getImage();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

//		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_K) {
//			velY = -speed;
//		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_J) {
//			velY = speed;
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_H) {
			velX = -speed;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) {
			velX = speed;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

//		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_K) {
//			velY = 0;
//		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_J) {
//			velY = 0;
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_H) {
			velX = 0;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) {
			velX = 0;
		}
	}

	public void checkCollisions() {
		ArrayList<HoleObstacle> holeObstacles = GameFrame.getHoleObstacleList();
		ArrayList<FuelContainer> fuelContainers = GameFrame.getFuelContainerList();

		// Collision with obstacle
		for (int i = 0; i < holeObstacles.size(); i++) {

			HoleObstacle tempHoleObstacle = holeObstacles.get(i);

			if (getBounds().intersects(tempHoleObstacle.getBounds())) {
				// you die and lose a life or if no more lives - GAME OVER
				JOptionPane.showMessageDialog(null, "BAD, you just lost 7 points");

                //velio: We need to add code to reset the car at the initial position

                GameFrame.removeHoleObstacle(tempHoleObstacle);
			}
		}

		// Collision with fuel container
		for (int i = 0; i < fuelContainers.size(); i++) {
			FuelContainer tempFuelContainer = fuelContainers.get(i);

			if (getBounds().intersects(tempFuelContainer.getBounds())) {
				GameFrame.removeFuelContainer(tempFuelContainer);
				JOptionPane.showMessageDialog(null, "Your tank is full now!");
				// need to add more code - when you hit a fuelContainer = the
				// fuel meter goes full
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 110); //getCarImg().getWidth(null), getCarImg().getHeight(null));
        //VELIO: 40, 110 малко намаля размера на колата, така че ако се удари дупка леко отстрани на колата, няма веднага да умреш.
    }

}
