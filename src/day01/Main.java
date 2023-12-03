package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private static final Map<String, String> mapping = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9");


    public static String findAndReplace(String str) {

        var matcher = Pattern.compile("one|two|three|four|five|six|seven|eight|nine|\\d").matcher(str);

        String first = null;
        String last = null;

        while (matcher.find()) {
            if (first == null) {
                first = matcher.group();
            }
            last = matcher.group();
            matcher.region(matcher.start() + 1, str.length());
        }
        return mapping.getOrDefault(first, first) + mapping.getOrDefault(last, last);
    }

    public static void main(String[] args) {
        String fileName = "E:\\Development\\Java\\AoC2023\\src\\day01\\input-silver.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            var sum = stream
                    .map(Main::findAndReplace)
                    .peek(System.out::println)
                    .mapToInt(Integer::parseInt)
                    .sum();
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}