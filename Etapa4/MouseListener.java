import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.geom.Point2D;

public class MouseListener extends MouseAdapter {
	private MyWorld world;
	private PhysicsElement currentElement;
	public MouseListener (MyWorld w){
		world = w;
	} 
	public void mouseMoved(MouseEvent e) {
		Point2D.Double p = new Point2D.Double(0,0); // Change mouse coordenates from
		MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);// pixels to meters.
		PhysicsElement newElement = world.find(p.getX(), p.getY()); 
		if (newElement == currentElement) return;
		if (currentElement != null) {
			currentElement.setReleased();
			currentElement = null;
		}
		if (newElement != null) { 
			currentElement = newElement;
			currentElement.setSelected();
		}
		world.repaintView();
	}
	public void mouseDragged(MouseEvent e) {
		Point2D.Double p = new Point2D.Double(0,0); // Change mouse coordenates from
		MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);// pixels to meters.

		currentElement.dragTo(p.getX());

		world.repaintView();
	}
	public void mouseReleased(MouseEvent e) {
		if (currentElement == null) return;
		if (currentElement instanceof Spring) {
			Spring spring = (Spring) currentElement;
			
			Point2D.Double p= new Point2D.Double(0,0);
			MyWorldView.SPACE_INVERSE_TRANSFORM.transform(e.getPoint(),p);

			 // we dragged a spring, so we look for and attachable element near by  
			SpringAttachable element = world.findAttachableElement(spring);
			if (element != null) {
				System.out.println("A:"+spring.getAendPosition()+"; B:"+spring.getBendPosition());
				double pos_e  = ((SpringAttachable)element).getPosition();
				double rad  = ((SpringAttachable)element).getRadius();
				// we dragged a spring and it is near an attachable element,
				// so we hook it to a spring end.
				double a=spring.getAendPosition();
				System.out.println("Ball:" + (pos_e - rad) + "," + (pos_e + rad));
				if ((a > pos_e - rad) && (a < pos_e + rad))
				//if (a==p.getX())
					spring.attachAend(element);
				double b=spring.getBendPosition();
				if ((b > pos_e - rad) && (b < pos_e + rad))
				//if (b==p.getX())
					spring.attachBend(element);
			 }
		}    
		currentElement.setReleased();
		currentElement = null;
		world.repaintView();
	}
}