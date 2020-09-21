package br.com.fiap.mba.mspedido.repositories

import br.com.fiap.mba.mspedido.models.Pedido
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository : ReactiveCrudRepository<Pedido, String>
