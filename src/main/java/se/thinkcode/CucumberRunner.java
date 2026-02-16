package se.thinkcode;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskProvider;

public class CucumberRunner implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        CucumberExtension extension = project.getExtensions().create("cucumber", CucumberExtension.class);
        TaskProvider<CucumberTask> cucumber = project.getTasks().register("cucumber", CucumberTask.class, task -> {
            task.setCucumberExtension(extension);
            task.getProjectDirectory().set(project.getLayout().getProjectDirectory());
        });

        project.getPlugins().withType(JavaPlugin.class, javaPlugin -> cucumber.configure(task -> {
            task.dependsOn(project.getTasks().named("testClasses"));
            SourceSetContainer sourceSets = project.getExtensions().getByType(SourceSetContainer.class);
            SourceSet testSourceSet = sourceSets.getByName("test");
            task.getTestClasspath().from(testSourceSet.getRuntimeClasspath());
        }));
    }
}
