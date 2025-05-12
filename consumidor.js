const amqp = require('amqplib');

// Executa a função para começar a consumir pedidos
// (receber pedido)

async function consumirPedidos() {
  try {
    const connection = await amqp.connect('amqp://rabbitmq');
    const channel = await connection.createChannel();

    const fila = 'fila_pedidos';
    await channel.assertQueue(fila, { durable: true });

    console.log('📥 Aguardando pedidos...');

    channel.consume(fila, (msg) => {
      if (msg !== null) {
        const pedido = JSON.parse(msg.content.toString());
        console.log('✅ Pedido recebido:', pedido);

        // Aqui você pode enviar e-mail, atualizar banco de dados, etc.

        channel.ack(msg); // Confirma que a mensagem foi processada
      }
    });
  } catch (error) {
    console.error('Erro ao consumir pedidos:', error);
  }
}

consumirPedidos();
