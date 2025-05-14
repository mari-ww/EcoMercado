// entrega-service/consumidor.js

const amqp = require('amqplib');
const { criarEntrega } = require('./database'); // importa função de criação

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

        // Cria nova entrega com base no pedido
        const novaEntrega = criarEntrega(pedido.id, pedido.cliente);
        console.log('📦 Entrega criada:', novaEntrega);

        channel.ack(msg); // Confirma que a mensagem foi processada
      }
    });
  } catch (error) {
    console.error('Erro ao consumir pedidos:', error);
  }
}

consumirPedidos();
