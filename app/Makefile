
run-dist:
	@./build/install/app/bin/app ./src/test/resources/json/file1.json ./src/test/resources/json/file2.json

.PHONY: build
build:
	./gradlew clean
	./gradlew build
	./gradlew checkstyleMain
	./gradlew test

report:
	./gradlew jacocoTestReport

help:
	@./build/install/app/bin/app -h

version:
	@./build/install/app/bin/app -V

