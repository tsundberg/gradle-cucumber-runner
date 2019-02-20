# Gradle Cucumber runner

A gradle plugin for running Cucumber-JVM.

It utilises the Cucumber-JVM command line implementation and forwards every call to `cucumber.api.cli.Main`

## Usage

Run Cucumber with the default settings

    ./gradlew cucumber

### Configuration

Cucumber must know where your steps are implemented. Add an extension to your gradle build script.

```
cucumber {
    glue = 'classpath:se.thinkcode'
}
```

This will search for steps in the package `se.thinkcode` and any subpackages below it.

The complete list of options that can be set are these:

```
cucumber {
    threads = '4'
    glue = 'classpath:se.thinkcode'
    plugin = 'pretty'
    tags = ''
    name = ''
    dryRun = 'true'
    monochrome = 'please'
    strict = 'please'
    snippets = ''
    version = ''
    help = ''
    i18n = ''
    wip = ''

    featurePath = 'src/test/resources'
    main = 'cucumber.api.cli.Main'
}
```

The only setting with default values are

```
    featurePath = 'src/test/resources'
    main = 'cucumber.api.cli.Main'
```

### Options

The options available can be listed with the command

    ./gradlew help --task cucumber

Singe word options can be given to Cucumber with the syntax

    ./gradlew cucumber --i18n help
    
More complicated expressions should be quoted to be forwarded to Cucumber.    

    ./gradlew cucumber --tags "not @wip"

#### Tags

An important part of running Cucumber is to be able to partition the execution 
using different tags. 

Executing a single tag can be done like this:

    ./gradlew cucumber --tags @wip

(assuming that you have a tag for work in progress, wip)

Cucumber supports multiple tags to be executed at the same time. 
Or negating a tag so everything else is executed. Unfortunately, 
you can't stack tags. The solution is to put them into quotes like this:

    ./gradlew cucumber --tags "not @wip"

Doing so will forward the expression `not @wip` to the option `-tags` 
and finally to Cucumber.

### Running features in parallel

Cucumber supports parallel execution if you specify the number of thread 
to use. This can be done in two ways

* An option whn running from a command line, `./gradlew cucumber --threads 4`, this will run four parallel threads
* An extension, that is setting the value in your `build.gradle` file in the `cucumber` section.  

When setting the value in the build script, you can't really know how many 
threads the build will have access to. Hardcoding a fixed number may not be 
your best option. 

One way of specifying the number of threads to use is:

```
cucumber {
    threads = = Runtime.runtime.availableProcessors().intdiv(2) ?: 1
}
```

This will use as many threads as possible while leaving resources for Gradle to execute.

### --junit

Cucumber support setting properties for `junit` from the command line. The option `--junit` is not supported. 

If you have an example where you think you need it, please share the example nd the desired outcome. 
Maybe there should be support for `--junit`. If that is the case, it is possible that it will be implemented.

### Getting help

    ./gradlew cucumber --cucumber-help=please

This will call Cucumber with the argument `--help`. Unfortunately, Gradle requires that each 
command line option is followed by a value. It is `please` above. However, it could be anything.

### Trouble shooting

#### Java 8 is required

The plugin is build using Java 8 and gradle must be executed with Java 8 or newer.

#### The cucumber command executed

Execute Gradle with `debug` to see the command that will be executed.

The printout is verbose, search for `Cucumber command` to find the actual command executed.

## Caveat

In order for Cucumber to have all test resources available, the cucumber task depends on the java plugin task `check`.
This means that your unit tests will be executed before Cucumber is executed.

The Java plugin lifecycle is described in 
the [Java plugin](https://docs.gradle.org/current/userguide/java_plugin.html) documentation.

## Development

Development is described in [development.md](development.md)