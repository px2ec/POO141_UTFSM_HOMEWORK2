import java.awt.*;
import java.awt.geom.*;

public class BlockView {
	private Color color = Color.BLUE;
	private Ellipse2D.Double shape = null;
	private Ball ball;
	
	public BallView (Ball b) {
		this.ball = b;
		this.shape = new Ellipse2D.Double();
	}

	public boolean contains(double x, double y) {
		double bPos = ball.getPosition();
		double bRad = ball.getRadius();


		if ((hPos - width/2.0) <= x && x <= (hPos + width/2.0))
			if ((hPos - width/2.0) <= y && y <= (hPos + width/2.0))
				return true;

		return false;
	}

	public void setSelected() {
		color = Color.RED;
	}

	public void setReleased() {
		color = Color.BLACK;
	}

	void updateView(Graphics2D g) {
		double hPos = hook.getPosition();

		shape.setFrameFromCenter(hPos, 0, hPos + width, width);
		g.setColor(color);
		g.fill(shape);
	}
}