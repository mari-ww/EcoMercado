# Use a imagem oficial do Node.js
FROM node:16

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

# Instala o netcat (nc) no segundo Dockerfile
RUN apt-get update && apt-get install -y netcat

COPY . .

# Expor as portas de ambos os serviços (ajuste conforme a necessidade)
EXPOSE 5000
EXPOSE 5001

CMD ["node", "server.js"]

