import java.util.*;
import java.awt.*;

public class Ball extends PhysicsElement implements Simulateable, SpringAttachable {
	private static int id = 0;       // Ball identification number
	private final double mass;
	private final double radius;
	private double pos_t;            // Current position at time t
	private double pos_tPlusDelta;   // Next position in delta time in future
	private double speed_t;          // Speed at time t
	private double speed_tPlusDelta;   // Speed in delta time in future
	private double a_t;                // Acceleration at time t
	private double a_tMinusDelta;      // Acceleration delta time ago
	private BallView view;           // Ball view of Model-View-Controller design pattern
	private ArrayList<Spring> springs; // ArrayList can grow, arrays cannot

	private Ball() {
		// Nobody can create a block without state
		this(1.0, 0.1, 0,0);
	}
	public Ball(double mass, double radius, double position, double speed){
		super(id++);
		this.pos_t = position;
		this.speed_t = speed;
		this.mass = mass;
		this.radius = radius;
		this.a_t = 0;
		this.a_tMinusDelta = 0;
		view = new BallView(this);
		springs = new ArrayList<Spring>();
	}
	public double getMass() {
		return mass;
	}
	public double getRadius() {
		return radius;
	}
	public double getPosition() {
		return pos_t;
	}
	public double getSpeed() {
		return speed_t;
	}

	private double getNetForce() {
		double extForce = 0;
		
		for (Spring s: springs) {
			extForce += s.getForce(this);
		}

		return extForce;
	}
	
	public void computeNextState(double delta_t, MyWorld world) {
		Ball b;
		a_t = getNetForce() / mass;

		/* Elastic collision */
		if ((b = world.findCollidingBall(this)) != null) {
			speed_tPlusDelta  = speed_t * (this.mass - b.getMass()) + 2 * b.getMass() * b.getSpeed();
			speed_tPlusDelta /= this.mass + b.getMass();
			pos_tPlusDelta = pos_t + speed_tPlusDelta * delta_t;
		} else {
			speed_tPlusDelta = speed_t + 0.5 * (3 * a_t - a_tMinusDelta) * delta_t;
			pos_tPlusDelta = pos_t + speed_t * delta_t + (4 * a_t - a_tMinusDelta) * delta_t * delta_t / 6;
		}
	}
	public boolean collide(Ball b) {
		if (b == null)
			return false;

		return (Math.abs(b.getPosition() - this.pos_t) <= (b.getRadius() + this.radius));
	}
	public void updateState() {
		pos_t = pos_tPlusDelta;
		speed_t = speed_tPlusDelta;
		a_tMinusDelta = a_t;
	}
	public void updateView(Graphics2D g) {
		/*
		 * Update this Ball's view in Model-View-Controller
		 * design pattern
		 */
		view.updateView(g);
	}
	public boolean contains(double x, double y) {
		return view.contains(x,y);
	}
	public void setSelected() {
		view.setSelected();
	}
	public void setReleased() {
		view.setReleased();
	}
	public void dragTo(double x) {
		this.pos_t = x;
	}
	public String getDescription() {
		return "Ball_" + getId() + ":x";
	}
	public String getState() {
		return getPosition() + "";
	}
	public void attachSpring(Spring spring) {
		if (spring == null)
			return;

		springs.add(spring);
	}
	public void detachSpring(Spring spring) {
		if (spring == null)
			return;

		for (Spring s: springs) {
			if (spring.getId() == s.getId()) {
				springs.remove(spring);
			}

		}
	}
}
