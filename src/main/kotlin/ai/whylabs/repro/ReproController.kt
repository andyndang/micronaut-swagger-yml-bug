package ai.whylabs.repro

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("/v1/repro")
@Tag(name = "ReproController ", description = "desc")
class ReproController {

    @Operation(
        operationId = "id",
        summary = "summary",
        description = "desc",
    )
    @Post(
        uri = "/repro",
        consumes = [MediaType.APPLICATION_JSON],
        produces = [MediaType.APPLICATION_JSON]
    )
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun generatePresignedUpload(): ReproResponse {
        return ReproResponse("")
    }

//    @Operation(
//            operationId = "id",
//            summary = "summary",
//            description = "desc",
//    )
//    @Post(
//            uri = "/repro",
//            consumes = [MediaType.APP],
//            produces = [MediaType.APPLICATION_JSON]
//    )
//    fun upload() {
//    }
}
