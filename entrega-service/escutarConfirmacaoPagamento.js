const amqp = require('amqplib');
const { atualizarStatus } = require('./database');

async function escutarConfirmacaoPagamento() {
  const connection = await amqp.connect('amqp://rabbitmq');
  const channel = await connection.createChannel();
  const queue = 'pagamento-confirmado';

  await channel.assertQueue(queue, { durable: true });
  console.log('💸 Aguardando confirmação de pagamento (entrega-service)...');

  channel.consume(queue, async (msg) => {
    const mensagem = JSON.parse(msg.content.toString());
    const pedidoId = mensagem.pedidoId;

    try {
      atualizarStatus(pedidoId, 'Pagamento Efetuado');
      console.log(`✅ Status atualizado para 'Pagamento Efetuado' para o pedido ${pedidoId}`);

      // Simula avanço para "Aguardando Entrega" após 2s
      setTimeout(() => {
        try {
          atualizarStatus(pedidoId, 'Aguardando Entrega');
          console.log(`📦 Status atualizado para 'Aguardando Entrega' para o pedido ${pedidoId}`);
        } catch (erro) {
          console.error('Erro ao atualizar para "Aguardando Entrega":', erro);
        }
      }, 2000);
    } catch (erro) {
      console.error('Erro ao atualizar status para "Pagamento Efetuado":', erro);
    }

    channel.ack(msg);
  });
}

escutarConfirmacaoPagamento();
