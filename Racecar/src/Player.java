import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player{

	int velX = 0;
	int speed = 2;
    int lives = 5;
    int x, y;
    
    int currentspeedchange = 0;
    boolean gamepause = false;
    
    
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
            currentspeedchange = 0;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) {
			velX = speed;
            if (x >= 390){
                x = 390;
                velX = 0;
            }
            currentspeedchange = 0;
		} else if (key == KeyEvent.VK_DOWN) {
			System.out.println("Down key pressed.");
			currentspeedchange = -1;
			
		} else if (key == KeyEvent.VK_UP) {
			System.out.println("Up key pressed.");
			currentspeedchange = 1;
		} else if (key == KeyEvent.VK_SPACE) {
			System.out.println("Space key pressed.");
			if (this.gamepause == false){
				this.gamepause = true;				
			} else {
				this.gamepause = false;
			}
			System.out.println("pause: " + this.gamepause);

		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_H) {
			velX = 0;
			currentspeedchange = 0;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_L) {
			velX = 0;
			currentspeedchange = 0;
		} else if (key == KeyEvent.VK_DOWN) {
			velX = 0;
			currentspeedchange = 0;
		} else if (key == KeyEvent.VK_UP) {
			velX = 0;
			currentspeedchange = 0;
		} else if (key == KeyEvent.VK_SPACE) {
			velX = 0;
		}
	}

	public void checkCollisions() {
		ArrayList<HoleObstacle> holeObstacles = GameFrame.getHoleObstacleList();
		ArrayList<FuelContainer> fuelContainers = GameFrame.getFuelContainerList();
		ArrayList<MovingObstacles> movingObstacles = GameFrame.getMovingObstaclesList();

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
                GameFrame.maxFuel(); //fills the fuel with the maxFuel() method in GameFrame
			}
		}

		// Collision with moving obstacles

		for (int i = 0; i < movingObstacles.size(); i++) {
			MovingObstacles tempMovingObstacles = movingObstacles.get(i);

			if (getBounds().intersects(tempMovingObstacles.getBounds())) {
                lives--;
                if (lives == 0) {
                    JOptionPane.showMessageDialog(null, "GAME OVER");
                    System.exit(0);
                }
                GameFrame.removeMovingObstacles(tempMovingObstacles);
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 110); //getCarImg().getWidth(null), getCarImg().getHeight(null));
        //VELIO: 40, 110 Ð¼Ð°Ð»ÐºÐ¾ Ð½Ð°Ð¼Ð°Ð»Ñ� Ñ€Ð°Ð·Ð¼ÐµÑ€Ð° Ð½Ð° ÐºÐ¾Ð»Ð°Ñ‚Ð°, Ñ‚Ð°ÐºÐ° Ñ‡Ðµ Ð°ÐºÐ¾ Ñ�Ðµ ÑƒÐ´Ð°Ñ€Ð¸ Ð´ÑƒÐ¿ÐºÐ° Ð»ÐµÐºÐ¾ Ð¾Ñ‚Ñ�Ñ‚Ñ€Ð°Ð½Ð¸ Ð½Ð° ÐºÐ¾Ð»Ð°Ñ‚Ð°, Ð½Ñ�Ð¼Ð° Ð²ÐµÐ´Ð½Ð°Ð³Ð° Ð´Ð° ÑƒÐ¼Ñ€ÐµÑˆ.
    }

	public int getSpeedChange() {
		return currentspeedchange;
	}

	public boolean getGamePause() {
		return this.gamepause;
	}

}
