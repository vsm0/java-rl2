.PHONY: all build run clean

all: build run clean

build:
	mkdir -p build
	javac -d build src/*/*.java

run:
	java -cp build main.Main

clean:
	rm -rf build/*
