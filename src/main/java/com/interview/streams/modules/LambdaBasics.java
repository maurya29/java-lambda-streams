package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 1: Lambda Basics.
 * Syntax, comparator lambdas, functional interfaces, and replacing anonymous classes.
 */
public class LambdaBasics {

  /**
   * Problem 1: Sort a list of integers using lambda
   * <p>Return a sorted copy of a list of integers using a lambda comparator.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use a lambda when Comparator logic is short and local to the sort.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Integer> sortIntegers(List<Integer> numbers) {
    return numbers.stream()
        .sorted((a, b) -> Integer.compare(a, b))
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Sort strings by length
   * <p>Sort strings from shortest to longest.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Comparator compares derived values instead of the original string.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<String> sortByLength(List<String> words) {
    return words.stream()
        .sorted((a, b) -> Integer.compare(a.length(), b.length()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 3: Find maximum element using lambda comparator
   * <p>Find the maximum number in a list using max() and a lambda comparator.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>max() needs comparison logic; lambda supplies it inline.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public int maxNumber(List<Integer> numbers) {
    return numbers.stream()
        .max((a, b) -> Integer.compare(a, b))
        .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
  }

  /**
   * Problem 4: Create a custom functional interface and implement it using lambda
   * <p>Create a calculator-style interface and implement it with lambda expressions.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>A lambda can implement any interface with exactly one abstract method.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  @FunctionalInterface
  interface Operation {
    int apply(int left, int right);
  }

  public int calculateSum(int a, int b) {
    Operation add = (left, right) -> left + right;
    return add.apply(a, b);
  }

  /**
   * Problem 5: Replace anonymous classes with lambda expressions
   * <p>Replace an anonymous Runnable class with a lambda expression.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Anonymous classes with one method are direct lambda candidates.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public Runnable buildTask() {
    return () -> System.out.println("Task is running");
  }

  public void runTask() {
    buildTask().run();
  }

  /**
   * Problem 6: Sort Employee objects by salary
   * <p>Sort employees by salary in ascending order.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Comparator can read a field through a getter and compare that value.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortBySalary(List<Employee> employees) {
    return employees.stream()
        .sorted((a, b) -> Double.compare(a.getSalary(), b.getSalary()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 7: Sort Employee by department and salary
   * <p>Sort employees by department, then salary inside each department.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Use comparator chaining when one field breaks ties for another field.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortByDepartmentAndSalary(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparing(Employee::getDepartment)
            .thenComparingDouble(Employee::getSalary))
        .collect(Collectors.toList());
  }

  /**
   * Problem 8: Chain Comparators using thenComparing()
   * <p>Sort employees by department, then age, then name.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>thenComparing() creates stable priority order across multiple fields.</p>
   * <p>Complexity</p>
   * <p>Time O(n log n), Space O(n).</p>
   */
  public List<Employee> sortByDepartmentAgeAndName(List<Employee> employees) {
    return employees.stream()
        .sorted(Comparator.comparing(Employee::getDepartment)
            .thenComparingInt(Employee::getAge)
            .thenComparing(Employee::getName))
        .collect(Collectors.toList());
  }
}
