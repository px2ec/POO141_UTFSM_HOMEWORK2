import java.util.*;
import java.awt.*;

public class Ball extends PhysicsElement implements Simulateable {
	private static int id = 0;       // Ball identification number
	private final double mass;
	private final double radius;
	private double pos_t;            // Current position at time t
	private double pos_tPlusDelta;   // Next position in delta time in future
	private double speed_t;          // Speed at time t
	private double speed_tPlusDelta; // Speed in delta time in future
	private BallView view;           // Ball view of Model-View-Controller design pattern

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
		view = new BallView(this);
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
	public void computeNextState(double delta_t, MyWorld world) {
		// Assumption: On collision we only change speed
		Ball b;
		/* Elastic collision */
		if ((b = world.findCollidingBall(this)) != null) {
			speed_tPlusDelta  = (speed_t * (this.mass - b.getMass()) + 2 * b.getMass() * b.getSpeed());
			speed_tPlusDelta /= (this.mass + b.getMass());
			pos_tPlusDelta = pos_t;
		} else {
			speed_tPlusDelta = speed_t;
			pos_tPlusDelta = pos_t + speed_tPlusDelta * delta_t;
		}
	}
	public boolean collide(Ball b) {
		if (this == b)
			return false;
		
		boolean closeEnougth = Math.abs(getPosition() - b.getPosition()) < (getRadius() + b.getRadius());
		boolean approaching;
		
		if (b.getPosition() < getPosition())
			approaching = getSpeed() < b.getSpeed();
		else
			approaching = getSpeed() > b.getSpeed();
		
		return closeEnougth && approaching;
	}
	public void updateState() {
		pos_t = pos_tPlusDelta;
		speed_t = speed_tPlusDelta;
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
}
