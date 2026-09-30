package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 11: Optional.
 * Find values safely and handle absence with orElse, orElseGet, and orElseThrow.
 */
public class OptionalModule {

  /**
   * Problem 1: Find first employee
   * <p>Return the first employee as Optional.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>findFirst() returns Optional because the stream may be empty.</p>
   * <p>Complexity</p>
   * <p>Time O(1) for ordered lists, Space O(1).</p>
   */
  public Optional<Employee> firstEmployee(List<Employee> employees) {
    return employees.stream().findFirst();
  }

  /**
   * Problem 2: Find highest salary employee
   * <p>Return the employee with the highest salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>max() returns Optional for empty input.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Optional<Employee> highestSalaryEmployee(List<Employee> employees) {
    return employees.stream()
        .max(Comparator.comparingDouble(Employee::getSalary));
  }

  /**
   * Problem 3: Handle null safely
   * <p>Trim a nullable name and return Unknown when missing.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Optional.ofNullable() protects null before map/filter operations.</p>
   * <p>Complexity</p>
   * <p>Time O(k), Space O(k).</p>
   */
  public String cleanName(String name) {
    return Optional.ofNullable(name)
        .map(String::trim)
        .filter(value -> !value.isEmpty())
        .orElse("Unknown");
  }

  /**
   * Problem 4: Use orElse()
   * <p>Return default employee name when Optional is empty.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>orElse() supplies an eager fallback value.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public String employeeNameOrDefault(Optional<Employee> employee) {
    return employee.map(Employee::getName)
        .orElse("No employee");
  }

  /**
   * Problem 5: Use orElseGet()
   * <p>Return generated fallback text only when Optional is empty.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>orElseGet() lazily calls Supplier only on empty Optional.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public String employeeNameOrGeneratedDefault(Optional<Employee> employee) {
    return employee.map(Employee::getName)
        .orElseGet(() -> "Employee-" + UUID.randomUUID());
  }

  /**
   * Problem 6: Use orElseThrow()
   * <p>Return employee or throw a custom exception when absent.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>orElseThrow() is the strict branch for required values.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public Employee requiredEmployee(Optional<Employee> employee) {
    return employee.orElseThrow(() -> new NoSuchElementException("employee not found"));
  }
}
