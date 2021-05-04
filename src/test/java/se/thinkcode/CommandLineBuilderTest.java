package se.thinkcode;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CommandLineBuilderTest {
    private final File projectDir = new File("");
    private final CucumberTask commandlineOption = mock(CucumberTask.class);
    private final CommandLineBuilder builder = new CommandLineBuilder();
    private final CucumberExtension extension = new CucumberExtension();

    @Test
    public void should_build_help_command() {
        extension.help = "help";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--help");
    }

    @Test
    public void should_build_help_command_from_commandline_option() {
        commandlineOption.help = "help";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--help");
    }

    @Test
    public void should_build_version_command() {
        extension.version = "version";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--version");
    }

    @Test
    public void should_build_version_command_from_commandline_option() {
        commandlineOption.version = "version";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--version");
    }

    @Test
    public void should_build_i18n_command_listing_all_supported_languages() {
        extension.i18n = "help";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--i18n", "help");
    }

    @Test
    public void should_build_i18n_command_listing_all_supported_languages_from_commandline_option() {
        commandlineOption.i18n = "help";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--i18n", "help");
    }

    @Test
    public void should_build_i18n_command_showing_keywords_for_swedish() {
        extension.i18n = "sv";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--i18n", "sv");
    }

    @Test
    public void should_build_i18n_command_showing_keywords_for_swedish_from_commandline_option() {
        commandlineOption.i18n = "sv";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--i18n", "sv");
    }

    @Test
    public void should_build_command_adding_four_threads() {
        extension.threads = "4";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--threads", "4");
    }

    @Test
    public void should_build_command_adding_four_threads_from_commandline_option() {
        commandlineOption.threads = "4";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--threads", "4");
    }

    @Test
    public void should_build_command_adding_glue() {
        extension.glue = "classpath:se.thinkcode";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--glue", "classpath:se.thinkcode");
    }

    @Test
    public void should_build_command_adding_glue_from_commandline_option() {
        commandlineOption.glue = "classpath:se.thinkcode";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--glue", "classpath:se.thinkcode");
    }

    @Test
    public void should_build_command_adding_plugin() {
        extension.plugin = new String[]{"plugin name"};

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--plugin", "plugin name");
    }

    @Test
    public void should_build_command_adding_multiple_plugins() {
        extension.plugin = new String[]{"plugin name", "another plugin"};

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--plugin", "plugin name");
        assertThat(actual).containsSequence("--plugin", "another plugin");
    }

    @Test
    public void should_build_command_adding_plugin_from_commandline_option() {
        commandlineOption.plugin = "plugin name";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--plugin", "plugin name");
    }

    @Test
    public void should_build_command_adding_multiple_plugins_from_commandline_option() {
        commandlineOption.plugin = "plugin name, another plugin";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--plugin", "plugin name");
        assertThat(actual).containsSequence("--plugin", "another plugin");
    }

    @Test
    public void should_build_command_adding_tags() {
        extension.tags = "lots of tags";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--tags", "lots of tags");
    }

    @Test
    public void should_build_command_adding_tags_from_commandline_option() {
        commandlineOption.tags = "lots of tags";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--tags", "lots of tags");
    }

    @Test
    public void should_build_command_adding_name() {
        extension.name = "a specific name";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--name", "a specific name");
    }

    @Test
    public void should_build_command_adding_name_from_commandline_option() {
        commandlineOption.name = "a specific name";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--name", "a specific name");
    }

    @Test
    public void should_build_command_adding_dry_run() {
        extension.dryRun = "dry run";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--dry-run");
    }

    @Test
    public void should_build_command_adding_dry_run_from_commandline_option() {
        commandlineOption.dryRun = "dry run";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--dry-run");
    }

    @Test
    public void should_build_command_adding_monochrome() {
        extension.monochrome = "monochrome value";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--monochrome", "monochrome value");
    }

    @Test
    public void should_build_command_adding_monochrome_from_commandline_option() {
        commandlineOption.monochrome = "monochrome value";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--monochrome", "monochrome value");
    }

    @Test
    public void should_build_command_adding_strict() {
        extension.strict = "strict";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--strict");
    }

    @Test
    public void should_build_command_adding_strict_from_commandline_option() {
        commandlineOption.strict = "strict";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--strict");
    }

    @Test
    public void should_build_command_adding_snippets() {
        extension.snippets = "snippets value";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--snippets", "snippets value");
    }

    @Test
    public void should_build_command_adding_snippets_from_commandline_option() {
        commandlineOption.snippets = "snippets value";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--snippets", "snippets value");
    }

    @Test
    public void should_build_command_adding_wip() {
        extension.wip = "wip";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--wip");
    }

    @Test
    public void should_build_command_adding_wip_from_commandline_option() {
        commandlineOption.wip = "wip";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).containsSequence("--wip");
    }

    @Test
    public void should_build_command_adding_default_feature_path() {
        String expected = getAbsoluteRoot() + "/" + "src/test/resources";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).endsWith(expected);
    }

    @Test
    public void should_build_command_adding_feature_path_from_commandline_option() {
        String expected = getAbsoluteRoot() + "/" + "a/custom/feature/path";
        commandlineOption.featurePath = "a/custom/feature/path";

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).endsWith(expected);
    }

    @Test
    public void should_build_command_adding_absolute_feature_path_from_commandline_option() {
        String expected = "/tmp/a_custom_feature_path";
        commandlineOption.featurePath = expected;

        String[] actual = builder.buildCommand(extension, "faked classpath", commandlineOption, projectDir);

        assertCommandStart(actual);
        assertThat(actual).endsWith(expected);
    }

    private String getAbsoluteRoot() {
        File projectDir = new File(".");
        String root = projectDir.getAbsolutePath();
        root = root.substring(0, root.length() - 2);

        return root;
    }

    private void assertCommandStart(String[] actual) {
        assertThat(actual).startsWith("java");

        String actualSystemProperty = actual[1];
        assertThat(actualSystemProperty).startsWith("-D");
        assertThat(actualSystemProperty).containsOnlyOnce("=");

        assertThat(actual).containsSequence("-cp", "faked classpath", "cucumber.api.cli.Main");
    }
}
