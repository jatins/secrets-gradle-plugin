// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.10")
    testImplementation("com.android.tools.build:gradle:4.1.1")
    testImplementation("junit:junit:4.13.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

gradlePlugin {
    plugins {
        create(PluginInfo.name) {
            id = PluginInfo.id
            implementationClass = PluginInfo.implementationClass
        }
    }
}

pluginBundle {
    website = "https://github.com/googlemaps"
    vcsUrl = "https://github.com/googlemaps"
    description = "A thing"
    version = "0.1"

    (plugins) {
        PluginInfo.name {
            displayName = "GMP API Key Provider"
            tags = listOf("kotlin")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenPub") {
            group = "com.google.maps.android"
            artifactId = "api_key_provider"
            version = "0.1"
        }
    }
    repositories {
        maven(url = "build/repository")
    }
}

object PluginInfo {
    const val id = "com.google.secrets_plugin"
    const val name = "secretsPlugin"
    const val implementationClass = "com.google.secrets_plugin.SecretsPlugin"
}