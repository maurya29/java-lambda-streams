package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 4: Filtering.
 * Use filter() with predicates to keep only matching records.
 */
public class Filtering {

  /**
   * Problem 1: Find all even numbers
   * <p>Return all even numbers from a list.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>filter() keeps values that satisfy a Predicate.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> evenNumbers(List<Integer> numbers) {
    return numbers.stream()
        .filter(number -> number % 2 == 0)
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Find all odd numbers
   * <p>Return all odd numbers from a list.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use filter() when the output is a subset of the input.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> oddNumbers(List<Integer> numbers) {
    return numbers.stream()
        .filter(number -> number % 2 != 0)
        .collect(Collectors.toList());
  }

  /**
   * Problem 3: Find employees with salary &gt; 50000
   * <p>Return employees whose salary is greater than 50000.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Filter object lists by a numeric field.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Employee> highSalaryEmployees(List<Employee> employees) {
    return employees.stream()
        .filter(employee -> employee.getSalary() > 50_000)
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Find active users
   * <p>Return only active users.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Boolean getters are direct filter predicates.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<User> activeUsers(List<User> users) {
    return users.stream()
        .filter(User::isActive)
        .collect(Collectors.toList());
  }

  /**
   * Problem 5: Find products belonging to Electronics category
   * <p>Return products whose category is Electronics.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use filter() with equalsIgnoreCase() for category matching.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Product> electronicsProducts(List<Product> products) {
    return products.stream()
        .filter(product -> "Electronics".equalsIgnoreCase(product.getCategory()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 6: Find strings starting with A
   * <p>Return strings that start with uppercase A.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Prefix checks fit naturally inside filter().</p>
   * <p>Complexity</p>
   * <p>Time O(n * k), Space O(n).</p>
   */
  public List<String> startingWithA(List<String> values) {
    return values.stream()
        .filter(value -> value.startsWith("A"))
        .collect(Collectors.toList());
  }

  /**
   * Problem 7: Find strings ending with n
   * <p>Return strings that end with lowercase n.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Suffix checks are simple filter predicates.</p>
   * <p>Complexity</p>
   * <p>Time O(n * k), Space O(n).</p>
   */
  public List<String> endingWithN(List<String> values) {
    return values.stream()
        .filter(value -> value.endsWith("n"))
        .collect(Collectors.toList());
  }
}
