import java.util.*;
import java.awt.*;

public class FixedHook extends PhysicsElement implements SpringAttachable {
	private static int id = 0;         // Ball identification number
	private double pos_t;              // Current position at time t
	private double pos_tPlusDelta;     // Next position in delta time in future
	private ArrayList<Spring> springs; // ArrayList can grow, arrays cannot

	private FixedHook() {
		// Nobody can create a block without state
		this(0);
	}
	
	public FixedHook(double position) {
		super(id++);
		pos_t = position;

		springs = new ArrayList<Spring>();
	}

	public String getState() {
		return "";
	}

	public double getPosition() {
		return pos_t;
	}

	public void computeNextState(double delta_t, MyWorld world) {
		pos_tPlusDelta = pos_t;
	}

	public void updateState() {
		pos_t = pos_tPlusDelta;
	}

	public String getDescription() {
		return "FixedHook_" + super.getId();
	}

	public void attachSpring(Spring spring) {
		if (spring == null)
			return;

		springs.add(spring);
	}
}