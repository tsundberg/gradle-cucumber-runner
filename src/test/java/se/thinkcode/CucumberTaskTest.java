package se.thinkcode;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberTaskTest {

    private Project project;
    private CucumberTask task;

    @Before
    public void setUp() {
        project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("java");
        project.getPluginManager().apply("se.thinkcode.cucumber-runner");
        task = (CucumberTask) project.getTasks().findByName("cucumber");
    }

    @Test
    public void should_return_test_classpath() {
        String actual = task.getClasspath();

        assertThat(actual).isNotNull();
        assertThat(actual).isNotEmpty();
    }
}
