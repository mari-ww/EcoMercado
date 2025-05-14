# Usar a imagem base do Node.js
FROM node:16

# Definir o diretório de trabalho
WORKDIR /usr/src/app

# Copiar o package.json e instalar as dependências
COPY package*.json ./
RUN npm install

# Instala o netcat (nc)
RUN apt-get update && apt-get install -y netcat

# Copiar o restante do código
COPY . .

# Copiar o script de espera do RabbitMQ e dar permissão de execução
COPY wait-for-rabbitmq.sh /wait-for-rabbitmq.sh
RUN chmod +x /wait-for-rabbitmq.sh

# Expor a porta, se necessário (ajustar conforme seu serviço)
EXPOSE 3000

# Definir o comando para iniciar o produtor após aguardar o RabbitMQ
CMD ["./wait-for-rabbitmq.sh", "node", "produtor.js"]
