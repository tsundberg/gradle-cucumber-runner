package se.thinkcode;

import org.gradle.api.Project;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;
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
    public void should_have_cucumber_extension_wired() {
        CucumberExtension expected = project.getExtensions().findByType(CucumberExtension.class);

        CucumberExtension actual = task.getCucumberExtension();

        assertThat(actual).isNotNull();
        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void should_have_project_directory_set() {
        DirectoryProperty actual = task.getProjectDirectory();

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getAsFile()).isEqualTo(project.getProjectDir());
    }

    @Test
    public void should_have_test_classpath_set() {
        ConfigurableFileCollection actual = task.getTestClasspath();

        assertThat(actual).isNotNull();
        assertThat(actual.getAsPath()).isNotEmpty();
    }
}
