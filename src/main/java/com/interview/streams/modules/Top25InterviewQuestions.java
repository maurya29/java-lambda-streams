package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 18: Top 25 Interview Questions.
 * Revise these repeatedly for Java 8 Stream and Lambda interviews.
 */
public class Top25InterviewQuestions {

  /**
   * Problem 1: Second highest salary employee
   * <p>Find an employee whose salary is the second highest distinct salary.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Find second salary first, then locate matching employee.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public Employee secondHighestSalaryEmployee(List<Employee> employees) {
    double secondSalary = employees.stream()
        .map(Employee::getSalary)
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("second salary not found"));

    return employees.stream()
        .filter(employee -> employee.getSalary() == secondSalary)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("employee not found"));
  }

  /**
   * Problem 2: Highest salary per department
   * <p>Find highest salary employee for every department.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Group employees and use maxBy salary as downstream collector.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Employee> highestSalaryPerDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
  }

  /**
   * Problem 3: Group employees by department
   * <p>Group employees by department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Classic groupingBy classifier problem.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<String, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment));
  }

  /**
   * Problem 4: Count employees by department
   * <p>Count employees per department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use counting downstream collector.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Long> countEmployeesByDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
  }

  /**
   * Problem 5: Duplicate elements in list
   * <p>Return duplicate values from a list.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Frequency map detects repeated elements.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Set<Integer> duplicateElements(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Problem 6: First non-repeating character
   * <p>Return first non-repeating character from a string.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>LinkedHashMap keeps original character order.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Optional<Character> firstNonRepeatingCharacter(String text) {
    Map<Character, Long> frequency = text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

    return frequency.entrySet().stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst();
  }

  /**
   * Problem 7: Frequency of characters
   * <p>Return character frequency map.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Box chars and group by identity.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Map<Character, Long> frequencyOfCharacters(String text) {
    return text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
  }

  /**
   * Problem 8: Frequency of words
   * <p>Return word frequency map.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Split into words and group by identity.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Map<String, Long> frequencyOfWords(String sentence) {
    return Arrays.stream(sentence.trim().split("\\s+"))
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
  }

  /**
   * Problem 9: Partition even/odd
   * <p>Partition numbers into even and odd.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Boolean split means partitioningBy().</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.partitioningBy(number -> number % 2 == 0));
  }

  /**
   * Problem 10: Top 3 salaries
   * <p>Return top three distinct salaries.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Sort distinct salary values descending and limit three.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Double> topThreeSalaries(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getSalary)
        .distinct()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .collect(Collectors.toList());
  }

  /**
   * Problem 11: Flatten nested list
   * <p>Flatten nested integer lists.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>flatMap() removes one collection level.</p>
   * <p>Complexity</p>
   * <p>Time O(total elements), Space O(total elements).</p>
   */
  public List<Integer> flattenNestedList(List<List<Integer>> nestedNumbers) {
    return nestedNumbers.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  /**
   * Problem 12: Remove duplicates
   * <p>Remove duplicates from a list.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>distinct() removes repeated values.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<Integer> removeDuplicates(List<Integer> numbers) {
    return numbers.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Problem 13: Sort employee by salary
   * <p>Sort employees by salary descending.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use comparingDouble and reversed().</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortEmployeeBySalary(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .collect(Collectors.toList());
  }

  /**
   * Problem 14: Average salary per department
   * <p>Find average salary by department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group by department with averagingDouble.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public Map<String, Double> averageSalaryPerDepartment(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
  }

  /**
   * Problem 15: Department with highest average salary
   * <p>Find department with maximum average salary.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Aggregate first, then max by entry value.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(d).</p>
   */
  public String departmentWithHighestAverageSalary(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 16: Find duplicate names
   * <p>Find repeated employee names.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Group employee names and keep counts greater than one.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Set<String> duplicateNames(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getName)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Problem 17: Join all names with comma
   * <p>Join employee names with comma.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Map to names then use joining().</p>
   * <p>Complexity</p>
   * <p>Time O(total characters), Space O(total characters).</p>
   */
  public String joinAllNamesWithComma(List<Employee> employees) {
    return employees.stream()
        .map(Employee::getName)
        .collect(Collectors.joining(", "));
  }

  /**
   * Problem 18: Convert list to map
   * <p>Convert employees into map keyed by id.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>toMap requires a key mapper and value mapper.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Integer, Employee> listToMap(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.toMap(Employee::getId, Function.identity(), (first, second) -> first));
  }

  /**
   * Problem 19: Salary statistics
   * <p>Return salary summary statistics.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>summaryStatistics gives min, max, sum, count, and average.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public DoubleSummaryStatistics salaryStatistics(List<Employee> employees) {
    return employees.stream()
        .mapToDouble(Employee::getSalary)
        .summaryStatistics();
  }

  /**
   * Problem 20: Find youngest employee
   * <p>Return employee with minimum age.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>min() with comparing age.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Employee youngestEmployee(List<Employee> employees) {
    return employees.stream()
        .min(Comparator.comparingInt(Employee::getAge))
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 21: Find oldest employee
   * <p>Return employee with maximum age.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>max() with comparing age.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Employee oldestEmployee(List<Employee> employees) {
    return employees.stream()
        .max(Comparator.comparingInt(Employee::getAge))
        .orElseThrow(() -> new NoSuchElementException("employees is empty"));
  }

  /**
   * Problem 22: Find common elements in arrays
   * <p>Return unique common values from two arrays.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Set lookup keeps the stream filter O(1) average per element.</p>
   * <p>Complexity</p>
   * <p>Time O(n + m), Space O(m).</p>
   */
  public List<Integer> commonElements(int[] first, int[] second) {
    Set<Integer> secondSet = Arrays.stream(second).boxed().collect(Collectors.toSet());
    return Arrays.stream(first)
        .boxed()
        .filter(secondSet::contains)
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Problem 23: Second largest number
   * <p>Return second largest distinct number.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Distinct, sort descending, skip one.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public int secondLargestNumber(List<Integer> numbers) {
    return numbers.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("second largest not found"));
  }

  /**
   * Problem 24: Missing number
   * <p>Find missing number from 1 to n.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Subtract actual stream sum from expected arithmetic sum.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int missingNumber(int[] numbers, int n) {
    return n * (n + 1) / 2 - Arrays.stream(numbers).sum();
  }

  /**
   * Problem 25: String character count
   * <p>Count every character in a string.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>chars() plus groupingBy creates the frequency map.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k).</p>
   */
  public Map<Character, Long> stringCharacterCount(String text) {
    return text.chars()
        .mapToObj(ch -> (char) ch)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
  }
}
