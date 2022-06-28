package com.example.qbs_zadanie;

import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class StringReplacer {

    private ReplacedRef replacedInfo;

    public ReplacedRef replaceStrings(File directory, String fileExtension, String stringToBeReplaced, String stringToReplaceWith){
        replacedInfo = new ReplacedRef();
        replaceStringsRecursively(directory, fileExtension, stringToBeReplaced, stringToReplaceWith);
        return replacedInfo;
    }

    private List<String> findFilesAndDirectoriesByExtension(Path path, String fileExtension) throws IOException{
        List<String> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .map(p -> p.toString())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;
    }

    private void replaceStringsRecursively(File directory, String fileExtension, String stringToBeReplaced, String stringToReplaceWith){
        try {
            for (String path : findFilesAndDirectoriesByExtension(directory.toPath(), fileExtension)){
                if(new File(path).isFile())
                    replaceStringsInFile(new File(path), stringToBeReplaced, stringToReplaceWith);
                else
                    replaceStringsRecursively(new File(path), fileExtension, stringToBeReplaced, stringToReplaceWith);
            }
        }
        catch (IOException e) {
            replacedInfo.filesWithErrors.add(directory.getPath());
        }
    }

    private void replaceStringsInFile(File file, String stringToBeReplaced, String stringToReplaceWith) throws IOException{
        String[] stringToBeReplacedSplit = stringToBeReplaced.split("\n");
        stringToBeReplaced = "";
        for(int i=0; i < stringToBeReplacedSplit.length - 1; i++)
            stringToBeReplaced += stringToBeReplacedSplit[i] + System.lineSeparator();
        stringToBeReplaced += stringToBeReplacedSplit[stringToBeReplacedSplit.length-1];

        String data = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        int replacedCount = StringUtils.countMatches(data, stringToBeReplaced);
        data = data.replace(stringToBeReplaced, stringToReplaceWith);
        FileUtils.writeStringToFile(file, data, Charset.forName("UTF-8"));
        if(replacedCount > 0)
            replacedInfo.replacedFilesWithCount.put(file.getPath(), replacedCount);
    }
}
