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
	FixedHook.class \
	FixedHookView.class \
	Spring.class \
	SpringView.class \
	SpringAttachable.class\
	LabMenuListener.class \
	Simulateable.class \
	MyWorldView.class \
	MouseListener.class

SOURCES = \
	Ball.java \
	BallView.java \
	MyWorld.java \
	PhysicsLab.java \
	PhysicsElement.java \
	FixedHook.java \
	FixedHookView.java \
	Spring.java \
	SpringView.java \
	SpringAttachable.java\
	LabMenuListener.java \
	Simulateable.java \
	MyWorldView.java \
	MouseListener.java

MAIN = PhysicsLab

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

all: $(CLASSES)

doc: $(SOURCES)
	$(JDOC) -author $(SOURCES) -d Documentation

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	rm -rf *.class

mrproper:
	make clean
	rm -rf res*.csv res*.png

rebuild:
	make clean && make

rerun:
	make rebuild && make run