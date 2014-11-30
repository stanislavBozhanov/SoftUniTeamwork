import javax.swing.*;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameFrame extends JPanel implements ActionListener {

    Timer mainTimer;
    Player player;
    static FuelMeter fuelMeter;  //VELIO: adds the fuelMeter
    Lives lives;
    Road asphalt;
    
    private int score = 0; 
    private int highScore = 0;
    private Font scoreFont;
    private String saveDataPath;
    private String fileName = "SaveData"; // georgi. added the highscore
    private static long initialTime = System.nanoTime();
    
    int gameSpeed = 2;  //Velio: will start at 1 and will increase with the levels up
    int currentSpeed = gameSpeed;
    int currentspeedchange = 0;

    int holeObstaclesCount;
    int fuelContainersCount;

    static ArrayList<HoleObstacle> holeObstacles = new ArrayList<HoleObstacle>();
    static ArrayList<FuelContainer> fuelContainers = new ArrayList<FuelContainer>();
   
    Random rand = new Random(); 
    
    public void loadFont() throws FontFormatException, IOException{ // georgi, imports the font.
        String fontFileName = "yourfont.ttf";
        InputStream is = this.getClass().getResourceAsStream(fontFileName);
        Font gameFont = Font.createFont(Font.TRUETYPE_FONT, is);   
      }

    public GameFrame() {
    	
        setFocusable(true);
     
        try {
        	saveDataPath = GameFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } 						// georgi set the path to save the highscore to the bin folder of the game.
        catch (Exception e){
        	e.printStackTrace();  
        }
        
        
        player = new Player(250, 550); // georgi, changed the player startpoint.
        addKeyListener(new KeyAdapt(player));

        lives = new Lives(460, 300);
        asphalt = new Road(0, -1050);
        fuelMeter = new FuelMeter(460, 400);

        mainTimer = new Timer(10, this);
        mainTimer.start();
        loadHighScore();	// georgi, loads the highscore.

        startGame();
 

    }
    
    private void createSaveData() {  // georgi, creates the file to save the highscores.
    	try {
    		File file = new File(saveDataPath, fileName);
    		FileWriter output = new FileWriter(file);
    		BufferedWriter writer = new BufferedWriter(output);
    		writer.write("" + 0);
    		writer.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    private void loadHighScore() {		// georgi, loads the highscore.
    	try {
    		File f = new File(saveDataPath, fileName); // georgi, checks if the highscore file exists.
    		if(!f.isFile()){
    			createSaveData(); // if it doesn't it creates it.
    		}
    		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    		highScore = Integer.parseInt(reader.readLine()); // reads the highScore.
    		reader.close();
    		
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void setHighScore() { // georgi, writes the highscore to the file.
    	FileWriter output = null;
    	 
    	try {
    		File f = new File(saveDataPath, fileName);
    		output = new FileWriter(f);
        	BufferedWriter writer = new BufferedWriter(output);
        	writer.write("" + highScore);
        	writer.close();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
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

        asphalt.draw(g2d);
    	currentspeedchange = player.getSpeedChange();
        currentSpeed = currentSpeed + currentspeedchange;
        if (currentSpeed <= 0) {
        	currentSpeed = 0;
        }
        asphalt.setGameSpeed(currentSpeed);

       for (int i = 0; i < holeObstacles.size(); i++) {
            HoleObstacle tempHoleObstacle = holeObstacles.get(i);
            tempHoleObstacle.draw(g2d);
            tempHoleObstacle.y += currentSpeed;
            
        }

        for (int i = 0; i < fuelContainers.size(); i++) {
            FuelContainer tempFuelContainer = fuelContainers.get(i);
            tempFuelContainer.draw(g2d);
            tempFuelContainer.y += currentSpeed;
        }

        player.draw(g2d);
        fuelMeter.draw(g2d);
        lives.draw(g2d);
        int[] timeArray = getTimeElapsed();
        
        g.setColor(Color.red);
        g.setFont(scoreFont);
        g.drawString("Highscore: " + highScore, 460, 40);
        g.setColor(Color.black);
        g.drawString("Time Elapsed: " + timeArray[1] + ":" + timeArray[0], 460, 80);
        g.drawString("Obstacles Left: " + holeObstacles.size(), 460, 120);
        g.drawString("Current Score: " + score, 460, 160);
    }
    
    //Returns an array of integers where the 0th position represents the seconds while the 1st minutes.
    private static int[] getTimeElapsed() {
    	long timeElapsed = (System.nanoTime() - initialTime) / 1000000;
    	int timeArray[] = {0,0};
        
		timeArray[1] = (int) TimeUnit.MILLISECONDS.toMinutes(timeElapsed);
        timeElapsed -= TimeUnit.MINUTES.toMillis(timeArray[1]);
        timeArray[0] = (int) TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
    	return timeArray;
    }

	@Override
    public void actionPerformed(ActionEvent e) {

        asphalt.update();
        if (score > highScore) { // georgi, checks the if you have highscore.
       		highScore = score;
       		
       	}

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
        score = score + gameSpeed;  // SKF changed this to have the effect of level on the score
        checkEnd();
        setHighScore(); // sets the highscore when you die.
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

        //Timer for the fuel - each 10 seconds the fuel goes down
        Timer fuelBurner = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                fuelMeter.setTempFuel(10);
            }
        });
        fuelBurner.setRepeats(true);
        fuelBurner.start();

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
            currentSpeed = gameSpeed;
            holeObstacles.clear();
            fuelContainers.clear();
            JOptionPane.showMessageDialog(null, "Nice Driving! You've completed level " + (gameSpeed - 2) + ". Let's drive on!");
            startGame();
            
            
        }

        if (fuelMeter.getTempFuel() == -10) {
            JOptionPane.showMessageDialog(null, "You've run out of fuel! GAME OVER");
            System.exit(0);
        }

    }

    //fills the fuel when a fuelcontainer is hit
    public static void maxFuel() {
        fuelMeter.setFuel(100);
    }


}

