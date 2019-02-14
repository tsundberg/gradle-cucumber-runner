package se.thinkcode;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.internal.impldep.com.esotericsoftware.kryo.NotNull;

public class CucumberRunner implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("cucumber", CucumberExtension.class);
        CucumberTask cucumber = project.getTasks().create("cucumber", CucumberTask.class);

        project.afterEvaluate((p) -> cucumber.dependsOn(p.getTasks().getByName("compileTestJava")));
    }
}
