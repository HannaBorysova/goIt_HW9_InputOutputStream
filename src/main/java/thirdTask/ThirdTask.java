package thirdTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ThirdTask {
    private static final String absolutePath = "src/main/resources/words.txt";

    public static void main(String[] args) {
        File file = new File(absolutePath);
        checkFile(file);
        readFile(file);
    }

    private static void checkFile(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String words = bufferedReader.readLine();
            while (words != null) {
                builder.append(words.trim());
                builder.append(" ");
                words = bufferedReader.readLine();
            }
            String wordsBuilder = builder.toString();
            sortAndCountWords(createArrayFromFile(wordsBuilder));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static String[] createArrayFromFile(String wordsBuilder) {
        String[] wordsArray = wordsBuilder.split(" ");
        return wordsArray;
    }

    private static void sortAndCountWords(String[] wordsArray) {
        List<String> wordsList = Arrays.asList(wordsArray);
        Map<String, Integer> countingWords = new HashMap<>();
        for (String repeats : wordsList) {
            countingWords.put(repeats, Collections.frequency(wordsList, repeats));
        }
        countingWords.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }
}
