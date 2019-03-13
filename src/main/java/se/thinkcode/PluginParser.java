package se.thinkcode;

class PluginParser {
    static String[] parse(String plugins) {
        String[] parts = plugins.split(",");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        return parts;
    }
}
