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
    implementation("org.springframework.cloud:spring-cloud-starter-zuul:1.4.7.RELEASE")
}
