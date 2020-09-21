package br.com.fiap.mba.mspedido.converters

import br.com.fiap.mba.mspedido.dtos.PedidoRequestDTO
import br.com.fiap.mba.mspedido.models.Pedido
import org.mapstruct.Mapper
import org.openapi.cambio.server.model.PedidoPostRequest
import org.openapi.cambio.server.model.Pedido as PedidoSchema

@Mapper(
    componentModel = "spring",
    uses = [ItemCoverter::class]
)
interface PedidoConverter {

    fun from(element: Pedido): PedidoSchema

    fun from(element: PedidoPostRequest): PedidoRequestDTO
}
