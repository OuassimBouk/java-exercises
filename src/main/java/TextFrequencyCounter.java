import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFrequencyCounter {
    static List<Map.Entry<String, Integer>> countAndSortWords(String text) {
        Map<String, Integer> wordCountsMap = new HashMap<>();

        String normalized = text.toLowerCase().replaceAll("[^a-z\\s]", " ").strip();
        if (normalized.isBlank()) {
            return new ArrayList<>();
        }
        String[] words = normalized.split("\\s+");

        for (String word : words) {
            int totalCount = wordCountsMap.getOrDefault(word, 0);
            wordCountsMap.put(word, totalCount + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCountsMap.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        return sortedEntries;
    }
    public static void main(String[] args) {
        String text = IO.readln("Enter text: ");
        List<Map.Entry<String, Integer>> wordCountsList = countAndSortWords(text);
        IO.println("Word frequencies:");

        int totalWords = 0;
        for (Map.Entry<String, Integer> word : wordCountsList) {
            totalWords = totalWords + word.getValue();
            IO.println(word.getKey() + ": " + word.getValue());
        }

        IO.println("\nTotal words: " + totalWords);
        IO.println("Unique words: " + wordCountsList.size());
    }
}