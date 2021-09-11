package se.thinkcode;

import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import se.thinkcode.stream.StreamConsumer;

import java.io.File;

public class CucumberTask extends DefaultTask {

    @Option(option = "cucumber-help",
            description = "Get Cucumber help")
    String help;

    public void setHelp(String help) {
        this.help = help;
    }

    @Option(option = "cucumber-version",
            description = "Get Cucumber version")
    String version;

    public void setVersion(String version) {
        this.version = version;
    }

    @Option(option = "i18n",
            description = "List keywords for in a particular language")
    String i18n;

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    @Option(option = "threads",
            description = "Number of threads to run tests under. Defaults to 1.")
    String threads;

    public void setThreads(String threads) {
        this.threads = threads;
    }

    @Option(option = "glue",
            description = "Package to load glue code (step definitions, hooks and plugins) from. E.g: com.example.app")
    String glue;

    public void setGlue(String glue) {
        this.glue = glue;
    }

    @Option(option = "plugin",
            description = "Register one or many plugins. --plugin=\"pretty, progress\" specifies two plugins See Cucumber help for more details, --cucumber-help=please")
    String plugin;

    public void setPlugin(String plugins) {
        this.plugin = plugins;
    }

    @Option(option = "tags",
            description = "Only run scenarios tagged with tags matching TAG_EXPRESSION.")
    String tags;

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Option(option = "name",
            description = "Only run scenarios whose names match REGEXP.")
    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Option(option = "cucumber-dry-run",
            description = "Skip execution of glue code.")
    String dryRun;

    public void setDryRun(String dryRun) {
        this.dryRun = dryRun;
    }

    @Option(option = "monochrome",
            description = "Don't colour terminal output.")
    String monochrome;

    public void setMonochrome(String monochrome) {
        this.monochrome = monochrome;
    }

    @Option(option = "strict",
            description = "Treat undefined and pending steps as errors.")
    String strict;

    public void setStrict(String strict) {
        this.strict = strict;
    }

    @Option(option = "snippets",
            description = "Naming convention for generated snippets. Defaults to underscore.")
    String snippets;

    public void setSnippets(String snippets) {
        this.snippets = snippets;
    }

    @Option(option = "wip",
            description = "Fail if there are any passing scenarios.")
    String wip;

    public void setWip(String wip) {
        this.wip = wip;
    }

    @Option(option = "shorten",
            description = "Shorten the command line by shortening the classpath. Defaults to none. Possible values are [manifest, argfile]")
    String shorten;

    public void setShorten(String shorten) {
        this.shorten = shorten;
    }

    @Option(option = "featurePath",
            description = "The feature or features to execute. Defaults to src/test/resources See Cucumber help for more details, --cucumber-help=please")
    String featurePath;

    public void setFeaturePath(String featurePath) {
        this.featurePath = featurePath;
    }

    public CucumberTask() {
        setGroup("Cucumber");
        setDescription("Execute Cucumber-JVM from Gradle");
    }

    @TaskAction
    public void runCucumber() {
        CucumberExtension extension = getProject().getExtensions().findByType(CucumberExtension.class);
        if (extension == null) {
            extension = new CucumberExtension();
        }

        try {
            CommandLineBuilder cliBuilder = new CommandLineBuilder();
            File projectDir = getProject().getProjectDir();
            String[] command = cliBuilder.buildCommand(extension, getClasspath(), this, projectDir);
            debugCommand(command);
            execute(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void debugCommand(String[] command) {
        Logger logger = getLogger();

        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            for (String s : command) {
                builder.append(s);
                builder.append(" ");
            }

            System.out.println();
            System.out.println("Cucumber command:");
            System.out.println(builder);
            System.out.println();
        }
    }

    private void execute(String[] command) {
        int exitValue;

        try {
            Process process = new ProcessBuilder()
                    .command(command)
                    .start();

            StreamConsumer stdOut = new StreamConsumer(process.getInputStream(), System.out);
            new Thread(stdOut).start();

            StreamConsumer stdErr = new StreamConsumer(process.getErrorStream(), System.err);
            new Thread(stdErr).start();

            exitValue = process.waitFor();

            stdOut.stopProcessing();
            stdErr.stopProcessing();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (exitValue != 0) {
            throw new RuntimeException("The execution failed");
        }
    }

    private String getClasspath() {
        SourceSetContainer sourceSets = getProject().getConvention().getPlugin(JavaPluginConvention.class).getSourceSets();

        for (SourceSet sourceSet : sourceSets) {
            if ("test".equals(sourceSet.getName())) {
                return sourceSet.getRuntimeClasspath().getAsPath();
            }
        }

        throw new RuntimeException("The test classpath was not found");
    }
}