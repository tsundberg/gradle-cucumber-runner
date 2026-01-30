package se.thinkcode;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberRunnerTest {

    private Project project;

    @Before
    public void setUp() {
        project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("se.thinkcode.cucumber-runner");
    }

    @Test
    public void should_register_cucumber_extension() {
        CucumberExtension actual = project.getExtensions().findByType(CucumberExtension.class);

        assertThat(actual).isNotNull();
    }

    @Test
    public void should_register_cucumber_task() {
        Task actual = project.getTasks().findByName("cucumber");

        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(CucumberTask.class);
    }

    @Test
    public void should_set_task_group() {
        Task actual = project.getTasks().findByName("cucumber");

        assertThat(actual.getGroup()).isEqualTo("Cucumber");
    }

    @Test
    public void should_set_task_description() {
        Task actual = project.getTasks().findByName("cucumber");

        assertThat(actual.getDescription()).isEqualTo("Execute Cucumber-JVM from Gradle");
    }
}
