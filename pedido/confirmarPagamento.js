const amqp = require('amqplib');

async function confirmarPagamento(pedidoId) {
  const conn = await amqp.connect('amqp://rabbitmq');
  const channel = await conn.createChannel();
  const queue = 'pagamento-confirmado';

  const mensagem = JSON.stringify({ pedidoId, status: 'confirmado' });

  await channel.assertQueue(queue, { durable: true });
  channel.sendToQueue(queue, Buffer.from(mensagem), { persistent: true });

  console.log(`📤 Pagamento confirmado para o pedido ${pedidoId}`);
  await channel.close();
  await conn.close();
}

confirmarPagamento(123);


