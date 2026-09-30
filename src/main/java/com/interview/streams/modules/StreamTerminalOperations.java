package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 13: Stream Terminal Operations.
 * Practice anyMatch, allMatch, noneMatch, findFirst, findAny, count, max, and min.
 */
public class StreamTerminalOperations {

  /**
   * Problem 1: anyMatch()
   * <p>Check whether any number is even.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>anyMatch() stops as soon as one element matches.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public boolean hasEvenNumber(List<Integer> numbers) {
    return numbers.stream()
        .anyMatch(number -> number % 2 == 0);
  }

  /**
   * Problem 2: allMatch()
   * <p>Check whether all numbers are positive.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>allMatch() validates every element against one rule.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public boolean allPositive(List<Integer> numbers) {
    return numbers.stream()
        .allMatch(number -> number > 0);
  }

  /**
   * Problem 3: noneMatch()
   * <p>Check whether no string is blank.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>noneMatch() is the clean negative existence check.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public boolean hasNoBlankValues(List<String> values) {
    return values.stream()
        .noneMatch(value -> value == null || value.trim().isEmpty());
  }

  /**
   * Problem 4: findFirst()
   * <p>Return the first name starting with S.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>findFirst() respects encounter order.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public Optional<String> firstNameStartingWithS(List<String> names) {
    return names.stream()
        .filter(name -> name.startsWith("S"))
        .findFirst();
  }

  /**
   * Problem 5: findAny()
   * <p>Return any employee from Engineering.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>findAny() allows faster results in parallel streams when order is not important.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public Optional<Employee> anyEngineeringEmployee(List<Employee> employees) {
    return employees.parallelStream()
        .filter(employee -> "Engineering".equals(employee.getDepartment()))
        .findAny();
  }

  /**
   * Problem 6: count()
   * <p>Count active employees.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>count() terminates the stream and returns long.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public long activeEmployeeCount(List<Employee> employees) {
    return employees.stream()
        .filter(Employee::isActive)
        .count();
  }

  /**
   * Problem 7: max()
   * <p>Find max number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>max() uses a comparator and returns Optional.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Optional<Integer> maxNumber(List<Integer> numbers) {
    return numbers.stream()
        .max(Integer::compareTo);
  }

  /**
   * Problem 8: min()
   * <p>Find min number.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>min() is the symmetric terminal operation to max().</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Optional<Integer> minNumber(List<Integer> numbers) {
    return numbers.stream()
        .min(Integer::compareTo);
  }

  /**
   * Problem 9: Is any employee earning &gt; 1 lakh?
   * <p>Check whether any employee earns more than 100000.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use anyMatch() for existence questions.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public boolean hasEmployeeAboveOneLakh(List<Employee> employees) {
    return employees.stream()
        .anyMatch(employee -> employee.getSalary() > 100_000);
  }

  /**
   * Problem 10: Are all employees active?
   * <p>Check whether every employee is active.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use allMatch() for universal checks.</p>
   * <p>Complexity</p>
   * <p>Time O(n) worst case, Space O(1).</p>
   */
  public boolean areAllEmployeesActive(List<Employee> employees) {
    return employees.stream()
        .allMatch(Employee::isActive);
  }

  /**
   * Problem 11: Is any department empty?
   * <p>Given all departments and employees, check whether any department has no employee.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Build present department set, then use anyMatch() on master departments.</p>
   * <p>Complexity</p>
   * <p>Time O(n + d), Space O(d).</p>
   */
  public boolean hasEmptyDepartment(List<String> departments, List<Employee> employees) {
    Set<String> departmentsWithEmployees = employees.stream()
        .map(Employee::getDepartment)
        .collect(Collectors.toSet());

    return departments.stream()
        .anyMatch(department -> !departmentsWithEmployees.contains(department));
  }
}
