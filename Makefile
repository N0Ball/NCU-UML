# Clear Before and Build

final: build
	java Main

build: clean

	javac -d . Main.java

clean:

	find . -name "*.class" -type f | xargs rm -rf