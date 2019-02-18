package se.thinkcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class CommandLineBuilder {

    String[] buildCommand(CucumberExtension extension, String classpath, CucumberTask commandLineOption) {
        String main = extension.main;

        List<String> command = new ArrayList<>();
        command.add("java");
        addSystemProperties(command);
        command.add("-cp");
        command.add(classpath);
        command.add(main);

        addHelp(command, extension, commandLineOption);
        addVersion(command, extension, commandLineOption);
        addI18N(command, extension, commandLineOption);
        addThreads(command, extension, commandLineOption);
        addGlue(command, extension, commandLineOption);
        addPlugin(command, extension, commandLineOption);
        addTags(command, extension, commandLineOption);
        addName(command, extension, commandLineOption);
        addDryRun(command, extension, commandLineOption);
        addMonochrome(command, extension, commandLineOption);
        addStrict(command, extension, commandLineOption);
        addSnippets(command, extension, commandLineOption);
        addWip(command, extension, commandLineOption);


        // must be last
        addFeaturePath(command, extension, commandLineOption);

        return command.toArray(new String[0]);
    }

    private void addSystemProperties(List<String> command) {
        Properties props = System.getProperties();

        for (Object key : props.keySet()) {
            String keyValue = (String) key;
            String value = props.getProperty(keyValue);

            String systemProperty = "-D" + keyValue + "=" + value;
            command.add(systemProperty);
        }
    }

    private void addHelp(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.help != null) {
            command.add("--help");
            return;
        }

        if (!extension.help.isEmpty()) {
            command.add("--help");
        }
    }

    private void addVersion(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.version != null) {
            command.add("--version");
            return;
        }

        if (!extension.version.isEmpty()) {
            command.add("--version");
        }
    }

    private void addI18N(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.i18n != null) {
            command.add("--i18n");
            command.add(commandLineOption.i18n);
            return;
        }

        if (!extension.i18n.isEmpty()) {
            command.add("--i18n");
            command.add(extension.i18n);
        }
    }

    private void addThreads(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.threads != null) {
            command.add("--threads");
            command.add(commandLineOption.threads);
            return;
        }

        if (!extension.threads.isEmpty()) {
            command.add("--threads");
            command.add(extension.threads);
        }
    }

    private void addGlue(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.glue != null) {
            command.add("--glue");
            command.add(commandLineOption.glue);
            return;
        }

        if (!extension.glue.isEmpty()) {
            command.add("--glue");
            command.add(extension.glue);
        }
    }

    private void addPlugin(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.plugin != null) {
            command.add("--plugin");
            command.add(commandLineOption.plugin);
            return;
        }

        if (!extension.plugin.isEmpty()) {
            command.add("--plugin");
            command.add(extension.plugin);
        }
    }

    private void addTags(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.tags != null) {
            command.add("--tags");
            command.add(commandLineOption.tags);
            return;
        }

        if (!extension.tags.isEmpty()) {
            command.add("--tags");
            command.add(extension.tags);
        }
    }

    private void addName(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.name != null) {
            command.add("--name");
            command.add(commandLineOption.name);
            return;
        }

        if (!extension.name.isEmpty()) {
            command.add("--name");
            command.add(extension.name);
        }
    }

    private void addDryRun(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.dryRun != null) {
            command.add("--dry-run");
            return;
        }

        if (!extension.dryRun.isEmpty()) {
            command.add("--dry-run");
        }
    }

    private void addMonochrome(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.monochrome != null) {
            command.add("--monochrome");
            command.add(commandLineOption.monochrome);
            return;
        }

        if (!extension.monochrome.isEmpty()) {
            command.add("--monochrome");
            command.add(extension.monochrome);
        }
    }

    private void addStrict(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.strict != null) {
            command.add("--strict");
            return;
        }

        if (!extension.strict.isEmpty()) {
            command.add("--strict");
        }
    }

    private void addSnippets(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.snippets != null) {
            command.add("--snippets");
            command.add(commandLineOption.snippets);
            return;
        }

        if (!extension.snippets.isEmpty()) {
            command.add("--snippets");
            command.add(extension.snippets);
        }
    }

    private void addWip(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.wip != null) {
            command.add("--wip");
            return;
        }

        if (!extension.wip.isEmpty()) {
            command.add("--wip");
        }
    }

    private void addFeaturePath(List<String> command, CucumberExtension extension, CucumberTask commandLineOption) {
        if (commandLineOption.featurePath != null) {
            command.add(commandLineOption.featurePath);
            return;
        }

        if (!extension.featurePath.isEmpty()) {
            command.add(extension.featurePath);
        }
    }
}
