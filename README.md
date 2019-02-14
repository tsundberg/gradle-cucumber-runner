# Gradle Cucumber runner

A gradle plugin for running Cucumber-JVM.

It utilises the Cucumber-JVM command line implementation and forwards every call to `Main`

## Usage

Run Cucumber with the default settings

    ./gradlew cucumber


### Getting help

    ./gradlew cucumber --cucumber-help=please

This will call Cucumber with the argument `--help`. Unfortunately, Gradle requires that each 
command line option is followed by a value. It is `please` above. However, it could be anything.


### See the different command line options available

    ./gradlew help --task cucumber
    

### Trouble shooting

#### The cucumber command executed

Execute Gradle with `debug` to see the command that will be executed.

The printout is verbose, search for `Cucumber command` to find the actual command executed.


## Development

### Building

Local build:

    ./gradlew clean build publishToMavenLocal

### Publish

To publish the plugin to Gradle


