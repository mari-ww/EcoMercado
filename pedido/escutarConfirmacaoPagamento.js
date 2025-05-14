const amqp = require('amqplib');

async function escutarConfirmacaoPagamento() {
  const connection = await amqp.connect('amqp://rabbitmq');
  const channel = await connection.createChannel();
  const queue = 'pagamento-confirmado';

  await channel.assertQueue(queue, { durable: true });
  console.log('💸 Aguardando confirmação de pagamento...');

  channel.consume(queue, (msg) => {
    const mensagem = JSON.parse(msg.content.toString());
    if (mensagem.status === 'sucesso' || mensagem.status === 'confirmado') {
      console.log(`✅ Pagamento confirmado para o pedido ${mensagem.pedidoId}`);
    }
  }, { noAck: true });
}

escutarConfirmacaoPagamento();






