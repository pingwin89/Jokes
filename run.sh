DIR=$(pwd)
cd src/main/java
javac -d ../../../bin pl/pawc/jokes/Main.java
cd $DIR/bin
java pl.pawc.jokes.Main baza
