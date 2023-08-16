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
    implementation(project(":core:exception"))
    implementation(project(":core:http")) {
        exclude(module = ":core:exception")
    }
}
