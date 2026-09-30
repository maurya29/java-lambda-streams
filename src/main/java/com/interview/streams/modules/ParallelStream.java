package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 17: Parallel Stream.
 * When parallel streams help, how to measure them, and how to avoid shared mutable state.
 */
public class ParallelStream {

  /**
   * Problem 1: Difference between Stream and ParallelStream
   * <p>Show sequential and parallel sum methods for the same input.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>parallelStream() splits work across the common ForkJoinPool; stream() stays sequential.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1); parallel overhead depends on data size and CPU cores.</p>
   */
  public long sequentialSum(List<Integer> numbers) {
    return numbers.stream()
        .mapToLong(Integer::longValue)
        .sum();
  }

  public long parallelSum(List<Integer> numbers) {
    return numbers.parallelStream()
        .mapToLong(Integer::longValue)
        .sum();
  }

  /**
   * Problem 2: Calculate sum using parallel stream
   * <p>Calculate sum using parallel stream.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Use parallelStream() for independent associative operations.</p>
   * <p>Complexity</p>
   * <p>Time O(n / p) ideal with p processors, Space O(1).</p>
   */
  public long sumUsingParallelStream(List<Integer> numbers) {
    return numbers.parallelStream()
        .mapToLong(Integer::longValue)
        .sum();
  }

  /**
   * Problem 3: Find performance difference
   * <p>Measure sequential and parallel sum duration.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Benchmark both paths on the same input before choosing parallel stream.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public Map<String, Long> compareSequentialAndParallel(List<Integer> numbers) {
    long sequentialStart = System.nanoTime();
    numbers.stream().mapToLong(Integer::longValue).sum();
    long sequentialTime = System.nanoTime() - sequentialStart;

    long parallelStart = System.nanoTime();
    numbers.parallelStream().mapToLong(Integer::longValue).sum();
    long parallelTime = System.nanoTime() - parallelStart;

    Map<String, Long> result = new LinkedHashMap<>();
    result.put("sequentialNanos", sequentialTime);
    result.put("parallelNanos", parallelTime);
    return result;
  }

  /**
   * Problem 4: Thread safety issues in parallel stream
   * <p>Count words safely in a parallel stream.</p>
   * <p>Hard</p>
   * <p>Trigger point</p>
   * <p>Avoid mutating shared HashMap/List inside forEach; use concurrent collectors.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(k); parallel speed depends on contention and input size.</p>
   */
  public Map<String, Long> safeWordFrequency(List<String> words) {
    return words.parallelStream()
        .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));
  }
}
