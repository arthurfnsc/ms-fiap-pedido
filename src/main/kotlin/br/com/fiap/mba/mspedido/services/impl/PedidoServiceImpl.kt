package br.com.fiap.mba.mspedido.services.impl

import br.com.fiap.mba.mspedido.dtos.ItemPedidoRequestDTO
import br.com.fiap.mba.mspedido.dtos.PedidoRequestDTO
import br.com.fiap.mba.mspedido.models.Item
import br.com.fiap.mba.mspedido.models.Pedido
import br.com.fiap.mba.mspedido.repositories.PedidoRepository
import br.com.fiap.mba.mspedido.services.PedidoService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class PedidoServiceImpl(
    private val pedidoRepository: PedidoRepository
) : PedidoService {

    companion object {

        private const val MIN_CEP = 10000000
        private const val MAX_CEP = 999999999
    }

    override fun criar(pedido: PedidoRequestDTO?): Mono<String> {

        val model = this.completarDadosPedido(pedido!!)

        val registro = this.pedidoRepository.save(model)

        return registro
            .map { it.id }
    }

    override fun recuperar(id: String?): Mono<Pedido> {
        return this.pedidoRepository.findById(id.toString())
    }

    private fun completarDadosItem(dto: ItemPedidoRequestDTO): Item =
        Item(
            nome = this.generateRandom(BigDecimal.TEN.toInt()),
            descricao = this.generateRandom(BigDecimal.TEN.toInt()),
            quantidade = dto.quantidade,
            valor = generateRandom(0, BigDecimal.TEN.toInt()),
            observacao = dto.observacao
        )

    private fun completarDadosPedido(dto: PedidoRequestDTO): Pedido {

        val itens = arrayListOf<Item>()

        val desconto = generateRandom(0, BigDecimal.TEN.toInt())
        val taxaEntrega = generateRandom(0, BigDecimal.TEN.toInt())

        val total = BigDecimal.ZERO
        total.add(taxaEntrega)
        total.subtract(desconto)

        dto
            .itens
            .forEach {

                val item = completarDadosItem(it)

                val totalItem = item.valor?.multiply(BigDecimal(item.quantidade))

                total.add(totalItem)

                itens.add(item)
            }

        return Pedido(
            itens = itens,
            descricao = this.generateRandom(BigDecimal.TEN.toInt()),
            bairro = this.generateRandom(BigDecimal.TEN.toInt()),
            cidade = this.generateRandom(BigDecimal.TEN.toInt()),
            estado = "SP",
            numero = generateRandom(0, BigDecimal.TEN.toInt()).toInt(),
            cep = generateRandom(MIN_CEP, MAX_CEP).toInt(),
            taxaEntrega = taxaEntrega,
            desconto = desconto,
            status = "EM PREPARO",
            total = BigDecimal.ONE
        )
    }

    private fun generateRandom(min: Int, max: Int): BigDecimal {

        val minino = BigDecimal(min)
        val maximo = BigDecimal(max)
        val random = BigDecimal(Math.random())

        return minino.add(random)
                .multiply(maximo.subtract(minino)).setScale(2, RoundingMode.HALF_EVEN)
    }

    private fun generateRandom(size: Int): String {

        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return (1..size)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}
