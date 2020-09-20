package br.com.fiap.mba.mspedido

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
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

    val app = SpringApplication(MsPedidoApplication::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.webApplicationType = WebApplicationType.SERVLET
    app.run(*args)
}
