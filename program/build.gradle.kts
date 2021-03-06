import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val spek_version = "2.0.2"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation(project(":sl"))

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation(kotlin("test"))

//    testImplementation("org.amshove.kluent:kluent:1.49")
//    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
//    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version") {
//        exclude(group = "org.jetbrains.kotlin")
//        exclude(group = "org.junit.platform")
//    }
}

//tasks.withType<Test> {
//    useJUnitPlatform {
//        includeEngines("spek")
//    }
//}
