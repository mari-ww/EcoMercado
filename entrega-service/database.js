const entregas = [];

function criarEntrega(pedidoId, cliente) {
  const novaEntrega = {
    entregaId: Math.floor(Math.random() * 100000),
    pedidoId,
    status: 'Preparando entrega',
    ultimaAtualizacao: new Date().toISOString()
  };
  entregas.push(novaEntrega);
  return novaEntrega;
}

function atualizarStatus(pedidoId, novoStatus) {
  const entrega = entregas.find(e => e.pedidoId == pedidoId);
  if (entrega) {
    entrega.status = novoStatus;
    entrega.ultimaAtualizacao = new Date().toISOString();
  }
  return entrega;
}

function buscarEntrega(pedidoId) {
  return entregas.find(e => e.pedidoId == pedidoId);
}

module.exports = { criarEntrega, atualizarStatus, buscarEntrega };
