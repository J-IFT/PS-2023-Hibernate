plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    implementation("org.postgresql:postgresql:42.3.1")

    // Hibernate ORM with JPA API
    implementation("org.hibernate:hibernate-core:5.6.7.Final")
    implementation("org.hibernate:hibernate-envers:5.6.7.Final")
    implementation("org.hibernate:hibernate-validator:6.2.0.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.5.7.Final")

    // H2 Database for testing
    testImplementation("com.h2database:h2:1.4.200")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    implementation("mysql:mysql-connector-java:8.0.32")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
}

application {
    mainClassName = "com.example.MyApplicationKt"
}