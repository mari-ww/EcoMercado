require('dotenv').config()
const fastify = require('fastify')({logger:true})
const axios = require('axios')
const CircuitBreaker = require('opossum')
const CB = require('opossum')

const USERS_SERVICE = process.env.USERS_SERVICE?.trim()
const PRODUCTS_SERVICE = process.env.PRODUCTS_SERVICE?.trim()
const PORT = process.env.PORT?.trim()

// Middleware de autenticação

fastify.addHook('onRequest' , async (request,reply) => {
  const token = request.headers['authorization']
  if(token !== '123456789'){
    reply.code(401).send({ error: "Não autorizado mané" })
  }  
})

// Circuit Breaker: Users

const usersService = async (path, headers) => {
  const fullPath = path || '/users'
  const url = `${USERS_SERVICE}${fullPath}`
  fastify.log.info(`➡️ USERS URL: ${url}`)
  const response = await axios.get(url, { headers })
  return response.data
}

const usersBreaker = new CB(usersService, {
  timeout: 3000,
  errorThresholdPercentage: 50,
  resetTimeout: 10000,
})
usersBreaker.fallback(()=> ({ error: 'Serviço de usuários indisponível' }))

// Rotas de users!
fastify.get('/users', async (request, reply) => {
  fastify.log.info(`Esse users foi ativado!`)
  try {
    const data = await usersBreaker.fire('', request.headers)
    
    reply.send(data)
  } catch (err) {
    reply.code(503).send({ error: 'Erro ao acessar /users', details: err.message })
  }
})



fastify.get('/users*' , async (request,reply) => {
  fastify.log.info(`Esse users** foi ativado!`)
  
  try {
    const path = request.raw.url.replace('/users', '')
    const data  = await usersBreaker.fire(path,request.headers)

    reply.send(data)
  } catch (error) {
    reply.code(503).send({ error: 'Erro ao acessar o serviço USUARIO! LASCOU DOIDO', details: err.message })
  }
})


// =====================================================================================
// Circuit Breaker : Products

const productsService = async(path,headers) =>{
  const fullPath = path || '/products'
  const url = `${PRODUCTS_SERVICE}${fullPath}`
  fastify.log.info(`➡️ PRODUCTS URL: ${url}`)

  const response = await axios.get(url, { headers })
  return response.data
}

const productsBreaker = new CB(productsService, {
  timeout: 3000,
  errorThresholdPercentage: 50,
  resetTimeout: 10000,
})

productsBreaker.fallback(() => ({ error: "Serviço de produtos indisponivel!" }))

// Rotas de products
fastify.get('/products' , async(request,reply) =>{ 
  fastify.log.info('Esse products foi ativado!')

  try {
    const data = await productsBreaker.fire('',request.headers)
    reply.send(data)
  } catch (error) {
    reply.code(503).send({ error: 'Erro ao acessar /products',detaisl:er.message })
  }

 })


fastify.get('/products*' , async (request,reply) => {
  fastify.log.info(`Esse products** foi ativado!`)

  try {
    const path = request.raw.url.replace('/products', '')
    const data  = await usersBreaker.fire(path,request.headers)

    reply.send(data)
  } catch (error) {
    reply.code(503).send({ error: 'Erro ao acessar o serviço PRODUTOS!! LASCOU DOIDO EM DOBRO', details: err.message })
  }
})






// inicar o servidor!!
fastify.listen({ port: PORT || 8080, host: '0.0.0.0' } , (err,address) => {
  if (err){
    fastify.log.error(err)
    process.exit(1)
  }
  fastify.log.info(`🟢 API Gateway Fastify rodando em ${address}`)
})