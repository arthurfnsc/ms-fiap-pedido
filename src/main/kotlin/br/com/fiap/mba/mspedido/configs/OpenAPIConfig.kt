package br.com.fiap.mba.mspedido.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class OpenAPIConfig {

    @Bean
    fun docket(): Docket =
        Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.fiap.mba.mspedido.resources.impl"))
            .build()
            .apiInfo(this.getApiInfo())

    private fun getApiInfo(): ApiInfo {

        val description = StringBuilder("")

        return ApiInfoBuilder()
            .title("Pedidos REST API")
            .description(description.toString())
            .version("1.0.0")
            .build()
    }
}
