# Define the pom directory path
POM_DIR := algorithm_hash_extraction

.PHONY: all
all:
	@echo "Please use 'make build' to build the project."

# Local build (using GPG)
.PHONY: build
build:
	cd $(POM_DIR) && mvn clean install

# CI build (utan GPG)
.PHONY: github_build
github_build:
	cd $(POM_DIR) && mvn -B -Dgpg.skip=true clean install

# Test
.PHONY: test
test:
	cd $(POM_DIR) && mvn clean test

# Clean
.PHONY: clean
clean:
	cd $(POM_DIR) && mvn clean

# Help
.PHONY: help
help:
	@echo "Available targets:"
	@echo "  make github_build - Build without GPG (CI)"
	@echo "  make build        - Build with GPG (local)"
	@echo "  make test         - Test the project"
	@echo "  make clean        - Clean the project"
