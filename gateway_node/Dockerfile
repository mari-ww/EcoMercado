# Usa imagem oficial Node.js
FROM node:18

# Cria diretório da aplicação
WORKDIR /usr/src/app

# Copia os arquivos package.json e package-lock.json
COPY package*.json ./

# Instala dependências
RUN npm install

# Copia os arquivos do projeto (inclusive .env)
COPY . .

# Expõe a porta da aplicação
EXPOSE 8080

# Inicia a aplicação
CMD ["node", "index.js"]
