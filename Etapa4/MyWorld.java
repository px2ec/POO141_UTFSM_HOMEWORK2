import java.util.*;
import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class MyWorld implements ActionListener {
	private PrintStream out;
	private ArrayList<PhysicsElement> elements;  // array to hold everything in my world.
	private MyWorldView view;   // NEW
	private Timer passingTime;   // NEW
	private double t;        // simulation time
	private double delta_t;        // in seconds
	private double refreshPeriod;  // in seconds
	private ArrayList<PhysicsElement> inpos;
	private PhysicsElement current;
	private ListIterator<PhysicsElement> litr;

	public MyWorld() {
		this(System.out);
	}

	public MyWorld(PrintStream output) {
		view = null;
		out = output;
		t = 0;
		refreshPeriod = 0.06; // 60.00 [ms]
		delta_t = 0.00001;    //  0.01 [ms]
		elements = new ArrayList<PhysicsElement>();
		inpos = new ArrayList<PhysicsElement>();
		passingTime = new Timer((int)(refreshPeriod*1000), this);    
	}

	public void addElement(PhysicsElement e) {
		elements.add(e);
		view.repaintView();
	}
	public void setView(MyWorldView view) {
		this.view = view;
	}
	public void setDelta_t(double delta) {
		delta_t = delta;
	}
	public void setRefreshPeriod(double rp) {
		refreshPeriod = rp;
		passingTime.setDelay((int)(refreshPeriod*1000)); // convert from [s] to [ms]
	}
	public void start() {
		if (passingTime.isRunning())
			return;
		passingTime.start();
		view.desableMouseListener();
      
	}
	public void stop() {
		passingTime.stop();
		view.enableMouseListener(); 
	}

	public void actionPerformed(ActionEvent event) {
		/*
		 * Like simulate method of Assignment 1,
		 * The arguments are attributes here.
		 */
		double nextStop = t + refreshPeriod;
		for (; t<nextStop; t += delta_t) {
			// Compute each element next state based on current global state
			for (PhysicsElement e: elements) {
				if (e instanceof Simulateable) {
					Simulateable s = (Simulateable) e;
					s.computeNextState(delta_t,this);
				}
			}
			// For each element update its state
			for (PhysicsElement e: elements) {
				if (e instanceof Simulateable) {
					Simulateable s = (Simulateable) e;
					s.updateState();
					repaintView();
				}
			}
		}
	}

	public void repaintView() {
		view.repaintView();
	}

	public Ball findCollidingBall(Ball me) {
		for (PhysicsElement e: elements)
			if (e instanceof Ball) {
				Ball b = (Ball) e;
				if ((b!=me) && b.collide(me)) return b;
			}
		return null;
	}
  
    public SpringAttachable findAttachableElement(Spring s){
		for (PhysicsElement e: elements) {
			if (e instanceof SpringAttachable) {
				double pos_e  = ((SpringAttachable)e).getPosition();
				double rad  = ((SpringAttachable)e).getRadius();
				double posA = s.getAendPosition();
				double posB = s.getBendPosition();
				if (((posA > pos_e - rad) && (posA < pos_e + rad)) || ((posB > pos_e - rad) && (posB < pos_e + rad)))
					return (SpringAttachable) e;
			}
		}
    	return null;
    }

	public ArrayList<PhysicsElement> getPhysicsElements() {
		return elements;
	}

	public void find(double x, double y) {
		inpos.clear();
		for (PhysicsElement e: elements) {
				if (e.contains(x,y))
					inpos.add(e);
		}
		if (inpos.size() != 0) {
			litr = inpos.listIterator();
			current = inpos.get(0);
		}
	}

	public PhysicsElement findCurrentElement() {	
		return current;
	}

	public void findNext(){
		if (litr.hasNext()) 
			current = litr.next();
	}
}