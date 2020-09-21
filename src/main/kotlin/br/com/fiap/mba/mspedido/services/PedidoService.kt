package br.com.fiap.mba.mspedido.services

import br.com.fiap.mba.mspedido.dtos.PedidoRequestDTO
import br.com.fiap.mba.mspedido.models.Pedido
import reactor.core.publisher.Mono

interface PedidoService {

    fun criar(pedido: PedidoRequestDTO?): Mono<String>

    fun recuperar(id: String?): Mono<Pedido>
}
