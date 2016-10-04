
# Uses linux file separator, consider adding logic to handle Windows/Linux automatically
all:
	cd .. && make && cd ./Tests
	javac -d ./Classes -cp ../../../../libs/*:../../Classes/:. OrderedTreeAndTreeTests.java

check: all
	java -cp ../../../../libs/*:../../Classes/:./Classes/:. org.junit.runner.JUnitCore OrderedTreeAndTreeTests
