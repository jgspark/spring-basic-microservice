import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version ("3.0.2")
    id("io.spring.dependency-management") version ("1.1.0")
    id("org.jetbrains.kotlin.jvm") version ("1.7.22")
    id("org.jetbrains.kotlin.plugin.spring") version ("1.7.22")
}


allprojects {

    val javaVersion = "17"
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects {

    apply {
        plugin("kotlin")
        plugin("io.spring.dependency-management")
        plugin("kotlin-kapt")
        plugin("org.jetbrains.kotlin.jvm")
    }

    dependencies {

        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        //web app
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")

        // db
        implementation("com.h2database:h2")

//        // redis
//        implementation("org.springframework.boot:spring-boot-starter-data-redis:3.0.3")

        // security
        implementation("org.springframework.boot:spring-boot-starter-security:3.0.3")

        // session
        implementation("org.springframework.session:spring-session:1.3.5.RELEASE")
    }
}
