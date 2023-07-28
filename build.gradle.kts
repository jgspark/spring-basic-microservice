import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version ("3.1.2")
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

        // gateway 는 web dependencies 를 가질 수 없다.
        //Please set spring.main.web-application-type=reactive or remove spring-boot-starter-web dependency.
        if (!project.name.contains("gateway")) {
            implementation("org.springframework.boot:spring-boot-starter-web")
        }

        if (!project.name.contains("product-composite") && !project.name.contains("util")) {

            // jpa
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")

            // log add
            // todo : 나중에 체크
            testImplementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")

            // mysql
            runtimeOnly("com.mysql:mysql-connector-j")
            testImplementation("com.h2database:h2")
        }

        if (project.name.contains("product-composite")) {

            // webclient add
            implementation("org.springframework.boot:spring-boot-starter-webflux")

        }


        // todo : 나중에 추가 예정
//        implementation("org.springframework.boot:spring-boot-starter-jdbc")
//        implementation("org.springframework.boot:spring-boot-starter-data-redis:3.0.3")
//        implementation("org.springframework.boot:spring-boot-starter-security:3.0.3")
//        implementation("org.springframework.session:spring-session:1.3.5.RELEASE")
    }
}
