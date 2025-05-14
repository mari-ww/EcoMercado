// pagamento/consumidorPagamento.js
const amqp = require('amqplib');

async function processarPagamentos() {
  const connection = await amqp.connect('amqp://rabbitmq');
  const channel = await connection.createChannel();
  const queue = 'novo-pagamento';

  await channel.assertQueue(queue, { durable: true });
  console.log('📥 Aguardando pedidos para processar pagamento...');

  channel.consume(queue, async (msg) => {
    const pedido = JSON.parse(msg.content.toString());
    console.log('💳 Processando pagamento do pedido:', pedido);

    // Simula um pagamento bem-sucedido após 2s
    await new Promise(res => setTimeout(res, 2000));
    
    const confirmacao = {
      pedidoId: pedido.id,
      status: 'sucesso'
    };

    const confirmacaoQueue = 'pagamento-confirmado';
    await channel.assertQueue(confirmacaoQueue, { durable: true });
    channel.sendToQueue(confirmacaoQueue, Buffer.from(JSON.stringify(confirmacao)), { persistent: true });

    console.log(`✅ Pagamento confirmado para o pedido ${pedido.id}`);
    channel.ack(msg);
  });
}

// 👇 Chama a função
processarPagamentos();



