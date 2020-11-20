Repro for an issue with the micronaut swagger .yml gen that results in the `required` field being a list instead of a boolean.


# Repro steps

```bash
./gradlew clean build
cat build/tmp/kapt3/classes/main/META-INF/swagger/repro-0.1.yml

# You should see the following
openapi: 3.0.1
info:
  title: repro
  description: desc
  version: "0.1"
paths:
  /v1/repro/repro:
    post:
      tags:
      - 'ReproController '
      summary: summary
      description: desc
      operationId: id
      parameters: []
      responses:
        default:
          description: id default response
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ReproResponse'
components:
  schemas:
    ReproResponse:
      type: object
      properties:
        id:
          required:
          - "true"
          type: string
          description: This is not nullable
          nullable: true
      description: Respond payload for an upload request

```

The `required` field in the ReproResponse object should be a boolean with a
value of `true`. Instead, it's a list with a string value of `"true"`.

The object is defined in `src/main/kotlin/ai/whylabs/repro/ReproResponse.kt`.

This results in the generated types for that object being nullable. The only
workaround I've found is to use the `requiredProperties` field in the class
level `Schema` annotation, but that's painful for a lot of reasons.
