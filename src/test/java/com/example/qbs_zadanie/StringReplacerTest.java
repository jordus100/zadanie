package com.example.qbs_zadanie;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StringReplacerTest {

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
        File sourceDirectory = new File(sourceDirectoryLocation);
        File destinationDirectory = new File(destinationDirectoryLocation);
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    @Test
    public void ReplaceStringsTest(){
        deleteDirectory(new File("src/test/resources/testableCopy"));
        try {
            copyDirectory(new File("src/test/resources/testable/").getAbsolutePath(), new File("src/test/resources/testableCopy").getAbsolutePath());
        } catch (Exception e){
            e.printStackTrace();
        }
        ReplacedRef replacedInfo = new StringReplacer().replaceStrings(new File("src/test/resources/testableCopy/"), "txt", "2dolor", "dolor");
        assertEquals(replacedInfo.replacedFilesWithCount.get(new File("src/test/resources/testableCopy/").getAbsolutePath() + "file1.txt").intValue(), 1);

    }


}