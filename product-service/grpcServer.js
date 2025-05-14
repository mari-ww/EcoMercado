const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
const products = require('./mockProducts'); // simula dados

const packageDef = protoLoader.loadSync('proto/product.proto');
const grpcObject = grpc.loadPackageDefinition(packageDef);
const productPackage = grpcObject.product;

function getProduct(call, callback) {
  const product = products.find(p => p.id === call.request.id);
  if (!product) {
    return callback({
      code: grpc.status.NOT_FOUND,
      message: "Produto não encontrado"
    });
  }
  callback(null, product);
}

const server = new grpc.Server();
server.addService(productPackage.ProductService.service, { getProduct });

server.bindAsync(
  '0.0.0.0:50051',
  grpc.ServerCredentials.createInsecure(),
  () => {
    console.log('Servidor gRPC de Produtos rodando na porta 50051');
    server.start();
  }
);
