package se.thinkcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class ClasspathShortener {
    public static String createJava9argFile(String classpath, String tempDir) {
        String argFile = Paths.get(tempDir,"cucumber_runner_argFile").toString();
        try {
            PrintWriter writer = new PrintWriter(argFile, StandardCharsets.UTF_8);
            writer.println("-classpath\n" + classpath);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Cannot write temporary file on %s,\nexception message:\n %s", argFile, e.getMessage()));
        }
        return argFile;
    }
}
