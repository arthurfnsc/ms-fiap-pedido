package br.com.fiap.mba.mspedido.exceptions

import java.lang.RuntimeException

class PedidoNaoEncontrado(mensagem: String) : RuntimeException(mensagem)
