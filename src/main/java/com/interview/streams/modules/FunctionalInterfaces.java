package com.interview.streams.modules;

import com.interview.streams.model.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Module 2: Functional Interfaces.
 * Predicate, Function, Consumer, Supplier, chaining, and method references.
 */
public class FunctionalInterfaces {

  /**
   * Problem 1: Predicate to check even numbers
   * <p>Use Predicate to test whether a number is even.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Predicate&lt;T&gt; returns boolean and fits filter/test logic.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public boolean isEven(int value) {
    Predicate<Integer> even = number -> number % 2 == 0;
    return even.test(value);
  }

  /**
   * Problem 2: Predicate to check palindrome
   * <p>Use Predicate to test whether a string is a palindrome.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Predicate is useful when a reusable yes/no rule is needed.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public boolean isPalindrome(String value) {
    Predicate<String> palindrome = text -> IntStream.range(0, text.length() / 2)
        .allMatch(i -> text.charAt(i) == text.charAt(text.length() - 1 - i));
    return palindrome.test(value);
  }

  /**
   * Problem 3: Predicate chaining using and(), or(), negate()
   * <p>Filter strings that are nonblank and either start with A or are long.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Predicate chaining keeps business rules composable.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(n).</p>
   */
  public List<String> filterImportantWords(List<String> words) {
    Predicate<String> nonBlank = word -> word != null && !word.trim().isEmpty();
    Predicate<String> startsWithA = word -> word.startsWith("A");
    Predicate<String> longWord = word -> word.length() >= 8;

    return words.stream()
        .filter(nonBlank.and(startsWithA.or(longWord)).and(nonBlank.negate().negate()))
        .collect(Collectors.toList());
  }

  /**
   * Problem 4: Function to convert String to Integer
   * <p>Convert a numeric string into an Integer using Function.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Function&lt;T,R&gt; transforms one type into another.</p>
   * <p>Complexity</p>
   * <p>Time O(k), Space O(1), where k is string length.</p>
   */
  public int parseNumber(String value) {
    Function<String, Integer> parse = Integer::parseInt;
    return parse.apply(value);
  }

  /**
   * Problem 5: Function to calculate square
   * <p>Use Function to calculate the square of an integer.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Function can represent reusable transformation logic.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public int square(int value) {
    Function<Integer, Integer> square = number -> number * number;
    return square.apply(value);
  }

  /**
   * Problem 6: Function chaining using andThen()
   * <p>Trim a string, parse it, then square the parsed value.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>andThen() feeds the previous output into the next function.</p>
   * <p>Complexity</p>
   * <p>Time O(k), Space O(k).</p>
   */
  public int parseThenSquare(String value) {
    Function<String, String> trim = String::trim;
    Function<String, Integer> parse = Integer::parseInt;
    Function<Integer, Integer> square = number -> number * number;

    return trim.andThen(parse).andThen(square).apply(value);
  }

  /**
   * Problem 7: Consumer to print Employee details
   * <p>Use Consumer to print details for every employee.</p>
   * <p>Easy</p>
   * <p>Trigger point</p>
   * <p>Consumer&lt;T&gt; performs an action without returning a value.</p>
   * <p>Complexity</p>
   * <p>Time O(n), Space O(1).</p>
   */
  public void printEmployees(List<Employee> employees) {
    Consumer<Employee> printer = employee -> System.out.println(
        employee.getId() + " " + employee.getName() + " " + employee.getSalary());

    employees.forEach(printer);
  }

  /**
   * Problem 8: Supplier to generate random OTP
   * <p>Use Supplier to generate a six-digit OTP.</p>
   * <p>Medium</p>
   * <p>Trigger point</p>
   * <p>Supplier&lt;T&gt; produces a value without input.</p>
   * <p>Complexity</p>
   * <p>Time O(1), Space O(1).</p>
   */
  public String generateOtp() {
    SecureRandom random = new SecureRandom();
    Supplier<String> otpSupplier = () -> String.format("%06d", random.nextInt(1_000_000));
    return otpSupplier.get();
  }
}
