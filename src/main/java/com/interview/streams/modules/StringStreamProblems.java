package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 15: String Stream Problems.
 * Character and word level Stream API practice.
 */
public class StringStreamProblems {

  /**
   * Problem 1: Count vowels
   * <p>Count vowels in a string.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>chars() turns a String into an IntStream.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public long countVowels(String text) {
    return text.chars()
        .filter(ch -> "aeiouAEIOU".indexOf(ch) >= 0)
        .count();
  }

  /**
   * Problem 2: Count characters frequency
   * <p>Return frequency of every character preserving first-seen order.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group characters after boxing int chars to Character.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Map<Character, Long> characterFrequency(String text) {
    return text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(
            Function.identity(),
            LinkedHashMap::new,
            Collectors.counting()));
  }

  /**
   * Problem 3: Find duplicate characters
   * <p>Return characters appearing more than once.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Frequency map plus filter identifies duplicates.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Set<Character> duplicateCharacters(String text) {
    return characterFrequency(text).entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Problem 4: Find first non-repeating character
   * <p>Return the first character that appears once.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>LinkedHashMap preserves encounter order for frequency scan.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Optional<Character> firstNonRepeatingCharacter(String text) {
    Map<Character, Long> frequency = text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

    return frequency.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst();
  }

  /**
   * Problem 5: Find first repeating character
   * <p>Return the first character whose total frequency is greater than one.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Count first, then scan in insertion order.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Optional<Character> firstRepeatingCharacter(String text) {
    Map<Character, Long> frequency = text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

    return frequency.entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .findFirst();
  }

  /**
   * Problem 6: Reverse each word
   * <p>Reverse every word while keeping word order.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Map each word to its reversed form.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public String reverseEachWord(String sentence) {
    return Arrays.stream(sentence.split("\\s+"))
        .map(word -> new StringBuilder(word).reverse().toString())
        .collect(Collectors.joining(" "));
  }

  /**
   * Problem 7: Reverse sentence using streams
   * <p>Reverse word order in a sentence using streams.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use IntStream indexes to read words from the end.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public String reverseSentence(String sentence) {
    List<String> words = Arrays.asList(sentence.trim().split("\\s+"));
    return IntStream.range(0, words.size())
        .mapToObj(index -> words.get(words.size() - 1 - index))
        .collect(Collectors.joining(" "));
  }

  /**
   * Problem 8: Sort characters alphabetically
   * <p>Return characters sorted alphabetically.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>chars().sorted() sorts character code points.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public String sortCharacters(String text) {
    return text.chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}
