package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 14: Advanced Employee Dataset Questions.
 * Frequently asked employee analytics with Stream API.
 */
public class AdvancedEmployeeDatasetQuestions {

  /**
   * Problem 1: Find second highest salary
   * <p>Return the second highest distinct salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use distinct salaries, sort descending, then skip one.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public double secondHighestSalary(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getSalary)
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("second salary not found"));
  }

  /**
   * Problem 2: Find third highest salary
   * <p>Return the third highest distinct salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>skip(2) after descending distinct salaries.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public double thirdHighestSalary(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getSalary)
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(2)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("third salary not found"));
  }

  /**
   * Problem 3: Find top 3 highest paid employees
   * <p>Return the top 3 employees by salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Sort objects by salary descending and limit result.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> topThreeHighestPaidEmployees(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .limit(3)
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Find duplicate employee names
   * <p>Return employee names that occur more than once.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group by name and keep groups with count greater than one.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Set<String> duplicateEmployeeNames(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Problem 5: Count employees in each department
   * <p>Return employee count per department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>groupingBy with counting is the standard count-per-key pattern.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Long> countEmployeesByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
  }

  /**
   * Problem 6: Department with highest average salary
   * <p>Return the department whose average salary is highest.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Compute average per department, then max over map entries.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public String departmentWithHighestAverageSalary(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.averagingDouble(Employee::getSalary)))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 7: Youngest employee
   * <p>Return the employee with minimum age.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>min() with comparingInt finds youngest by age.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Employee youngestEmployee(List<Employee> employees) {
    return employees.stream()
        .min(Comparator.comparingInt(Employee::getAge))
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 8: Oldest employee
   * <p>Return the employee with maximum age.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>max() with comparingInt finds oldest by age.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Employee oldestEmployee(List<Employee> employees) {
    return employees.stream()
        .max(Comparator.comparingInt(Employee::getAge))
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 9: Employee with longest name
   * <p>Return employee whose name has maximum length.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Compare derived string length.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Employee employeeWithLongestName(List<Employee> employees) {
    return employees.stream()
        .max(Comparator.comparingInt(employee -> employee.getName().length()))
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 10: Average salary by gender
   * <p>Return average salary grouped by gender.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group by gender with averagingDouble downstream collector.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(g).</p>
   */
  public Map<String, Double> averageSalaryByGender(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getGender,
            Collectors.averagingDouble(Employee::getSalary)));
  }

  /**
   * Problem 11: Highest paid employee in each department
   * <p>Return the highest paid employee per department.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Group by department and use maxBy salary inside each group.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Employee> highestPaidEmployeeByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
  }

  /**
   * Problem 12: Department having maximum employees
   * <p>Return the department with the highest number of employees.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Count per department, then find max count.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public String departmentHavingMaximumEmployees(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 13: Sort employees by department then salary
   * <p>Sort employees by department ascending and salary descending.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Comparator chaining can mix ascending and descending fields.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortByDepartmentThenSalaryDesc(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparing(Employee::getDepartment)
            .thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 14: Find employees whose names start with S
   * <p>Return employees with names starting with S.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use filter on object field.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Employee> employeesWhoseNamesStartWithS(List<Employee> employees) {
    return employees.stream()
        .filter(employee -> employee.getName().startsWith("S"))
        .collect(Collectors.toList());
  }

  /**
   * Problem 15: Find salary statistics
   * <p>Return count, sum, min, average, and max salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>summaryStatistics() gives all salary aggregates in one pass.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public DoubleSummaryStatistics salaryStatistics(List<Employee> employees) {
    return employees.stream()
        .mapToDouble(Employee::getSalary)
        .summaryStatistics();
  }
}
