package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 9: PartitioningBy.
 * Split data into true and false buckets.
 */
public class PartitioningBy {

  /**
   * Problem 1: Partition employees by salary &gt; 50000
   * <p>Partition employees into salary greater than 50000 and not greater than 50000.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>partitioningBy() is groupingBy() for boolean keys.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Boolean, List<Employee>> partitionByHighSalary(List<Employee> employees) {
    return employees.stream()
        .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50_000));
  }

  /**
   * Problem 2: Partition numbers into even and odd
   * <p>Partition numbers into even and odd groups.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Boolean tests should use partitioningBy() instead of groupingBy().</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
    return numbers.stream()
        .collect(Collectors.partitioningBy(number -> number % 2 == 0));
  }

  /**
   * Problem 3: Partition students into pass/fail
   * <p>Partition students by marks greater than or equal to pass marks.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>partitioningBy() cleanly names pass/fail as true/false buckets.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public Map<Boolean, List<Student>> partitionPassFail(List<Student> students, int passMarks) {
    return students.stream()
        .collect(Collectors.partitioningBy(student -> student.getMarks() >= passMarks));
  }
}
