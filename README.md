# Gradle Cucumber runner

A gradle plugin for running Cucumber-JVM.

It utilises the Cucumber-JVM command line implementation and forwards every call to `Main`

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
    threads = '4';
    glue = 'classpath:se.thinkcode'
    plugin = 'pretty';
    tags = '';
    name = '';
    dryRun = 'true';
    monochrome = 'please';
    strict = 'please';
    snippets = '';
    version = '';
    help = '';
    i18n = '';
    wip = '';

    featurePath = "src/test/resources";
    main = "cucumber.api.cli.Main";
}
```

The only setting with default values are

```
    featurePath = 'src/test/resources'
    main = 'cucumber.api.cli.Main'
```

### --junit

Cucumber support setting properties for `junit` from the command line. The option `--junit` is not supported. 

If you have an example where you think you need it, please share the example nd the desired outcome. 
Maybe there should be support for `--junit`. If that is the case, it is possible that it will be implemented.

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

Development is described in [development.md](development.md)