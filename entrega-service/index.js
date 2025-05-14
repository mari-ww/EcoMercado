const express = require('express');
const app = express();
const entregaRoutes = require('./entregaController');

app.use(express.json());
app.use('/entregas', entregaRoutes);

app.listen(3002, () => console.log('🚚 entrega-service rodando na porta 3002'));

require('./consumidor');
