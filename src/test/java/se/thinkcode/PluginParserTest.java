package se.thinkcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PluginParserTest {

    @Test
    public void set_one_plugin() {
        String[] actual = PluginParser.parse("a plugin");

        assertThat(actual).containsExactly("a plugin");
    }

    @Test
    public void set_two_plugins() {
        String[] actual = PluginParser.parse("a-plugin, another-plugin");

        assertThat(actual).containsExactlyInAnyOrder("a-plugin", "another-plugin");
    }
}
