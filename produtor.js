const amqp = require('amqplib');  

// Importa a biblioteca amqplib para trabalhar com RabbitMQ
// (enviar pedido)

async function enviarPedido(pedido) {
  try {
    const connection = await amqp.connect('amqp://rabbitmq');
    const channel = await connection.createChannel();

    const fila = 'fila_pedidos';
    await channel.assertQueue(fila, { durable: true });

    channel.sendToQueue(fila, Buffer.from(JSON.stringify(pedido)), {
      persistent: true
    });

    console.log('📤 Pedido enviado para a fila:', pedido);

    await channel.close();
    await connection.close();
  } catch (error) {
    console.error('Erro ao enviar pedido:', error);
  }
}

// Exemplo de pedido
enviarPedido({
  id: 1234,
  cliente: 'Gabriela',
  total: 199.90,
  produtos: ['Camiseta', 'Tênis']
});
