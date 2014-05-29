import java.awt.*;
import java.awt.geom.*;

public class FixedHookView {
	private final double width;
	private Color color = Color.GREEN;
	private Rectangle2D.Double shape = null;
	private FixedHook hook;
	
	public FixedHookView(FixedHook h) {
		this.width = 0.1;
		this.hook = h;
		this.shape = new Rectangle2D.Double();
	}

	public boolean contains(double x, double y) {
		double hPos = hook.getPosition();

		if ((hPos - width/2.0) <= x && x <= (hPos + width/2.0))
			if ((hPos - width/2.0) <= y && y <= (hPos + width/2.0))
				return true;

		return false;
	}

	public void setSelected() {
		color = Color.RED;
	}

	public void setReleased() {
		color = Color.BLUE;
	}

	void updateView(Graphics2D g) {
		double hPos = hook.getPosition();

		shape.setFrameFromCenter(hPos, 0, hPos + width, width);
		g.setColor(color);
		g.fill(shape);
	}
}