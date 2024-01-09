
stylish:
	@./build/install/app/bin/app ./src/test/resources/json/file1.json ./src/test/resources/json/file2.json

plain:
	@./build/install/app/bin/app -f=plain ./src/test/resources/json/file1.json ./src/test/resources/json/file2.json

json:
	@./build/install/app/bin/app -f=json ./src/test/resources/json/file1.json ./src/test/resources/json/file2.json

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

