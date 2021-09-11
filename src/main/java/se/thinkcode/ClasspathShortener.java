package se.thinkcode;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.jar.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class ClasspathShortener {
    public static String createJava9argFile(String classpath, String tempDir) {
        String argFile = Paths.get(tempDir, "cucumber_runner_argFile").toString();
        try {
            OutputStream file = new FileOutputStream(argFile);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(file, StandardCharsets.UTF_8));
            writer.println("-classpath\n" + classpath);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    String.format("Cannot write temporary file on %s,\nexception message:\n %s",
                            argFile, e.getMessage()
                    ));
        }
        return argFile;
    }

    public static String createManifestJarFile(String classpath, String tempDir) {
        String manifestJarFile = Paths.get(tempDir, "cucumber_runner_manifest.jar").toString();
        Manifest manifest = createManifestWithClasspath(classpath);
        JarOutputStream jarOutputStream;
        try {
            jarOutputStream = new JarOutputStream(new FileOutputStream(manifestJarFile), manifest);
            jarOutputStream.putNextEntry(new ZipEntry("META-INF/"));
            jarOutputStream.close();
            return manifestJarFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    String.format("Cannot write temporary file on %s,\nexception message:\n %s",
                            manifestJarFile, e.getMessage()
                    ));
        }
    }

    private static Manifest createManifestWithClasspath(String classpath) {
        Manifest manifest = new Manifest();
        Attributes attributes = manifest.getMainAttributes();
        attributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        if (classpath != null) {
            String classpathAttr = Arrays.stream(classpath.split(";")).map(
                    ClasspathShortener::getURL
            ).collect(Collectors.joining(" "));
            attributes.putValue("Class-Path", classpathAttr);
        }
        return manifest;
    }

    private static String getURL(String path) {
        try {
            return Paths.get(path).toUri().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
