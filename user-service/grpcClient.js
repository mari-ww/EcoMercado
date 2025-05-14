const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');

const packageDef = protoLoader.loadSync('proto/product.proto');
const grpcObject = grpc.loadPackageDefinition(packageDef);
const productPackage = grpcObject.product;

const client = new productPackage.ProductService(
  'product-service:50051', // nome do container no docker-compose
  grpc.credentials.createInsecure()
);

module.exports = client;
