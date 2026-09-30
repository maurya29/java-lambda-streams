package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 12: FlatMap.
 * Flatten nested lists and one-to-many relationships.
 */
public class FlatMap {

  /**
   * Problem 1: Convert List&lt;List&lt;Integer&gt;&gt; to List&lt;Integer&gt;
   * <p>Flatten nested integer lists into one list.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>flatMap() removes one nesting level.</p>
   * <p>Complexity</p>
   * <p>Time O(total elements), Space O(total elements).</p>
   */
  public List<Integer> flattenNumbers(List<List<Integer>> nestedNumbers) {
    return nestedNumbers.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  /**
   * Problem 2: Flatten list of employee skills
   * <p>Return all skills from all employees.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Each employee maps to many skills, so map() is not enough.</p>
   * <p>Complexity</p>
   * <p>Time O(total skills), Space O(total skills).</p>
   */
  public List<String> allSkills(List<Employee> employees) {
    return employees.stream()
        .flatMap(employee -> employee.getSkills().stream())
        .collect(Collectors.toList());
  }

  /**
   * Problem 3: Flatten nested lists
   * <p>Flatten nested lists of strings.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>flatMap() converts Stream&lt;List&lt;T&gt;&gt; into Stream&lt;T&gt;.</p>
   * <p>Complexity</p>
   * <p>Time O(total elements), Space O(total elements).</p>
   */
  public List<String> flattenStrings(List<List<String>> nestedValues) {
    return nestedValues.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Get unique skills across all employees
   * <p>Return unique employee skills sorted alphabetically.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>flatMap() produces skills, distinct() deduplicates them.</p>
   * <p>Complexity</p>
   * <p>Time O(s log s), Space O(s), where s is total skills.</p>
   */
  public List<String> uniqueSkills(List<Employee> employees) {
    return employees.stream()
        .flatMap(employee -> employee.getSkills().stream())
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }
}
