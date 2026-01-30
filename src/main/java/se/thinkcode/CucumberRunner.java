package se.thinkcode;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

public class CucumberRunner implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("cucumber", CucumberExtension.class);
        TaskProvider<CucumberTask> cucumber = project.getTasks().register("cucumber", CucumberTask.class);

        project.afterEvaluate((p) -> cucumber.configure(task -> task.dependsOn(p.getTasks().getByName("testClasses"))));
    }
}
