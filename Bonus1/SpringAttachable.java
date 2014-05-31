interface SpringAttachable {
	void attachSpring(Spring s);
	void detachSpring(Spring s);
	boolean collide(SpringAttachable b);
	double getPosition();
	double getMass();
	double getRadius();
	double getSpeed();
	String getDescription();
}