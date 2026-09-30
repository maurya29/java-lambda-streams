package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 6: Distinct and Sorting.
 * Deduplicate and order values or objects.
 */
public class DistinctAndSorting {

  /**
   * Problem 1: Remove duplicates
   * <p>Return values without duplicates while preserving encounter order.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>distinct() removes duplicates using equals() and hashCode().</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> removeDuplicates(List<Integer> numbers) {
    return numbers.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Sort ascending
   * <p>Sort numbers in ascending order.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>sorted() uses natural order for comparable values.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Integer> sortAscending(List<Integer> numbers) {
    return numbers.stream()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Problem 3: Sort descending
   * <p>Sort numbers in descending order.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use reverseOrder() when natural order must be flipped.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Integer> sortDescending(List<Integer> numbers) {
    return numbers.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Sort strings alphabetically
   * <p>Sort strings in lexicographic order.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>String has natural alphabetical ordering.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n * k), Space O(n).</p>
   */
  public List<String> sortAlphabetically(List<String> values) {
    return values.stream()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Problem 5: Sort strings by length
   * <p>Sort strings by length, then alphabetically.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use comparator chaining for deterministic ties.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<String> sortByLengthThenName(List<String> values) {
    return values.stream()
        .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 6: Sort employees by salary
   * <p>Sort employees by salary in descending order.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Object sorting requires a field-based comparator.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortEmployeesBySalaryDesc(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .collect(Collectors.toList());
  }

  /**
   * Problem 7: Sort employees by age
   * <p>Sort employees by age in ascending order.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use comparingInt for primitive int fields.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortEmployeesByAge(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparingInt(Employee::getAge))
        .collect(Collectors.toList());
  }
}
