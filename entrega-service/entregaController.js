const express = require('express');
const router = express.Router();
const { criarEntrega, atualizarStatus, buscarEntrega } = require('./database');

router.post('/', (req, res) => {
  const { pedidoId, cliente } = req.body;
  const entrega = criarEntrega(pedidoId, cliente);
  res.status(201).json(entrega);
});

router.get('/:pedidoId', (req, res) => {
  const entrega = buscarEntrega(req.params.pedidoId);
  if (entrega) return res.json(entrega);
  res.status(404).json({ erro: 'Entrega não encontrada' });
});

router.put('/:pedidoId/status', (req, res) => {
  const { status } = req.body;
  const entrega = atualizarStatus(req.params.pedidoId, status);
  if (entrega) return res.json(entrega);
  res.status(404).json({ erro: 'Entrega não encontrada' });
});

module.exports = router;
