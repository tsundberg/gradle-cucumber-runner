package se.thinkcode;

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
}
