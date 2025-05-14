const entregas = [];

const statusSequencia = [
  'Pendente',
  'Aguardando Pagamento',
  'Pagamento Efetuado',
  'Aguardando Entrega',
  'Entrega Efetuada'
];

function criarEntrega(pedidoId, cliente) {
  if (!pedidoId || !cliente) {
    throw new Error('Pedido ID e Cliente são obrigatórios');
  }

  const novaEntrega = {
    entregaId: Math.floor(Math.random() * 100000),
    pedidoId,
    cliente,
    status: statusSequencia[0],  // A primeira fase é "Pendente"
    ultimaAtualizacao: new Date().toISOString()
  };
  entregas.push(novaEntrega);
  return novaEntrega;
}

function atualizarStatus(pedidoId, novoStatus) {
  if (!pedidoId || !novoStatus) {
    throw new Error('Pedido ID e Novo Status são obrigatórios');
  }

  const entrega = entregas.find(e => e.pedidoId == pedidoId);
  if (entrega) {
    const statusIndex = statusSequencia.indexOf(entrega.status);
    const novoStatusIndex = statusSequencia.indexOf(novoStatus);
    
    if (novoStatusIndex > statusIndex) {
      entrega.status = novoStatus;
      entrega.ultimaAtualizacao = new Date().toISOString();
      return entrega;
    } else {
      throw new Error('Não é possível retroceder no status');
    }
  }

  throw new Error('Entrega não encontrada');
}

function buscarEntrega(pedidoId) {
  const entrega = entregas.find(e => e.pedidoId == pedidoId);
  if (!entrega) {
    throw new Error('Entrega não encontrada');
  }
  return entrega;
}

module.exports = { criarEntrega, atualizarStatus, buscarEntrega };
