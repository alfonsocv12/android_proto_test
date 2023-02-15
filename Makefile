proto-create:
	wget https://github.com/alfonsocv12/backend_proto_test/releases/download/0.1.1/backend_proto_test.zip
	unzip -o backend_proto_test -d app/src/main/proto
	rm -rf backend_proto_test.zip