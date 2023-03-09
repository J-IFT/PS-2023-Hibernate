plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Add Hibernate to the project
    implementation("org.hibernate:hibernate-core:5.6.7.Final")

    // An in-memory h2 database to test the application without the need for a real database
    implementation("com.h2database:h2:2.1.210")
    // The driver to connect to a real MySQL database
    //implementation("mysql:mysql-connector-java:8.0.28")

    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")

}

application {
    // Define the main class for the application.
    mainClass.set("b3.hibernate.App")
}

