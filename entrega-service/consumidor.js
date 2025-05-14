const amqp = require('amqplib');
const { criarEntrega, atualizarStatus } = require('./database'); // importa função de criação e atualização
const { enviarParaPagamento } = require('./enviarPagamento');

async function consumirPedidos() {
  try {
    const connection = await amqp.connect('amqp://rabbitmq');
    const channel = await connection.createChannel();

    const fila = 'fila_pedidos';
    await channel.assertQueue(fila, { durable: true });

    console.log('📥 Aguardando pedidos...');

    channel.consume(fila, (msg) => {
      if (msg !== null) {
        try {
          const pedido = JSON.parse(msg.content.toString());
          console.log('✅ Pedido recebido:', pedido);

          const novaEntrega = criarEntrega(pedido.id, pedido.cliente);
          console.log('📦 Entrega criada:', novaEntrega);

          enviarParaPagamento(pedido);

          channel.ack(msg); // Confirma que a mensagem foi processada
        } catch (erro) {
          console.error('Erro ao processar mensagem:', erro);
        }
      }
    });
  } catch (error) {
    console.error('Erro ao consumir pedidos:', error);
  }
}

consumirPedidos();
