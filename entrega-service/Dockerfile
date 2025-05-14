FROM node:18

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de dependência e instala
COPY package*.json ./
RUN npm install

# Copia os demais arquivos da aplicação
COPY . .

# Copia o script e dá permissão de execução
COPY wait-for-rabbitmq.sh /wait-for-rabbitmq.sh
RUN chmod +x /wait-for-rabbitmq.sh

# Comando para iniciar o container
CMD ["node", "index.js"]
