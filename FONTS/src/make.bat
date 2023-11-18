

javac --release 11 domini\classes\*.java
javac --release 11 drivers\*.java
javac --release 11 domini\controladors\*.java
javac --release 11 domini\excepcions\*.java
javac --release 11 persistencia\controladors\*.java
javac --release 11 ProgramaConsola.java
mkdir ..\..\EXE\CLASS\domini\classes 2>NUL
mkdir ..\..\EXE\CLASS\drivers 2>NUL
mkdir ..\..\EXE\CLASS\domini\controladors 2>NUL
mkdir ..\..\EXE\CLASS\persistencia\controladors 2>NUL
mkdir ..\..\EXE\CLASS\domini\excepcions 2>NUL

REM Move files, suppress errors, and overwrite existing files
move /Y domini\classes\*.class ..\..\EXE\CLASS\domini\classes\
move /Y drivers\*.class ..\..\EXE\CLASS\drivers\
move /Y domini\controladors\*.class ..\..\EXE\CLASS\domini\controladors\
move /Y persistencia\controladors\*.class ..\..\EXE\CLASS\persistencia\controladors\
move /Y domini\excepcions\*.class ..\..\EXE\CLASS\domini\excepcions\
move /Y ProgramaConsola.class ..\..\EXE\CLASS\
