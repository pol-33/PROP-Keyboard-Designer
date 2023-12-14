CLASS_INPUT =	./src/domini/classes/*.java \
				./src/domini/controladors/*.java \
				./src/persistencia/controladors/*.java \
				./src/presentacio/vistes/*.java \
				./src/presentacio/elements/*.java \
                ./src/presentacio/controladors/*.java \
                ./src/drivers/*.java

CLASS_OUTPUT =	../EXE/out/

JAR_OUTPUT =	../EXE/

JUNIT_JARS = ./lib/junit/junit/4.13.1/junit-4.13.1.jar:./lib/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

JUNIT_TESTS = ./src/test/*.java

FUNCIONS_TESTS =		./src/test/functions/*.java

all:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac --release 11 -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./src/drivers/*.java
	jar cmvf ./src/drivers/DriverControladorAlgoritme.mf $(JAR_OUTPUT)DriverControladorAlgoritme.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./src/drivers/DriverControladorPresentacio.mf $(JAR_OUTPUT)DriverControladorPresentacio.jar -C $(CLASS_OUTPUT) .

executaDriverControladorAlgoritme: jars
	java -jar $(JAR_OUTPUT)DriverControladorAlgoritme.jar

executaDriverControladorPresentacio: jars
	java -jar $(JAR_OUTPUT)DriverControladorPresentacio.jar

AllTests: AlfabetTest EntradaTest LPFTest TeclatTest TextTest UsuariTest

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