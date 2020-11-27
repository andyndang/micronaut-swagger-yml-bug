package ai.whylabs.repro

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    fun generatePresignedUpload(): ReproResponse {
        return ReproResponse("")
    }

    @Operation(
            operationId = "id2",
            summary = "summary",
            description = "desc",
    )
    @ApiResponses(
            ApiResponse(
                    responseCode = "200",
                    description = "'OK' if the operation succeeded",
            ),
            ApiResponse(responseCode = "404", description = "Invalid organization ID or model ID"),
            ApiResponse(responseCode = "400", description = "Payload is invalid")
    )
    @Post(
            uri = "/upload/{id}",
            produces = [MediaType.APPLICATION_JSON]
    )
    fun upload(
            id: String,

    ) {
        print("Do nothing")
    }
}


@Schema(description = "Respond payload for an upload request")
data class ReproResponse(val id: String)