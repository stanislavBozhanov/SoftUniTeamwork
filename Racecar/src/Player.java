import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player{

	int velX = 0;
	int speed = 2;
    int lives = 5;
    int x, y;

    public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		x += velX;
		checkCollisions();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getCarImg(), x, y, null);

        switch (lives) {
            case 5: g2d.drawImage(Lives.getSkullImg(), 465, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 485, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 505, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 525, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 545, 325, null);
                break;
            case 4: g2d.drawImage(Lives.getSkullImg(), 465, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 485, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 505, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 525, 325, null);
                break;
            case 3: g2d.drawImage(Lives.getSkullImg(), 465, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 485, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 505, 325, null);
                break;
            case 2: g2d.drawImage(Lives.getSkullImg(), 465, 325, null);
                g2d.drawImage(Lives.getSkullImg(), 485, 325, null);
                break;
            case 1: g2d.drawImage(Lives.getSkullImg(), 465, 325, null);
                break;
        }
	}

	public Image getCarImg() {

		ImageIcon ic = new ImageIcon("media/car.png");
		return ic.getImage();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_H) {
            velX = -speed;
            if (x <= 0){
                x = 0;
                velX = 0;
            }
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) {
			velX = speed;
            if (x >= 390){
                x = 390;
                velX = 0;
            }
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

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

                lives--;
                if (lives == 0) {
                    JOptionPane.showMessageDialog(null, "GAME OVER");
                    System.exit(0);
                }
              //  else {  // georgi, removed the message.
              //      JOptionPane.showMessageDialog(null, "You crashed! Be more careful!");
             //   }
                GameFrame.removeHoleObstacle(tempHoleObstacle);
			}
		}

		// Collision with fuel container
		for (int i = 0; i < fuelContainers.size(); i++) {
			FuelContainer tempFuelContainer = fuelContainers.get(i);

			if (getBounds().intersects(tempFuelContainer.getBounds())) {
				GameFrame.removeFuelContainer(tempFuelContainer);
				//JOptionPane.showMessageDialog(null, "Your tank is full now!");
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
