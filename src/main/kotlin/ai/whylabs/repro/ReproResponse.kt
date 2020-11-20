package ai.whylabs.repro

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Respond payload for an upload request")
class ReproResponse(
    @field:Schema(
        description = "This is not nullable",
        required = true,
        nullable = true,
    ) val id: String,
)
