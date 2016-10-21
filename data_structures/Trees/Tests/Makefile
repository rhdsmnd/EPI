
# Uses linux file separator, consider adding logic to handle Windows/Linux automatically
all:
	cd .. && make && cd ./Tests
	javac -d ./Classes -cp ../../../../libs/*:../../Classes/:. OrderedTreeAndTreeTests.java
	javac -d ./Classes -cp ../../../../libs/*:../../Classes/:. TreeParserTests.java
	javac -d ./Classes -cp ../../../../libs/*:../../Classes/:. UnorderedTreeTests.java

run:
	java -cp ../../../../libs/*:../../Classes/:./Classes/:. org.junit.runner.JUnitCore OrderedTreeAndTreeTests
	java -cp ../../../../libs/*:../../Classes/:./Classes/:. org.junit.runner.JUnitCore TreeParserTests
	java -cp ../../../../libs/*:../../Classes/:./Classes/:. org.junit.runner.JUnitCore UnorderedTreeTests

check: all run

