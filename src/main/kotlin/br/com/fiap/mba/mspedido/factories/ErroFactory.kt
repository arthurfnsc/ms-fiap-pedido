package br.com.fiap.mba.mspedido.factories

import org.openapi.pedido.server.model.Erro
import org.openapi.pedido.server.model.ParametroInvalido
import org.springframework.http.HttpStatus

class ErroFactory {

    private constructor()

    companion object {

        fun criar(
            httpStatus: HttpStatus
        ): Erro {

            val erro = Erro()
            erro.httpCode = httpStatus.value()
            erro.httpMessage = httpStatus.reasonPhrase
            erro.parametrosInvalidos = emptyList()

            return erro
        }

        fun criar(
            httpStatus: HttpStatus,
            descricao: String
        ): Erro {

            val erro = criar(httpStatus)
            erro.descricao = descricao

            return erro
        }

        fun criar(
            httpStatus: HttpStatus,
            descricao: String,
            parametrosInvalidos: List<ParametroInvalido>
        ): Erro {

            val erro = criar(httpStatus, descricao)
            erro.parametrosInvalidos = parametrosInvalidos

            return erro
        }
    }
}
