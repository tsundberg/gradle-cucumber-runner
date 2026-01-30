package se.thinkcode;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberRunnerFunctionalTest {

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        File buildFile = testProjectDir.newFile("build.gradle");
        try (FileWriter writer = new FileWriter(buildFile)) {
            writer.write("plugins {\n");
            writer.write("    id 'java'\n");
            writer.write("    id 'se.thinkcode.cucumber-runner'\n");
            writer.write("}\n");
        }
    }

    @Test
    public void should_apply_plugin_successfully() {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("tasks", "--all")
                .withPluginClasspath()
                .build();

        assertThat(result.getOutput()).contains("cucumber");
    }
}
