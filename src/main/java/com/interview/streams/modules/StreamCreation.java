package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 3: Stream Creation.
 * Create streams from collections, arrays, factory methods, and infinite generators.
 */
public class StreamCreation {

  /**
   * Problem 1: Create stream from List
   * <p>Create a stream from a List and collect uppercase values.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>List already exposes stream() for sequential pipelines.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<String> uppercase(List<String> names) {
    return names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Create stream from Array
   * <p>Create a stream from an int array and calculate sum.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Arrays.stream() creates primitive and object streams from arrays.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int sum(int[] numbers) {
    return Arrays.stream(numbers).sum();
  }

  /**
   * Problem 3: Create stream using Stream.of()
   * <p>Create a stream from fixed values using Stream.of().</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Stream.of() is best for small fixed input lists.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<String> fixedNames() {
    return Stream.of("Asha", "Ravi", "Sana")
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Create infinite stream using iterate()
   * <p>Generate the first n even numbers using Stream.iterate().</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>iterate() creates the next value from the previous value.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> firstEvenNumbers(int count) {
    return Stream.iterate(0, number -> number + 2)
        .limit(count)
        .collect(Collectors.toList());
  }

  /**
   * Problem 5: Create infinite stream using generate()
   * <p>Generate random numbers with Stream.generate().</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>generate() repeatedly calls a Supplier with no previous state.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> randomNumbers(int count) {
    SecureRandom random = new SecureRandom();
    return Stream.generate(() -> random.nextInt(100))
        .limit(count)
        .collect(Collectors.toList());
  }
}
