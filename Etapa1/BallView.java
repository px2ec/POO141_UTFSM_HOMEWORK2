import java.awt.*;
import java.awt.geom.*;

public class BallView {
	private Color color = Color.BLUE;
	private Ellipse2D.Double shape = null;
	private Ball ball;
	
	public BallView (Ball b) {
		this.ball = b;
		this.shape = new Ellipse2D.Double();
	}

	public boolean contains(double x, double y) {
		/* I HAVE NO IDEA WHAT I'M DOING */
		/* I HAVE NO IDEA WHAT I'M DOING */
		/* I HAVE NO IDEA WHAT I'M DOING */
		/* I HAVE NO IDEA WHAT I'M DOING */
		/* I HAVE NO IDEA WHAT I'M DOING */
		/* I HAVE NO IDEA WHAT I'M DOING */

		double bPos = ball.getPosition();
		double bRad = ball.getRadius();

		if ((bPos - bRad <= x) && (x <= bPos + bRad))
			if ((-bRad <= y) && (y <= bRad))
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
		double radius = ball.getRadius();
		shape.setFrame(ball.getPosition() - radius, -radius, 2 * radius, 2 * radius);
		g.setColor(color);
		g.fill(shape);
	}
}