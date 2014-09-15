import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JPanel implements ActionListener {

    Timer mainTimer;
    Player player;

    int holeObstaclesCount = 3;
    int fuelContainersCount = 2;

    static ArrayList<HoleObstacle> holeObstacles = new ArrayList<HoleObstacle>();
    static ArrayList<FuelContainer> fuelContainers = new ArrayList<FuelContainer>();
    Random rand = new Random();


    public GameFrame() {
        setFocusable(true);

        player = new Player(250, 600);
        addKeyListener(new KeyAdapt(player));

        mainTimer = new Timer(10, this);
        mainTimer.start();

        for (int i = 0; i < holeObstaclesCount; i++) {
            addHoleObstacle(new HoleObstacle(rand.nextInt(500), rand.nextInt(400)));
        }

        for (int i = 0; i < fuelContainersCount; i++) {
            addFuelContainer(new FuelContainer(rand.nextInt(500), rand.nextInt(400)));
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        player.draw(g2d);

        for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.draw(g2d);
        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
}
