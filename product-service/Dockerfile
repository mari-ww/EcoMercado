# Usando a imagem oficial do Node.js
FROM node:14

# Definindo o diretório de trabalho dentro do container
WORKDIR /usr/src/app

# Copiar o package.json e o package-lock.json para o container
COPY package*.json ./

# Instalar as dependências do projeto
RUN npm install

# Copiar o restante dos arquivos do projeto para dentro do container
COPY . .

# Expor a porta em que o serviço de produtos vai rodar
EXPOSE 5001

# Comando para iniciar o servidor
CMD ["npm", "start"]

