package br.com.fiap.mba.mspedido.architecture

import br.com.fiap.mba.mspedido.MsPedidoApplication
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS
import com.tngtech.archunit.core.importer.ImportOptions
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class CamadaResourceTest {

    companion object {

        private const val DIRETORIO_ANALISE = "..resources.impl.."

        private val BASE_PATH = MsPedidoApplication::class.java.`package`.name

        private val classes =
            ClassFileImporter(ImportOptions().with(DO_NOT_INCLUDE_TESTS))
                .importPackages(BASE_PATH)
    }

    @Test
    fun `nenhuma controller deve acessar classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().accessClassesThat()
            .resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `nenhuma controller deve depender de classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().dependOnClassesThat()
            .resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `uma controller só pode acessar classes`() {
        classes().that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyAccessClassesThat()
            .resideInAnyPackage(
                "..converters..",
                "..java..",
                "..kotlin..",
                "..openapi..",
                "..reactor..",
                "..resources..",
                "..services..",
                "..springframework.."
            )
            .check(classes)
    }
}
