import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wacky Racer");
        frame.setSize(600, 900);
        frame.setLocationRelativeTo(null);//vlado, centered the frame. It should be after the size. 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameFrame());
        frame.setVisible(true);
    }
}
