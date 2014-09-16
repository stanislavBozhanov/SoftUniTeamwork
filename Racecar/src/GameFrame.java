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
    
    int holeObstaclesCount = 3;
    int fuelContainersCount = 2;
    
    int gameSpeed = 1;  //Velio: will start at 1 and will increase with the levels up

    static ArrayList<HoleObstacle> holeObstacles = new ArrayList<HoleObstacle>();
    static ArrayList<FuelContainer> fuelContainers = new ArrayList<FuelContainer>();
    Random rand = new Random();


    public GameFrame() {
        setFocusable(true);

        player = new Player(250, 600);
        addKeyListener(new KeyAdapt(player));

        mainTimer = new Timer(10, this);
        mainTimer.start();
        
        startGame();

        /*for (int i = 0; i < holeObstaclesCount; i++) {
            addHoleObstacle(new HoleObstacle(rand.nextInt(500), rand.nextInt(400)));
        }

        for (int i = 0; i < fuelContainersCount; i++) {
            addFuelContainer(new FuelContainer(rand.nextInt(500), rand.nextInt(400))); //moved into the last method "start Game"
        }*/

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Lives l = new Lives(460, 30);
        l.draw(g2d);
        ///////////////////////////////////////////////////////vlado - opit da nachertaia linii
        Line2D lin1 = new Line2D.Float(150, 0, 150, 800*5);
        g2d.draw(lin1);
        Line2D lin2 = new Line2D.Float(450, 0, 450, 800*5);
        g2d.draw(lin2);
        /////////////////////////////////////////////////////////////


       for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.draw(g2d);
            tempHoleObstacle.y += gameSpeed;   //Velio: moves the obstacles down//...vlado - not sure if this should be commented or not after the other changes
        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.draw(g2d);
            tempFuelContainer.y += gameSpeed;  //Velio - moves the fuel containers down//...vlado - not sure if this should be commented or not after the other changes
        }
        //Velio @vlado - uncommented them because when I tried to change the game speed - the obstacles moved with the same speed as before. Now when the gameSpeed is up, everything moves fasted.

        player.draw(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.update();
        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.update();
        }

        player.update();
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
    		addHoleObstacle(new HoleObstacle(rand.nextInt(450 - 150) + 150, -rand.nextInt(800 * gameSpeed)*5)); //Velio: added * gameSpeed variable
    	}

    	for (int i = 0; i < fuelContainersCount; i++) {
    		addFuelContainer(new FuelContainer(rand.nextInt(450 - 150) + 150, -rand.nextInt(800 * gameSpeed)*5));
    	}
    }
    //////////////////////////////////////////////////////
}
