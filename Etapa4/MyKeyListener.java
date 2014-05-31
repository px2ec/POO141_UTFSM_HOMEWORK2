
import java.awt.event.*;
import javax.swing.*;

public class MyKeyListener implements ActionListener, KeyListener {
	private MyWorld world;

	public MyKeyListener (MyWorld w){
		world = w;
	}
	 
	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) {
		
	}
	 
	/** Handle the key pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		if(e.getKeyCode()==KeyEvent.VK_N) {
			actionPerformed(null);
		}
	}
	 
	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
		
	}

	public void actionPerformed(ActionEvent e) {
		/* Testing...
		Spring sp0 = new Spring(1.0, 1.0);
		world.addElement(sp0);*/
		world.findNext();
	}

}