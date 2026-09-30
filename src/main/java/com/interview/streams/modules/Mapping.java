package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 5: Mapping.
 * Use map() to transform each input element into another value.
 */
public class Mapping {

  /**
   * Problem 1: Convert all names to uppercase
   * <p>Return names converted to uppercase.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>map() transforms every element.</p>
   * <p>Complexity</p>
   * <p>Time O(n * k), Space O(n * k).</p>
   */
  public List<String> toUppercase(List<String> names) {
    return names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Convert all names to lowercase
   * <p>Return names converted to lowercase.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use map() when output size equals input size.</p>
   * <p>Complexity</p>
   * <p>Time O(n * k), Space O(n * k).</p>
   */
  public List<String> toLowercase(List<String> names) {
    return names.stream()
        .map(String::toLowerCase)
        .collect(Collectors.toList());
  }

  /**
   * Problem 3: Extract employee names from Employee list
   * <p>Return only employee names.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Map objects to one selected field.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<String> employeeNames(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getName)
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Extract employee salaries
   * <p>Return employee salaries.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use mapToDouble for numeric primitive output.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Double> employeeSalaries(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getSalary)
        .collect(Collectors.toList());
  }

  /**
   * Problem 5: Convert List&lt;String&gt; to List&lt;Integer&gt;
   * <p>Parse all numeric strings into integers.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>map() changes element type from String to Integer.</p>
   * <p>Complexity</p>
   * <p>Time O(n * k), Space O(n).</p>
   */
  public List<Integer> parseNumbers(List<String> values) {
    return values.stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  /**
   * Problem 6: Get length of each string
   * <p>Return the length of every string.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Mapping can derive a computed property.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> lengths(List<String> values) {
    return values.stream()
        .map(String::length)
        .collect(Collectors.toList());
  }
}
