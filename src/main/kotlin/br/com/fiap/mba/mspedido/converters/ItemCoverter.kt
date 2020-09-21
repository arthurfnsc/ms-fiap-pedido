package br.com.fiap.mba.mspedido.converters

import br.com.fiap.mba.mspedido.dtos.ItemPedidoRequestDTO
import br.com.fiap.mba.mspedido.models.Item
import org.mapstruct.Mapper
import org.openapi.pedido.server.model.ItemPedidoRequest
import org.openapi.pedido.server.model.Item as ItemSchema

@Mapper(
    componentModel = "spring"
)
interface ItemCoverter {

    fun from(element: Item): ItemSchema

    fun from(element: ItemPedidoRequest): ItemPedidoRequestDTO
}
