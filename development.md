# Development

## Building

Use Java 8

    sdk use java 8.0.442-zulu 

Local build:

    ./gradlew clean build publishToMavenLocal

Test it in a gradle project using the correct version. In order for Gradle to pick up the plugin from the local Maven
repository, add this first in the gradle setting file `setting.gradle`

```
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
```

## Publish

To publish the plugin to Gradle

* Update the version number

* Execute the command `./gradlew publishPlugins`

Ref: https://guides.gradle.org/publishing-plugins-to-gradle-plugin-portal/         

