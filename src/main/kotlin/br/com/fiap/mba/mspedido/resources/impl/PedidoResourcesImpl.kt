package br.com.fiap.mba.mspedido.resources.impl

import br.com.fiap.mba.mspedido.converters.PedidoConverter
import br.com.fiap.mba.mspedido.services.PedidoService
import org.openapi.pedido.server.api.V1Api
import org.openapi.pedido.server.model.Pedido
import org.openapi.pedido.server.model.PedidoId
import org.openapi.pedido.server.model.PedidoPostRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
class PedidoResourcesImpl(
    private val conversor: PedidoConverter,
    private val service: PedidoService
) : V1Api {

    override fun criarPedido(
        pedidoPostRequest: Mono<PedidoPostRequest>?,
        exchange: ServerWebExchange?
    ): Mono<ResponseEntity<PedidoId>>? {

        return pedidoPostRequest
            ?.map {
                this.conversor.from(it)
            }?.map {
                this.service.criar(it)
            }?.flatMap {
                it
            }?.map {

                val response = PedidoId()
                response.id = it

                ResponseEntity(response, HttpStatus.CREATED)
            }
    }

    override fun recuperarPedido(
        id: String?,
        exchange: ServerWebExchange?
    ): Mono<ResponseEntity<Pedido>> {

        return this.service.recuperar(id)
            .map { this.conversor.from(it) }
            .map { ResponseEntity(it, HttpStatus.OK) }
    }
}
