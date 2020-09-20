package br.com.fiap.mba.mspedido.resources.impl

import org.openapi.cambio.server.api.V1Api
import org.openapi.cambio.server.model.Pedido
import org.openapi.cambio.server.model.PedidoPostRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class PedidoResourcesImpl : V1Api {

    override fun criarPedido(
        pedidoPostRequest: Mono<PedidoPostRequest>?,
        exchange: ServerWebExchange?
    ): Mono<ResponseEntity<Pedido>> {
        return super.criarPedido(pedidoPostRequest, exchange)
    }

    override fun recuperarPedido(
        id: UUID?,
        exchange: ServerWebExchange?
    ): Mono<ResponseEntity<Pedido>> {
        return super.recuperarPedido(id, exchange)
    }
}
