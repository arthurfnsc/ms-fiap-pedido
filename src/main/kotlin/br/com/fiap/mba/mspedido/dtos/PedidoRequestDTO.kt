package br.com.fiap.mba.mspedido.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class PedidoRequestDTO(
    val itens: List<ItemPedidoRequestDTO> = listOf()
)
