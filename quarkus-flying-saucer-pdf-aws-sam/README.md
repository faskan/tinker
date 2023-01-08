# quarkus-flying-saucer-pdf
## To create native image
`quarkus build --native --no-tests -Dquarkus.native.container-build=true`
#### The --no-tests flag is required only on Windows and macOS.

## SAM local
`sam local start-api --template target/sam.jvm.yaml`

## Deploy to AWS
`sam deploy -t target/sam.jvm.yaml -g`

