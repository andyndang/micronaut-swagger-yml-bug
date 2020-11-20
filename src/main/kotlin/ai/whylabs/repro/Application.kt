package ai.whylabs.repro

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "repro",
        version = "0.1",
        description = "desc",
    ),
)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .eagerInitSingletons(true)
            .packages(javaClass.`package`.name)
            .mainClass(Application.javaClass)
            .start()
    }
}
