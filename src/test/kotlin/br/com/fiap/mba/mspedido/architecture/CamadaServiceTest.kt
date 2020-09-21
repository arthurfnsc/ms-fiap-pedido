package br.com.fiap.mba.mspedido.architecture

import br.com.fiap.mba.mspedido.MsPedidoApplication
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS
import com.tngtech.archunit.core.importer.ImportOptions
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class CamadaServiceTest {

    companion object {

        private const val DIRETORIO_ANALISE = "..services.impl.."

        private val BASE_PATH = MsPedidoApplication::class.java.`package`.name

        private val classes =
            ClassFileImporter(ImportOptions().with(DO_NOT_INCLUDE_TESTS))
                .importPackages(BASE_PATH)
    }

    @Test
    fun `nenhuma service deve acessar classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().dependOnClassesThat().resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `nenhuma service deve depender de classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().dependOnClassesThat().resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `uma service só pode acessar classes`() {
        classes().that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyAccessClassesThat()
            .resideInAnyPackage(
                "..dtos..",
                "..exceptions..",
                "..gateways..",
                "..java..",
                "..kotlin..",
                "..models..",
                "..reactor..",
                "..repositories..",
                "..services..",
                "..springframework.."
            )
            .check(classes)
    }

    @Test
    fun `uma service só pode ser acessada`() {
        classes()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyBeAccessed()
            .byAnyPackage(
                "..services.."
            )
            .check(classes)
    }

    @Test
    fun `uma service só pode ter dependência de classes`() {
        classes()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyHaveDependentClassesThat()
            .resideInAnyPackage(
                "..services.."
            )
            .check(classes)
    }
}
