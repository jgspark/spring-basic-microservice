rootProject.name = "spring-basic-microservice"


include(
    "api-gateway",
    ":service:product-service",
    ":service:review-service",
    ":service:recommendation-service",
    ":service:product-composite-service",
    ":core:exception",
    ":core:http"
)
