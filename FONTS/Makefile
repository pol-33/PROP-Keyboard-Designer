CLASS_INPUT =	./src/domini/classes/*.java \
				./src/domini/controladors/*.java \
				./src/persistencia/controladors/*.java \
				./src/presentacio/vistes/*.java \
				./src/presentacio/elements/*.java \
                ./src/presentacio/controladors/*.java \
                ./src/drivers/*.java

CLASS_INPUT_PERSISTENCIA =  ./src/persistencia/controladors/*.java \
                            ./src/persistencia/classes/*.java

CLASS_OUTPUT =	../EXE/out/

JAR_OUTPUT =	../EXE/

JUNIT_JARS = ./lib/junit/junit/4.13.1/junit-4.13.1.jar:./lib/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

OPENCSV_JARS = ./lib/opencsv-5.9.jar

JUNIT_TESTS = ./src/test/*.java

FUNCIONS_TESTS =		./src/test/functions/*.java

all:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac --release 11 -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./src/drivers/*.java
	jar cmvf ./src/drivers/DriverControladorAlgoritme.mf $(JAR_OUTPUT)DriverControladorAlgoritme.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./src/drivers/Main.mf $(JAR_OUTPUT)Main.jar -C $(CLASS_OUTPUT) .

jars_persistencia:
	javac -cp $(OPENCSV_JARS) --release 11 -d $(CLASS_OUTPUT) $(CLASS_INPUT_PERSISTENCIA) ./src/drivers/DriverControladorPersistencia.java
	jar cmvf ./src/drivers/DriverControladorPersistencia.mf $(JAR_OUTPUT)DriverControladorPersistencia.jar -C $(CLASS_OUTPUT) .

executaDriverControladorAlgoritme: jars
	java -jar $(JAR_OUTPUT)DriverControladorAlgoritme.jar

executaAplicacio: jars
	java -jar $(JAR_OUTPUT)Main.jar

executaDriverControladorPersistencia: jars_persistencia
	cd ./../EXE/out && \
    java -cp .:../../FONTS/lib/opencsv-5.9.jar:../../FONTS/lib/commons-lang3-3.14.0.jar drivers.DriverControladorPersistencia

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