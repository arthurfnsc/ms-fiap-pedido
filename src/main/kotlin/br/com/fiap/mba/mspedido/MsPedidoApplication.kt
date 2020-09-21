package br.com.fiap.mba.mspedido

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.SimpleCommandLinePropertySource
import springfox.documentation.oas.annotations.EnableOpenApi
import kotlin.system.exitProcess

@EnableOpenApi
@SpringBootApplication
class MsPedidoApplication

@Suppress("SpreadOperator")
fun validateProfile(args: Array<String>) {

    val source = SimpleCommandLinePropertySource(*args)

    require(
        source.containsProperty("spring.profiles.active") ||
            System.getenv().containsKey("SPRING_PROFILES_ACTIVE")
    ) {
        System.err.println("No Profile set, exiting")
        exitProcess(0)
    }
}

@Suppress("SpreadOperator")
fun main(args: Array<String>) {

    validateProfile(args)

    runApplication<MsPedidoApplication>(*args)
}
