CLASS_INPUT =	./src/domini/classes/*.java \
				./src/domini/controladors/*.java

CLASS_OUTPUT =	../EXE/out/

JAR_OUTPUT =	../EXE/

JUNIT_JARS ./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./src/test/*.java

FUNCIONS_TESTS =		./src/test/functions/*.java

all:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac --release 11 -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./src/drivers/*.java
	jar cmvf ./src/drivers/ControladorDomini.mf $(JAR_OUTPUT)DriverControladorDomini.jar -C $(CLASS_OUTPUT) .

executaDriverControladorDomini:
	java --release 11 -jar $(JAR_OUTPUT)DriverDomini.jar

AlfabetTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.AlfabetTest

EntradaTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.EntradaTest

LPFTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.LPFTest

TeclatTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TeclatTest

TextTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TextTest

UsuariTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.UsuariTest

clean:
	rm -r ../EXE/out/*

distclean:
	rm -r ../EXE/*