all:
	cd .. && make && cd ./Tests
	javac -d ./Classes -cp ../../../libs/*:../Classes:. TestRunnerTests.java 
run:
	java -cp ../../../libs/*:../Classes:./Classes:. org.junit.runner.JUnitCore TesterTests
check: all run
