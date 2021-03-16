package secondTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SecondTask {
    private static final String absolutePath = "src/main/resources/FileGSON.txt";
    private static final String absoluteJsonPath = "src/main/resources/JSON.json";

    public static void main(String[] args) {
        File fileGSON = new File(absolutePath);
        File fileJSON = new File(absoluteJsonPath);
        checkFile(fileGSON);
        checkFile(fileJSON);
        List<Person> people = new LinkedList<>();

        readFile(fileGSON,people);

        writeToJson(fileJSON, people);
    }

    private static void writeToJson(File fileJSON, List<Person> people) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileJSON))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(people);
            bufferedWriter.write(json);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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

    private static void readFile(File fileGSON, List<Person> people) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileGSON))) {
            String dataFile = bufferedReader.readLine();
                  while (dataFile != null) {
                      String[] column = dataFile.split(" ");
                      if (!column[0].equals("name") && !column[1].equals("age")) {
                          people.add(new Person(column[0], Integer.parseInt(column[1])));
                      }
                      dataFile = bufferedReader.readLine();
                  }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}