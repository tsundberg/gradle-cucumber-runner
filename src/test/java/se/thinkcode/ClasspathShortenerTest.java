package se.thinkcode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class ClasspathShortenerTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void create_argfile() throws IOException {
        String tempDir = temporaryFolder.newFolder().getAbsolutePath();
        String expectedClasspath = "faked classpath";

        String argFilePath = ClasspathShortener.createJava9argFile(expectedClasspath, tempDir);
        File argFile = new File(argFilePath);
        assertThat(argFile).exists().canRead();
        assertThat(contentOf(argFile)).startsWith("-classpath").contains(expectedClasspath);
    }

    @Test
    public void create_manifest_jar() throws IOException {
        String tempDir = temporaryFolder.newFolder().getAbsolutePath();
        String expectedClasspath = "faked classpath";

        String manifestJarFilePath = ClasspathShortener.createManifestJarFile(expectedClasspath, tempDir);
        File manifestJarFile = new File(manifestJarFilePath);
        assertThat(manifestJarFile).exists().canRead();
    }
}
