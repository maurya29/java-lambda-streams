package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 10: Reduction.
 * Use reduce() for folding streams into one value.
 */
public class Reduction {

  /**
   * Problem 1: Sum of all numbers
   * <p>Use reduce() to calculate the sum of all numbers.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>reduce() combines elements into a single accumulated result.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int sum(List<Integer> numbers) {
    return numbers.stream()
        .reduce(0, Integer::sum);
  }

  /**
   * Problem 2: Product of all numbers
   * <p>Use reduce() to calculate product of all numbers.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>The identity for multiplication is 1.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int product(List<Integer> numbers) {
    return numbers.stream()
        .reduce(1, (left, right) -> left * right);
  }

  /**
   * Problem 3: Maximum number
   * <p>Use reduce() to find the maximum number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use a binary operator that keeps the better value.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int maximum(List<Integer> numbers) {
    return numbers.stream()
        .reduce(Integer::max)
        .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
  }

  /**
   * Problem 4: Minimum number
   * <p>Use reduce() to find the minimum number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>A reduction without identity returns Optional for empty safety.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int minimum(List<Integer> numbers) {
    return numbers.stream()
        .reduce(Integer::min)
        .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
  }

  /**
   * Problem 5: Total salary of employees
   * <p>Use reduce() to add employee salaries.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Map to salary first, then reduce doubles.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public double totalSalary(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getSalary)
        .reduce(0.0, Double::sum);
  }

  /**
   * Problem 6: Concatenate strings
   * <p>Use reduce() to concatenate strings with spaces.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Reduction can combine strings, but joining() is usually preferred.</p>
   * <p>Complexity</p>
   * <p>Time O(total characters squared) in worst case, Space O(total characters).</p>
   */
  public String concatenate(List<String> words) {
    return words.stream()
        .reduce("", (left, right) -> left.isEmpty() ? right : left + " " + right);
  }
}
