# Usando a imagem do Node.js
FROM node:14

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie os arquivos do diretório atual para o diretório de trabalho no container
COPY . .

# Instale as dependências do projeto
RUN npm install

# Comando para iniciar o servidor
CMD ["node", "index.js"]
