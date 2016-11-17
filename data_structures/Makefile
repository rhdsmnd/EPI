SHELL = bash

GRAPH_SRCS := $(wildcard com/rhdes/graphs/*.java)
TREE_SRCS := $(wildcard com/rhdes/trees/*.java)
STACK_SRCS := $(wildcard com/rhdes/stacks/*.java)
QUEUE_SRCS := $(wildcard com/rhdes/queues/*.java)

SRCS := $(GRAPH_SRCS) $(TREE_SRCS) $(STACK_SRCS) $(QUEUE_SRCS)

DATA_STRUCT_CLASSPATH = ./Classes:../../libs/*

JFLAGS = -Xlint:deprecation -cp $(DATA_STRUCT_CLASSPATH) -d ./Classes


GRAPH_CLASSES = $(GRAPH_SRCS:.java=.class)
TREE_CLASSES = $(TREE_SRCS:.java=.class)
STACK_CLASSES = $(STACK_SRCS:.java=.class)
QUEUE_CLASSES = $(QUEUE_SRCS:.java=.class)

default: graphs trees stacks queues

graphs: $(GRAPH_CLASSES)

trees: $(TREE_CLASSES)

stacks: $(STACK_CLASSES)

queues: $(QUEUE_CLASSES)

$(CLASSES): $(SRCS)
	javac $(JFLAGS) $(SRCS)

$(GRAPH_CLASSES): $(GRAPH_SRCS)
	javac $(JFLAGS) $(GRAPH_SRCS)

$(TREE_CLASSES): $(TREE_SRCS)
	javac $(JFLAGS) $(TREE_SRCS)

$(STACK_CLASSES): $(STACK_SRCS)
	javac $(JFLAGS) $(STACK_SRCS)

$(QUEUE_CLASSES): $(QUEUE_SRCS)
	javac $(JFLAGS) $(QUEUE_SRCS)

clean:
	rm -rf ./Classes/*

check-graph: graphs
	javac $(JFLAGS) tests/DataStructuresTester.java
	java -ea -cp $(DATA_STRUCT_CLASSPATH) DataStructuresTester graphs

check-trees: trees
	javac $(JFLAGS) tests/DataStructuresTester.java
	java -ea -cp $(DATA_STRUCT_CLASSPATH) DataStructuresTester trees

check-stacks: stacks
	javac $(JFLAGS) tests/DataStructuresTester.java
	java -ea -cp $(DATA_STRUCT_CLASSPATH) DataStructuresTester stacks

check-queues: queues
	javac $(JFLAGS) tests/DataStructuresTester.java
	java -ea -cp $(DATA_STRUCT_CLASSPATH) DataStructuresTester queues

check: default
	javac $(JFLAGS) tests/DataStructuresTester.java
	java -ea -cp $(DATA_STRUCT_CLASSPATH) DataStructuresTester queues stacks trees graphs
