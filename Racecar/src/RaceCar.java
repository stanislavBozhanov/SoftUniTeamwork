import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RaceCar extends JFrame {

	public RaceCar() {
		setLayout(new GridLayout(1, 1, 0, 0));
		add(new Car());
	}

	public static void main(String[] args) {
		JFrame theRace = new RaceCar();
		theRace.setTitle("The Best");
		theRace.setSize(500, 888);
		theRace.setLocationRelativeTo(null);
		theRace.setVisible(true);
		theRace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class Car extends JPanel {
		protected int x = 250;
		protected int y = 0;
		protected int z = 0;

		public Car() {
			int speed = (int) (Math.random() * 1000) + 2;
			Timer timer1 = new Timer(speed, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					x += 10;
					repaint();
				}
			});
			timer1.start();
		}

		@Override
		public void paintComponent(Graphics theCar) {
			super.paintComponent(theCar);
		
			y = getHeight();
			z = getWidth();
			System.out.println(z);
			System.out.println(y);
			Polygon polygon = new Polygon();
			theCar.setColor(Color.BLACK);
			theCar.fillOval(x + 10, y - 11, 10, 10);
			theCar.fillOval(x + 30, y - 11, 10, 10);
			theCar.setColor(Color.BLUE);
			theCar.fillRect(x, y - 21, 50, 10);
			
			theCar.fillPolygon(polygon);
			
			
		}

	}
}