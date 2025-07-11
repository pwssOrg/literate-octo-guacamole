# Define the pom directory path
POM_DIR := pwss/cryptographic_algorithm

# Default target - nothing to do here, but it helps users
.PHONY: all
all:
	@echo "Please use 'make build' to build the project."

# Build target
.PHONY: build
build:
	cd $(POM_DIR) && mvn clean install

# Clean target
.PHONY: clean
clean:
	cd $(POM_DIR) && mvn clean

# Help target
.PHONY: help
help:
	@echo "Available targets:"
	@echo "  make build    - Build the project using Maven"
	@echo "  make clean   - Clean the project using Maven"
	@echo "  make help    - Display this help message"
