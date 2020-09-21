package br.com.fiap.mba.mspedido.models

import com.github.pozo.KotlinBuilder
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@KotlinBuilder
data class Pedido(
    @Id
    val id: String? = null,
    val itens: List<Item> = listOf(),
    val descricao: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val numero: Int,
    val cep: Int,
    val taxaEntrega: BigDecimal,
    val desconto: BigDecimal,
    val total: BigDecimal,
    val status: String,
    val criadoEm: LocalDateTime = LocalDateTime.now()
)
