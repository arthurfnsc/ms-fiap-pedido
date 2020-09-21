package br.com.fiap.mba.mspedido.architecture

import br.com.fiap.mba.mspedido.MsPedidoApplication
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS
import com.tngtech.archunit.core.importer.ImportOptions
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class CamadaDTOTest {

    companion object {

        private const val DIRETORIO_ANALISE = "..dtos.."

        private val BASE_PATH = MsPedidoApplication::class.java.`package`.name

        private val classes =
            ClassFileImporter(ImportOptions().with(DO_NOT_INCLUDE_TESTS))
                .importPackages(BASE_PATH)
    }

    @Test
    fun `nenhuma dto deve acessar classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().accessClassesThat()
            .resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `nenhuma dto deve depender de classes`() {
        noClasses()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().dependOnClassesThat()
            .resideInAnyPackage()
            .check(classes)
    }

    @Test
    fun `uma dto só pode acessar classes`() {
        classes().that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyAccessClassesThat()
            .resideInAnyPackage(
                "..dtos..",
                "..kotlin..",
                "..java.."
            )
            .check(classes)
    }

    @Test
    fun `uma dto só pode ser acessada`() {
        classes()
            .that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyBeAccessed()
            .byAnyPackage(
                "..converters..",
                "..dtos..",
                "..services.."
            )
            .check(classes)
    }

    @Test
    fun `uma dto só pode depender de classes`() {
        classes().that().resideInAPackage(DIRETORIO_ANALISE)
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                "..dtos..",
                "..java..",
                "..jetbrains..",
                "..kotlin..",
                "..pozo.."
            )
            .check(classes)
    }
}
