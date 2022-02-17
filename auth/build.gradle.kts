import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
//	id("com.github.ManifestClasspath")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	kotlin("plugin.serialization")
}

group = "com.sean"
version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_11
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.auth0:java-jwt:3.18.1")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("javax.mail:mail:1.4.7")
	implementation("commons-io:commons-io:2.11.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.12.5")

	// spring cloud
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
	implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
	implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")

//	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
//	implementation("io.github.resilience4j:resilience4j-spring-boot2:1.7.1")
//	implementation("org.springframework.boot:spring-boot-starter-aop")
//	implementation("io.github.resilience4j:resilience4j-spring:1.7.1")
//	implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.7.1")



	implementation(project(":base"))
	implementation(project(":jpa"))

//	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("mysql:mysql-connector-java")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
	testImplementation("io.mockk:mockk:1.12.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
