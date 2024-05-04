JC = javac
JCFLAGS = -d build -cp build -implicit:none -sourcepath "src:tmp"
JCDEPEND = -d build -cp build -implicit:class -sourcepath "src:tmp"
JVM = java
JVMFLAGS = -cp build
SRCPATH = src/fr/iutfbleau/sae32
BUILDPATH = build/fr/iutfbleau/sae32


$(BUILDPATH)/main/Main.class: $(SRCPATH)/main/Main.java $(BUILDPATH)/view/Fenetre.class
	$(JC) $(JCFLAGS) $(SRCPATH)/main/Main.java

$(BUILDPATH)/view/Fenetre.class: $(SRCPATH)/view/Fenetre.java $(BUILDPATH)/entity/graphics/SPanel.class $(BUILDPATH)/utils/PrimaryColor.class $(BUILDPATH)/controller/CelluleController.class $(BUILDPATH)/controller/FieldController.class
	$(JC) $(JCFLAGS) $(SRCPATH)/view/Fenetre.java

$(BUILDPATH)/utils/PrimaryColor.class: $(SRCPATH)/utils/PrimaryColor.java
	$(JC) $(JCFLAGS) $(SRCPATH)/utils/PrimaryColor.java

$(BUILDPATH)/entity/graphics/SPanel.class: $(SRCPATH)/entity/graphics/SPanel.java $(BUILDPATH)/entity/Tree.class
	$(JC) $(JCFLAGS) $(SRCPATH)/entity/graphics/SPanel.java

$(BUILDPATH)/entity/Node.class: $(SRCPATH)/entity/Node.java $(BUILDPATH)/entity/graphics/SPanel.class
	$(JC) $(JCDEPEND) $(SRCPATH)/entity/Node.java $(SRCPATH)/entity/graphics/SPanel.java $(SRCPATH)/entity/Tree.java

$(BUILDPATH)/controller/CelluleController.class: $(SRCPATH)/controller/CelluleController.java $(BUILDPATH)/controller/FieldController.class
	$(JC) $(JCFLAGS) $(SRCPATH)/controller/CelluleController.java

$(BUILDPATH)/controller/FieldController.class: $(SRCPATH)/controller/FieldController.java $(BUILDPATH)/utils/PrimaryColor.class
	$(JC) $(JCFLAGS) $(SRCPATH)/controller/FieldController.java

$(BUILDPATH)/entity/NodeOperation.class: $(SRCPATH)/entity/NodeOperation.java $(BUILDPATH)/entity/Node.class
	$(JC) $(JCFLAGS) $(SRCPATH)/entity/NodeOperation.java

$(BUILDPATH)/entity/NodeNumber.class: $(SRCPATH)/entity/NodeNumber.java $(BUILDPATH)/entity/Node.class
	$(JC) $(JCFLAGS) $(SRCPATH)/entity/NodeNumber.java

$(BUILDPATH)/entity/NodePanel.class: $(SRCPATH)/entity/NodePanel.java $(BUILDPATH)/entity/Node.class $(BUILDPATH)/entity/graphics/SPanel.class
	$(JC) $(JCFLAGS) $(SRCPATH)/entity/NodePanel.java

$(BUILDPATH)/entity/Tree.class: $(SRCPATH)/entity/Tree.java $(BUILDPATH)/entity/graphics/SPanel.class $(BUILDPATH)/entity/Node.class $(BUILDPATH)/entity/NodeNumber.class $(BUILDPATH)/entity/NodeOperation.class $(BUILDPATH)/entity/NodePanel.class
	$(JC) $(JCDEPEND) $(SRCPATH)/entity/Tree.java $(SRCPATH)/entity/graphics/SPanel.java


## Run depuis les class
run: $(BUILDPATH)/main/Main.class
	$(JVM) $(JVMFLAGS) fr.iutfbleau.sae32.main.Main

## Création .jar
jar: $(BUILDPATH)/main/Main.class
	jar cvfe sae32.jar fr.iutfbleau.sae32.main.Main -C build fr


## Run depuis le jar
run-jar: sae32.jar
	$(JVM) -jar sae32.jar

clean:
	rm -rf build/