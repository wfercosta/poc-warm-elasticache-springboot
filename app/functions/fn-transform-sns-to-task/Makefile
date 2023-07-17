package:
	cd ./src && zip -rv ../bin/function.zip . \
		-x "*__pycache__*" \
		-x "*test*" \
		-x .DS_Store 

test:
	cd ./src && python -m unittest -v

all: test package

	