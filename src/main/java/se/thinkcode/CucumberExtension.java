package se.thinkcode;

import org.gradle.api.tasks.Input;

@SuppressWarnings("WeakerAccess")
public class CucumberExtension {

    public String threads = "";
    public String glue = "";
    public String[] extraGlues = new String[0];
    public String[] plugin = new String[0];
    public String tags = "";
    public String name = "";
    public String dryRun = "";
    public String monochrome = "";
    public String strict = "";
    public String snippets = "";
    public String version = "";
    public String help = "";
    public String i18n = "";
    public String wip = "";
    public String shorten = "";

    public String featurePath = "src/test/resources";

    public String main = "io.cucumber.core.cli.Main";

    @Input
    public String getThreads() { return threads; }

    @Input
    public String getGlue() { return glue; }

    @Input
    public String[] getExtraGlues() { return extraGlues; }

    @Input
    public String[] getPlugin() { return plugin; }

    @Input
    public String getTags() { return tags; }

    @Input
    public String getName() { return name; }

    @Input
    public String getDryRun() { return dryRun; }

    @Input
    public String getMonochrome() { return monochrome; }

    @Input
    public String getStrict() { return strict; }

    @Input
    public String getSnippets() { return snippets; }

    @Input
    public String getVersion() { return version; }

    @Input
    public String getHelp() { return help; }

    @Input
    public String getI18n() { return i18n; }

    @Input
    public String getWip() { return wip; }

    @Input
    public String getShorten() { return shorten; }

    @Input
    public String getFeaturePath() { return featurePath; }

    @Input
    public String getMain() { return main; }
}
