plugins {
    id("org.springframework.boot")
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-zuul
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul:2.2.10.RELEASE")
}
