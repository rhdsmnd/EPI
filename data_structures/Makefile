SHELL = bash

GRAPH_SRCS := $(wildcard com/rhdes/graphs/*.java)
TREE_SRCS := $(wildcard com/rhdes/trees/*.java)

SRCS := $(GRAPH_SRCS) $(TREE_SRCS)

DATA_STRUCT_CLASSPATH = ./Classes:../../libs/*

JFLAGS = -Xlint:deprecation -cp $(DATA_STRUCT_CLASSPATH) -d ./Classes


GRAPH_CLASSES = $(GRAPH_SRCS:.java=.class)
TREE_CLASSES = $(TREE_SRCS:.java=.class)

default: graphs trees

graphs: $(GRAPH_CLASSES)

trees: $(TREE_CLASSES)

$(CLASSES): $(SRCS)
	javac $(JFLAGS) $(SRCS)

$(GRAPH_CLASSES): $(GRAPH_SRCS)
	javac $(JFLAGS) $(GRAPH_SRCS)

$(TREE_CLASSES): $(TREE_SRCS)
	javac $(JFLAGS) $(TREE_SRCS)

clean:
	rm -rf ./Classes/*
