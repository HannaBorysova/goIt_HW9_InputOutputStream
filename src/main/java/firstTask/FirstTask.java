package firstTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FirstTask {
    private static final String absolutePath = "src/main/resources/File.txt";

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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String numbers = bufferedReader.readLine();
            while (numbers != null) {
                if (checkWrongNumbers(numbers)) {
                    System.out.println(numbers);
                }
                numbers = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWrongNumbers(String numbers) {
        String firstCorrectType = "\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";
        String secondCorrectType = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
        return numbers.matches(firstCorrectType) || numbers.matches(secondCorrectType);
        }
    }