import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity {

    int velX = 0, velY = 0;
    int speed = 2;

    public Player(int x, int y) {
        super(x, y);
    }

    public void update() {
        y += velY;
        x += velX;

        checkCollisions();
    }

    public void draw (Graphics2D g2d) {
        g2d.drawImage(getCarImg(), x, y, null);
    }

    public Image getCarImg() {

        ImageIcon ic = new ImageIcon("/home/velio/Downloads/TeamWorkProject/car.png");
        return ic.getImage();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            velY = -speed;
        } else if (key == KeyEvent.VK_DOWN) {
            velY = speed;
        } else if (key == KeyEvent.VK_LEFT) {
            velX = -speed;
        } else if (key == KeyEvent.VK_RIGHT) {
            velX = speed;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            velY = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            velY = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            velX = 0;
        } else if (key == KeyEvent.VK_RIGHT) {
            velX = 0;
        }
    }

    public void checkCollisions() {
        ArrayList<HoleObstacle> holeObstacles = GameFrame.getHoleObstacleList();
        ArrayList<FuelContainer> fuelContainers = GameFrame.getFuelContainerList();

        //Collision with obstacle
        for (int i = 0; i < holeObstacles.size(); i++) {

            HoleObstacle tempHoleObstacle = holeObstacles.get(i);

            if (getBounds().intersects(tempHoleObstacle.getBounds())) {
                //you die and lose a life or if no more lives - GAME OVER
            }
        }

        //Collision with fuel container
        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);

            if (getBounds().intersects(tempFuelContainer.getBounds())) {
                GameFrame.removeFuelContainer(tempFuelContainer);
                //need to add more code - when you hit a fuelContainer = the fuel meter goes full
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getCarImg().getWidth(null), getCarImg().getHeight(null));
    }

}
