const amqp = require('amqplib');

async function enviarParaPagamento(pedido) {
  try {
    const connection = await amqp.connect('amqp://rabbitmq');
    const channel = await connection.createChannel();

    const fila = 'novo-pagamento';
    await channel.assertQueue(fila, { durable: true });

    channel.sendToQueue(fila, Buffer.from(JSON.stringify(pedido)), {
      persistent: true,
    });

    console.log('📤 Pedido enviado para PAGAMENTO:', pedido);

    await channel.close();
    await connection.close();
  } catch (error) {
    console.error('Erro ao enviar para pagamento:', error);
  }
}

// Exemplo de pedido
const pedido = {
  id: 1234,
  cliente: 'Gabriela',
  total: 199.90,
  produtos: ['Camiseta', 'Tênis'],
};

enviarParaPagamento(pedido);
