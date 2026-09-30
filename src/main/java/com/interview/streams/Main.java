package com.interview.streams;

import com.interview.streams.model.Employee;
import com.interview.streams.modules.*;
import java.util.*;

/** Small executable example; see the module classes for all 149 solutions. */
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 2, 7, 4);
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Asha", "Engineering", 90000, 28, "Female", true, Arrays.asList("Java", "SQL")),
            new Employee(2, "Ravi", "Engineering", 75000, 31, "Male", true, Arrays.asList("Java", "Docker")),
            new Employee(3, "Sana", "HR", 60000, 26, "Female", false, Arrays.asList("Recruiting"))
        );
        System.out.println("Java Lambda and Streams: 18 modules, 149 solutions");
        System.out.println("Sorted: " + new LambdaBasics().sortIntegers(numbers));
        System.out.println("Even: " + new Filtering().evenNumbers(numbers));
        System.out.println("Employee names: " + new Mapping().employeeNames(employees));
        System.out.println("Department counts: " + new GroupingBy().countByDepartment(employees));
        System.out.println("Skills: " + new FlatMap().uniqueSkills(employees));
        System.out.println("Second highest salary: " + new AdvancedEmployeeDatasetQuestions().secondHighestSalary(employees));
        System.out.println("First unique character: " + new StringStreamProblems().firstNonRepeatingCharacter("swiss").orElse('?'));
        System.out.println("Parallel sum: " + new ParallelStream().sumUsingParallelStream(numbers));
    }
}
