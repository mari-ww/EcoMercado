const axios = require('axios');
const express = require('express');
const app = express();
const port = 5001;

let produtos = [];  // Lista de produtos temporária

app.use(express.json());

// Endpoint para cadastrar produto
app.post('/produtos', (req, res) => {
  const produto = req.body;
  produtos.push(produto);
  res.status(201).send(produto);
});

// Endpoint para listar todos os produtos
app.get('/produtos', (req, res) => {
  res.json(produtos);
});

// Endpoint de exemplo
app.get('/', (req, res) => {
  res.send('Produtos - Microserviço');
});

app.listen(port, () => {
  console.log(`Serviço de Produtos rodando na porta ${port}`);
});

// rota pra buscar dados do usuário
app.get('/usuario-de-produto/:id', async (req, res) => {
    try {
      const { id } = req.params;
  
      // Chamada REST para o user-service
      const resposta = await axios.get(`http://user-service:5000/users/${id}`);
  
      res.json({
        mensagem: 'Dados do usuário retornados via REST!',
        usuario: resposta.data,
      });
    } catch (erro) {
      res.status(500).json({ erro: 'Erro ao buscar usuário', detalhe: erro.message });
    }
  });
  