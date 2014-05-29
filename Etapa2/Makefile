JC = javac
JVM = java
JDOC = javadoc
JFLAGS = -g

CLASSES = \
	Ball.class \
	BallView.class \
	MyWorld.class \
	PhysicsLab.class \
	PhysicsElement.class \
	LabMenuListener.class \
	Simulateable.class \
	MyWorldView.class

SOURCES = \
	Ball.java \
	BallView.java \
	MyWorld.java \
	PhysicsLab.java \
	PhysicsElement.java \
	LabMenuListener.java \
	Simulateable.java \
	MyWorldView.java

MAIN = PhysicsLab

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

all: $(CLASSES)

doc: $(SOURCES)
	$(JDOC) -autor $(SOURCES)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	rm -rf *.class

mrproper:
	make clean
	rm -rf res*.csv res*.png