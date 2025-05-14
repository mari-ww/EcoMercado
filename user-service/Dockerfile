# Use a imagem oficial do Node.js
FROM node:16

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

# Instala o netcat (nc)
RUN apt-get update && apt-get install -y netcat

COPY . .

EXPOSE 5001

CMD ["node", "server.js"]
