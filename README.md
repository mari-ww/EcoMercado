# 🛒 EcoMercado: Integração gRPC + REST

Este projeto demonstra como integrar serviços gRPC e REST em uma arquitetura de microsserviços, oferecendo uma comunicação eficiente e escalável entre os componentes internos e uma interface flexível para clientes externos.

## 📦 Estrutura do Projeto

```
ecomercado/
├── auth-service/           # Serviço de autenticação (gRPC)
├── gateway-service/        # Gateway REST que se comunica com os serviços gRPC
├── iot-service/            # Serviço de IoT (gRPC)
├── order-service/          # Serviço de pedidos (gRPC)
└── product-service/        # Serviço de produtos (gRPC)
```

## 🔗 Comunicação entre Serviços

### 🧩 REST

* Utiliza HTTP/1.1 com JSON.
* Ideal para comunicação com clientes web e aplicações externas.
* Implementado no `gateway-service`.

### ⚡ gRPC

* Utiliza HTTP/2 com Protocol Buffers.
* Ideal para comunicação interna entre microsserviços devido à sua alta performance e baixo overhead.
* Implementado nos serviços: `auth-service`, `iot-service`, `order-service` e `product-service`.

## 🚀 Como Funciona

1.  **Definição dos Serviços gRPC:**
    Os serviços gRPC são definidos em arquivos `.proto` dentro de cada serviço correspondente. Exemplo para o `product-service`:

    ```protobuf
    syntax = "proto3";

    service ProductService {
      rpc GetProduct (ProductRequest) returns (ProductResponse);
    }

    message ProductRequest {
      string id = 1;
    }

    message ProductResponse {
      string name = 1;
      float price = 2;
    }
    ```

2.  **Geração de Código:**
    Utilize o compilador `protoc` para gerar o código específico para a linguagem de cada serviço. No caso de Python:

    ```bash
    protoc --proto_path=. --python_out=./product_service --grpc_python_out=./product_service product.proto
    ```
    *(Note: Ajuste os diretórios de saída conforme a estrutura do seu projeto)*

3.  **Implementação dos Serviços:**
    Implemente a lógica dos serviços gRPC nos diretórios correspondentes (`auth-service`, `iot-service`, etc.) e a API REST no `gateway-service`. O gateway utilizará os stubs gRPC gerados para se comunicar com os outros serviços.

4.  **Integração:**
    O `gateway-service` se comunica com os serviços gRPC utilizando os stubs gerados pelo `protoc`. Exemplo em Python no gateway:

    ```python
    import grpc
    from product_service import product_pb2
    from product_service import product_pb2_grpc

    channel = grpc.insecure_channel('localhost:50051') # Endereço do product-service
    stub = product_pb2_grpc.ProductServiceStub(channel)
    response = stub.GetProduct(product_pb2.ProductRequest(id='123'))
    print(f"Produto: {response.name}, Preço: {response.price}")
    ```

## 🧪 Testando a Integração

1.  **Inicie os Serviços gRPC:**
    Abra diferentes terminais e execute os servidores gRPC:

    ```bash
    python auth-service/server.py
    python iot-service/server.py
    python order-service/server.py
    python product-service/server.py
    ```
    *(Certifique-se de que os arquivos `server.py` existam em cada diretório e contenham a lógica para iniciar o servidor gRPC)*

2.  **Inicie o Serviço REST (Gateway):**
    Em outro terminal, execute o gateway REST:

    ```bash
    python gateway-service/app.py
    ```
    *(Certifique-se de que o arquivo `app.py` contenha a lógica para iniciar o servidor REST e rotear as requisições para os serviços gRPC)*

3.  **Faça uma Requisição REST:**
    Utilize `curl` ou outra ferramenta HTTP para interagir com o gateway REST:

    ```bash
    curl http://localhost:8000/products/123
    ```
    O `gateway-service` irá internamente chamar o `product-service` via gRPC para obter os dados do produto e retornar a resposta em JSON.

## 📝 Conclusão

Integrar serviços gRPC e REST permite aproveitar os benefícios de cada tecnologia: o alto desempenho e baixo overhead do gRPC para a comunicação interna entre microsserviços e a ampla compatibilidade e facilidade de uso do REST para a comunicação com clientes externos. Este projeto serve como um exemplo prático de como essa integração pode ser implementada em uma arquitetura de microsserviços.

---
