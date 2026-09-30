package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 8: GroupingBy.
 * Most asked employee grouping problems using Collectors.groupingBy().
 */
public class GroupingBy {

  /**
   * Problem 1: Group employees by department
   * <p>Return employees grouped by department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>groupingBy() partitions records by a classifier key.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment));
  }

  /**
   * Problem 2: Count employees per department
   * <p>Return department wise employee count.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use downstream counting() when grouped values are counts.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Long> countByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
  }

  /**
   * Problem 3: Find highest paid employee per department
   * <p>Return the highest paid employee for every department.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Use groupingBy() with maxBy() as a downstream collector.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
  }

  /**
   * Problem 4: Find average salary per department
   * <p>Return average salary for each department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>groupingBy() can run averagingDouble() per group.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.averagingDouble(Employee::getSalary)));
  }

  /**
   * Problem 5: Group employees by age
   * <p>Return employees grouped by age.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Any stable field can be the grouping key.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Integer, List<Employee>> groupByAge(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getAge));
  }

  /**
   * Problem 6: Find employees grouped by salary range
   * <p>Group employees into LOW, MID, and HIGH salary ranges.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use a custom classifier method when the key is derived.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<String, List<Employee>> groupBySalaryRange(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(this::salaryRange));
  }

  private String salaryRange(Employee employee) {
    if (employee.getSalary() < 50_000) {
      return "LOW";
    }
    if (employee.getSalary() <= 100_000) {
      return "MID";
    }
    return "HIGH";
  }

  /**
   * Problem 7: Multi-level grouping: Department then Age
   * <p>Group employees first by department and then by age.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Nested groupingBy() builds a map of maps.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<String, Map<Integer, List<Employee>>> groupByDepartmentThenAge(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.groupingBy(Employee::getAge)));
  }
}
