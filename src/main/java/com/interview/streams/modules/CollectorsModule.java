package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 7: Collectors.
 * Convert streams into lists, sets, maps, strings, counts, sums, averages, min, and max.
 */
public class CollectorsModule {

  /**
   * Problem 1: Convert stream to List
   * <p>Collect a stream into a List.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Collectors.toList() materializes stream output.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<String> collectToList(Stream<String> stream) {
    return stream.collect(Collectors.toList());
  }

  /**
   * Problem 2: Convert stream to Set
   * <p>Collect a stream into a Set.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Collectors.toSet() removes duplicate values.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Set<String> collectToSet(Stream<String> stream) {
    return stream.collect(Collectors.toSet());
  }

  /**
   * Problem 3: Convert stream to Map
   * <p>Convert employees into a map by id.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>toMap() needs key mapper, value mapper, and merge rule if keys can repeat.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Integer, Employee> employeeById(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.toMap(
            Employee::getId,
            Function.identity(),
            (existing, replacement) -> existing));
  }

  /**
   * Problem 4: Join strings with comma
   * <p>Join names with comma separator.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>joining() is the collector for string concatenation.</p>
   * <p>Complexity</p>
   * <p>Time O(total characters), Space O(total characters).</p>
   */
  public String joinWithComma(List<String> names) {
    return names.stream()
        .collect(Collectors.joining(", "));
  }

  /**
   * Problem 5: Count total elements
   * <p>Count stream elements.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>counting() is useful inside collectors; count() is direct terminal operation.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public long countNames(List<String> names) {
    return names.stream()
        .collect(Collectors.counting());
  }

  /**
   * Problem 6: Calculate average salary
   * <p>Calculate average employee salary.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>averagingDouble() summarizes numeric object fields.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public double averageSalary(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.averagingDouble(Employee::getSalary));
  }

  /**
   * Problem 7: Calculate total salary
   * <p>Calculate total employee salary.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>summingDouble() adds numeric object fields.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public double totalSalary(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.summingDouble(Employee::getSalary));
  }

  /**
   * Problem 8: Find maximum salary
   * <p>Find maximum employee salary.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>mapToDouble with max avoids Optional&lt;Employee&gt; when only salary is needed.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public double maximumSalary(List<Employee> employees) {
    return employees.stream()
        .mapToDouble(Employee::getSalary)
        .max()
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 9: Find minimum salary
   * <p>Find minimum employee salary.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>mapToDouble with min returns the smallest numeric field.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public double minimumSalary(List<Employee> employees) {
    return employees.stream()
        .mapToDouble(Employee::getSalary)
        .min()
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }
}
