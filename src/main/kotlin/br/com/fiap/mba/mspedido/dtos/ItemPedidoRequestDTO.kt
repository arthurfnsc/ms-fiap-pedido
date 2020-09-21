package br.com.fiap.mba.mspedido.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class ItemPedidoRequestDTO(
    val quantidade: Int,
    val observacao: String
)
