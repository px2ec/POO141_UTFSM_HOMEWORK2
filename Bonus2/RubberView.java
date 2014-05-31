import java.awt.*;
import java.awt.geom.*;

public class RubberView {
	/*private static final double xPoints[] = {0,0.10, 0.125, 0.175, 0.225, 0.275, 0.325, 
											0.375, 0.425, 0.475, 0.525,0.575,0.625,
											0.675,0.725,0.775,0.825,0.875, 0.90,1.0};
	private static final double yPoints[] = {0,0,-0.1,0.1,-0.1,0.1,-0.1,0.1,-0.1,0.1,
											-0.1,0.1,-0.1,0.1,-0.1,0.1,-0.1,0.1,0,0};
	private static final
	Path2D.Double polyline = new Path2D.Double(Path2D.WIND_EVEN_ODD,xPoints.length);

	private Path2D.Double shape;*/
	private Stroke stroke;
	private Rubber rubber;   
	private Rectangle2D.Double shape = null;

	static {  // static initialization block. It creates a spring of length = 1.
		polyline.moveTo (xPoints[0], yPoints[0]);
		for (int index = 1; index < xPoints.length;index++)
			polyline.lineTo(xPoints[index], yPoints[index]);
	}
	public RubberView(Rubber r) {
		this.rubber = r;
		AffineTransform at = AffineTransform.getTranslateInstance(0,0);
		double  x = rubber.getBendPosition() - rubber.getAendPosition();
		at.rotate(x, 0);
		at.scale(Math.abs(x), rubber.getRestLength());
		shape = (Path2D.Double) at.createTransformedShape(polyline);
		stroke = new BasicStroke(0.02f);
	}
	public void updateView (Graphics2D g){
		double ax = rubber.getAendPosition();
		double xa_b = rubber.getBendPosition() - rubber.getAendPosition();
		AffineTransform at = AffineTransform.getTranslateInstance(ax, 0);
		at.rotate(xa_b, 0);
		at.scale(Math.abs(xa_b),  rubber.getRestLength());
		shape = (Path2D.Double) at.createTransformedShape(polyline);
		if (Math.abs(xa_b) < rubber.getRestLength())
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.RED);
		g.setStroke(stroke);
		g.draw(shape);
	}
	public boolean contains(double x, double y){
		return shape.getBounds2D().contains(x,y);
	}
	public void setSelected() {
		/*    */
	}
	public void setReleased() {
		/* .... */
	}
}