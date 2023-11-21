CLASS_INPUT =	./src/domini/classes/*.java \
				./src/domini/controladors/*.java \
				./src/persistencia/controladors/*.java \
				./src/ProgramaConsola.java

CLASS_OUTPUT =	../EXE/out/

JAR_OUTPUT =	../EXE/

JUNIT_JARS = ./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar:./lib/junit-jupiter-5.4.2.jar:./lib/junit-jupiter-api-5.4.2.jar:./lib/apiguardian-api-1.0.0.jar

JUNIT_TESTS = ./src/test/*.java

FUNCIONS_TESTS =		./src/test/functions/*.java

all:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac --release 11 -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./src/drivers/*.java
	jar cmvf ./src/drivers/DriverControladorAlgoritme.mf $(JAR_OUTPUT)DriverControladorAlgoritme.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverControladorAlgoritme.jar

executaDriverControladorAlgoritme:
	java -jar $(JAR_OUTPUT)DriverControladorAlgoritme.jar

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