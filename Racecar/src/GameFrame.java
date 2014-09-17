
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JPanel implements ActionListener {

    Timer mainTimer;
    Player player;
    FuelMeter fuelMeter;  //VELIO: adds the fuelMeter
    Lives lives;
    Road asphalt;

    int gameSpeed = 1;  //Velio: will start at 1 and will increase with the levels up

    int holeObstaclesCount;
    int fuelContainersCount;

    static ArrayList<HoleObstacle> holeObstacles = new ArrayList<HoleObstacle>();
    static ArrayList<FuelContainer> fuelContainers = new ArrayList<FuelContainer>();
    Random rand = new Random();


    public GameFrame() {
        setFocusable(true);

        player = new Player(250, 650);
        addKeyListener(new KeyAdapt(player));

        lives = new Lives(460, 300);
        asphalt = new Road(0, -850);
        fuelMeter = new FuelMeter(460, 400);

        mainTimer = new Timer(10, this);
        mainTimer.start();


        startGame();


    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        /*///////////////////////////////////////////////////////vlado - opit da nachertaia linii
        Line2D lin1 = new Line2D.Float(150, 0, 150, 800*5);
        g2d.draw(lin1);
        Line2D lin2 = new Line2D.Float(450, 0, 450, 800*5);
        g2d.draw(lin2);
        /////////////////////////////////////////////////////////////*/

<<<<<<< HEAD
        Road asphalt = new Road(460,800);
=======
        asphalt.draw(g2d);
        asphalt.setGameSpeed(gameSpeed);
>>>>>>> 0c744f60eebbfbc6dcfd7a67c1eca2cf0388f572

       for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.draw(g2d);
            tempHoleObstacle.y += gameSpeed;

        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.draw(g2d);
            tempFuelContainer.y += gameSpeed;
        }

        player.draw(g2d);
        fuelMeter.draw(g2d);
        lives.draw(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        asphalt.update();

        for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.update();
        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.update();
        }

        player.update();
        lives.update();

        checkEnd();
        repaint();
    }

    public void addHoleObstacle(HoleObstacle h) {
        holeObstacles.add(h);
    }
    
    public static void removeHoleObstacle(HoleObstacle h) {
        holeObstacles.remove(h);
    }
    
    public static ArrayList<HoleObstacle> getHoleObstacleList() {
        return holeObstacles;
    }

    public void addFuelContainer(FuelContainer f) {
        fuelContainers.add(f);
    }

    public static void removeFuelContainer(FuelContainer f) {
        fuelContainers.remove(f);
    }

    public static ArrayList<FuelContainer> getFuelContainerList() {
        return fuelContainers;
    }

    //////////////////////////////////////////////////////vlado - added to try to make the movement in the midle and more appart
    public void startGame() {
    	holeObstaclesCount = gameSpeed * 10;
    	fuelContainersCount = gameSpeed * 2;
    	
    	for (int i = 0; i < holeObstaclesCount; i++) {
    		addHoleObstacle(new HoleObstacle(rand.nextInt(425), -10 -rand.nextInt(800 * gameSpeed)*5));

    	}

    	for (int i = 0; i < fuelContainersCount; i++) {
    		addFuelContainer(new FuelContainer(rand.nextInt(410), -10 -rand.nextInt(800 * gameSpeed)*5));
    	}
    }
    //////////////////////////////////////////////////////

    public void checkEnd() {
        if (holeObstacles.size() == 0) {
            gameSpeed++;
            holeObstacles.clear();
            fuelContainers.clear();
            JOptionPane.showMessageDialog(null, "Nice Driving! You've completed level " + (gameSpeed - 1) + ". Let's drive on!");
            startGame();
        }

    }
}

