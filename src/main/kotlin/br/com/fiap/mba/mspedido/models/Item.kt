package br.com.fiap.mba.mspedido.models

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal

@KotlinBuilder
data class Item(
    val nome: String?,
    val descricao: String,
    val quantidade: Int,
    val valor: BigDecimal?,
    val observacao: String
)
