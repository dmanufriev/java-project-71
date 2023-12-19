
run-dist:
	@./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json

.PHONY: build
build:
	./gradlew clean
	./gradlew build
	./gradlew checkstyleMain
	./gradlew test


