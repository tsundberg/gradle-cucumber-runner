package se.thinkcode;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CucumberRunner implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("cucumber", CucumberExtension.class);
        CucumberTask cucumber = project.getTasks().create("cucumber", CucumberTask.class);

        project.afterEvaluate((p) -> cucumber.dependsOn(p.getTasks().getByName("check")));
    }
}
