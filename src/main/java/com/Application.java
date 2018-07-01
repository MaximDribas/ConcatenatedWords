package main.java.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {
    private static final List<String> WORDS_LIST = readFile();
    private static final Set<String> WORDS_SET = new HashSet<>(WORDS_LIST);
    private static List<String> concatenatedWords = new ArrayList<>();
    private static String checkingWord;
    private static boolean flag = false;

    public static void main(String[] args) {

        for (String word : WORDS_LIST) {
            checkingWord = word;
            checkingIfConcatenated(checkingWord);
        }

        concatenatedWords = sortList(concatenatedWords);

        if (!concatenatedWords.isEmpty() && concatenatedWords.size() > 1) {
            System.out.println("The longest concatenated word: " + concatenatedWords.get(0));
            System.out.println("The 2nd longest concatenated word: " + concatenatedWords.get(1));
            System.out.println("The total count of all the concatenated words: " + concatenatedWords.size());
        }
    }

    private static void checkingIfConcatenated(String word) {
        for (int i = 0; i < word.length(); i++) {
            String left = word.substring(0, i + 1);
            String right = word.substring(i + 1);
            if (WORDS_SET.contains(left)) {
                if (WORDS_SET.contains(right)) {
                    concatenatedWords.add(checkingWord);
                    flag = true;
                    break;
                }
                checkingIfConcatenated(right);
                if (flag) {
                    break;
                }
            }
        }
    }

    private static List<String> readFile() {
        List<String> gettingWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            while (reader.ready()) {
                String readeWord = reader.readLine();
                if (readeWord.length() > 0) {
                    gettingWords.add(readeWord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gettingWords;
    }

    private static List<String> sortList(List<String> list) {
        return list.stream()
                .sorted((o1, o2) -> Integer.compare(o2.length(), o1.length()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
