import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFrequencyCounterTest {

    @ParameterizedTest
    @MethodSource("wordCountCases")
    void countAndSortWordsShouldReturnExpectedEntries(String text, List<Map.Entry<String, Integer>> expectedList) {
        // Act
        List<Map.Entry<String, Integer>> actual = TextFrequencyCounter.countAndSortWords(text);

        // Assert
        assertEquals(expectedList, actual);
    }

    static List<Arguments> wordCountCases() {
        return List.of(
                // Counting and descending sorting
                Arguments.of("red blue blue green green green",
                        List.of(
                                Map.entry("green", 3),
                                Map.entry("blue", 2),
                                Map.entry("red", 1)
                        )
                ),

                // Mixed capitalization
                Arguments.of("JAVA java Java spring SPRING sql",
                        List.of(
                                Map.entry("java", 3),
                                Map.entry("spring", 2),
                                Map.entry("sql", 1)
                        )
                ),

                // Punctuation between words
                Arguments.of("cat,dog cat!dog cat?",
                        List.of(
                                Map.entry("cat", 3),
                                Map.entry("dog", 2)
                        )
                ),

                // Surrounding and repeated spaces
                Arguments.of("   java   spring  java   ",
                        List.of(
                                Map.entry("java", 2),
                                Map.entry("spring", 1)
                        )
                ),

                // Tabs and newlines
                Arguments.of("java\tspring\njava",
                        List.of(
                                Map.entry("java", 2),
                                Map.entry("spring", 1)
                        )
                ),

                // Empty input
                Arguments.of("", List.of()),

                // Whitespace-only input
                Arguments.of("   ", List.of()),

                // Punctuation-only input
                Arguments.of("!!! ,,, ???", List.of())
        );
    }
}