package ch.hslu.ad.sw11.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileFindersTest {
    @Test
    void testFileFinderSequential() {
        FindFile.findFile("FileFindersTest.java", new File("./src"));
    }

    @Test
    void testFindFileTask() {
        FindFileTask findFileTask = new FindFileTask("FileFindersTest.java", new File("./src"));
        String result = findFileTask.invoke();
        System.out.println(result);
    }
}
