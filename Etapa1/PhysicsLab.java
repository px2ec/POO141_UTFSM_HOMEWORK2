import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;

public class PhysicsLab {
	public static void main(String[] args) {
		PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
		lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lab_gui.setVisible(true);
	}
}

class PhysicsLab_GUI extends JFrame {
	public PhysicsLab_GUI() {
		setTitle("My Small and Nice Physics Laboratory");
		// (HEIGHT + 50) to account for menu height
		setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT + 50);
		MyWorld world = new MyWorld();
		MyWorldView worldView = new MyWorldView(world);
		world.setView(worldView);
		add(worldView);
		createConfiguration(world);

		world.start();
	}
	// Please note how similar it is to "Etapa 4" of T1
	private void createConfiguration(MyWorld world) {
		double mass = 1.0;      //  1.0 [kg] 
		double radius = 0.1;    // 10.0 [cm] 
		double position = 0.0;  //  1.0 [m] 
		double speed = 0.5;     //  0.5 [m/s]
		Ball b0 = new Ball(mass, radius, position, speed);
		Ball b1 = new Ball(mass, radius, 2.0, 0);
		world.addElement(b0);
		world.addElement(b1);
	}
}