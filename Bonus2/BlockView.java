import java.awt.*;
import java.awt.geom.*;

public class BlockView {
	private final double width;
	private Color color = Color.BLACK;
	private Rectangle2D.Double shape = null;
	private Block block;
	
	public BlockView (Block b) {
		this.width = 0.1;
		this.block = b;
		this.shape = new Rectangle2D.Double();
	}

	public boolean contains(double x, double y) {
		double bPos = block.getPosition();

		if ((bPos - width/2.0) <= x && x <= (bPos + width/2.0))
			if ((bPos - width/2.0) <= y && y <= (bPos + width/2.0))
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
		double bPos = block.getPosition();

		shape.setFrameFromCenter(bPos, 0, bPos + width, width);
		g.setColor(color);
		g.fill(shape);
	}
}