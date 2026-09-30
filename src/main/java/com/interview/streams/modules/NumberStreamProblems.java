package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 16: Number Stream Problems.
 * Numeric Stream API patterns for duplicates, missing values, ranking, and set operations.
 */
public class NumberStreamProblems {

  /**
   * Problem 1: Find duplicates
   * <p>Return duplicate numbers from a list.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group by value and keep counts greater than one.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Set<Integer> duplicateNumbers(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Problem 2: Find missing number
   * <p>Given numbers from 1 to n with one missing, return the missing number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use arithmetic sum and stream sum.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int missingNumber(int[] numbers, int n) {
    int expected = n * (n + 1) / 2;
    int actual = Arrays.stream(numbers).sum();
    return expected - actual;
  }

  /**
   * Problem 3: Find second largest
   * <p>Return the second largest distinct number.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use distinct sorted descending and skip one.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public int secondLargest(List<Integer> numbers) {
    return numbers.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("second largest not found"));
  }

  /**
   * Problem 4: Find second smallest
   * <p>Return the second smallest distinct number.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use distinct sorted ascending and skip one.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public int secondSmallest(List<Integer> numbers) {
    return numbers.stream()
        .distinct()
        .sorted()
        .skip(1)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("second smallest not found"));
  }

  /**
   * Problem 5: Find frequency of numbers
   * <p>Return frequency of every number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>groupingBy(Function.identity(), counting()) is the frequency-map pattern.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Integer, Long> frequency(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  /**
   * Problem 6: Separate even and odd
   * <p>Partition numbers into even and odd groups.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>partitioningBy() creates true and false buckets.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Boolean, List<Integer>> separateEvenOdd(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.partitioningBy(number -> number % 2 == 0));
  }

  /**
   * Problem 7: Find common elements in two arrays
   * <p>Return unique numbers present in both arrays.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Build a set from one array, filter the other, then distinct.</p>
   * <p>Complexity</p>
   * <p>Time O(n + m), Space O(m).</p>
   */
  public List<Integer> commonElements(int[] first, int[] second) {
    Set<Integer> secondValues = Arrays.stream(second)
        .boxed()
        .collect(Collectors.toSet());

    return Arrays.stream(first)
        .boxed()
        .filter(secondValues::contains)
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Problem 8: Find intersection
   * <p>Return intersection preserving duplicate counts.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use a frequency map for the second array and decrement as matches are used.</p>
   * <p>Complexity</p>
   * <p>Time O(n + m), Space O(m).</p>
   */
  public List<Integer> intersectionWithCounts(int[] first, int[] second) {
    Map<Integer, Long> counts = Arrays.stream(second)
        .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return Arrays.stream(first)
        .boxed()
        .filter(value -> {
          long count = counts.getOrDefault(value, 0L);
          if (count == 0) {
            return false;
          }
          counts.put(value, count - 1);
          return true;
        })
        .collect(Collectors.toList());
  }

  /**
   * Problem 9: Find union
   * <p>Return union of two arrays without duplicates.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>concat() merges streams; distinct() removes duplicates.</p>
   * <p>Complexity</p>
   * <p>Time O(n + m), Space O(n + m).</p>
   */
  public List<Integer> union(int[] first, int[] second) {
    return Stream.concat(Arrays.stream(first).boxed(), Arrays.stream(second).boxed())
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Problem 10: Find top K largest numbers
   * <p>Return top k largest distinct numbers.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Sort descending, then limit k.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Integer> topKLargest(List<Integer> numbers, int k) {
    return numbers.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .limit(k)
        .collect(Collectors.toList());
  }
}
