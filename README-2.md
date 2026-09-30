# Java Lambda and Streams - Questions and Solutions

## Topic 1: Lambda Basics

### 1. Sort a list of integers using lambda

Return a sorted copy of a list of integers using a lambda comparator.

```java
public List<Integer> sortIntegers(List<Integer> numbers) {
  return numbers.stream()
      .sorted((a, b) -> Integer.compare(a, b))
      .collect(Collectors.toList());
}
```

### 2. Sort strings by length

Sort strings from shortest to longest.

```java
public List<String> sortByLength(List<String> words) {
  return words.stream()
      .sorted((a, b) -> Integer.compare(a.length(), b.length()))
      .collect(Collectors.toList());
}
```

### 3. Find maximum element using lambda comparator

Find the maximum number in a list using max() and a lambda comparator.

```java
public int maxNumber(List<Integer> numbers) {
  return numbers.stream()
      .max((a, b) -> Integer.compare(a, b))
      .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
}
```

### 4. Create a custom functional interface and implement it using lambda

Create a calculator-style interface and implement it with lambda expressions.

```java
@FunctionalInterface
interface Operation {
  int apply(int left, int right);
}

public int calculateSum(int a, int b) {
  Operation add = (left, right) -> left + right;
  return add.apply(a, b);
}
```

### 5. Replace anonymous classes with lambda expressions

Replace an anonymous Runnable class with a lambda expression.

```java
public Runnable buildTask() {
  return () -> System.out.println("Task is running");
}

public void runTask() {
  buildTask().run();
}
```

### 6. Sort Employee objects by salary

Sort employees by salary in ascending order.

```java
public List<Employee> sortBySalary(List<Employee> employees) {
  return employees.stream()
      .sorted((a, b) -> Double.compare(a.getSalary(), b.getSalary()))
      .collect(Collectors.toList());
}
```

### 7. Sort Employee by department and salary

Sort employees by department, then salary inside each department.

```java
public List<Employee> sortByDepartmentAndSalary(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparing(Employee::getDepartment)
          .thenComparingDouble(Employee::getSalary))
      .collect(Collectors.toList());
}
```

### 8. Chain Comparators using thenComparing()

Sort employees by department, then age, then name.

```java
public List<Employee> sortByDepartmentAgeAndName(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparing(Employee::getDepartment)
          .thenComparingInt(Employee::getAge)
          .thenComparing(Employee::getName))
      .collect(Collectors.toList());
}
```

## Topic 2: Functional Interfaces

### 1. Predicate to check even numbers

Use Predicate to test whether a number is even.

```java
public boolean isEven(int value) {
  Predicate<Integer> even = number -> number % 2 == 0;
  return even.test(value);
}
```

### 2. Predicate to check palindrome

Use Predicate to test whether a string is a palindrome.

```java
public boolean isPalindrome(String value) {
  Predicate<String> palindrome = text -> IntStream.range(0, text.length() / 2)
      .allMatch(i -> text.charAt(i) == text.charAt(text.length() - 1 - i));
  return palindrome.test(value);
}
```

### 3. Predicate chaining using and(), or(), negate()

Filter strings that are nonblank and either start with A or are long.

```java
public List<String> filterImportantWords(List<String> words) {
  Predicate<String> nonBlank = word -> word != null && !word.trim().isEmpty();
  Predicate<String> startsWithA = word -> word.startsWith("A");
  Predicate<String> longWord = word -> word.length() >= 8;

  return words.stream()
      .filter(nonBlank.and(startsWithA.or(longWord)).and(nonBlank.negate().negate()))
      .collect(Collectors.toList());
}
```

### 4. Function to convert String to Integer

Convert a numeric string into an Integer using Function.

```java
public int parseNumber(String value) {
  Function<String, Integer> parse = Integer::parseInt;
  return parse.apply(value);
}
```

### 5. Function to calculate square

Use Function to calculate the square of an integer.

```java
public int square(int value) {
  Function<Integer, Integer> square = number -> number * number;
  return square.apply(value);
}
```

### 6. Function chaining using andThen()

Trim a string, parse it, then square the parsed value.

```java
public int parseThenSquare(String value) {
  Function<String, String> trim = String::trim;
  Function<String, Integer> parse = Integer::parseInt;
  Function<Integer, Integer> square = number -> number * number;

  return trim.andThen(parse).andThen(square).apply(value);
}
```

### 7. Consumer to print Employee details

Use Consumer to print details for every employee.

```java
public void printEmployees(List<Employee> employees) {
  Consumer<Employee> printer = employee -> System.out.println(
      employee.getId() + " " + employee.getName() + " " + employee.getSalary());

  employees.forEach(printer);
}
```

### 8. Supplier to generate random OTP

Use Supplier to generate a six-digit OTP.

```java
public String generateOtp() {
  SecureRandom random = new SecureRandom();
  Supplier<String> otpSupplier = () -> String.format("%06d", random.nextInt(1_000_000));
  return otpSupplier.get();
}
```

## Topic 3: Stream Creation

### 1. Create stream from List

Create a stream from a List and collect uppercase values.

```java
public List<String> uppercase(List<String> names) {
  return names.stream()
      .map(String::toUpperCase)
      .collect(Collectors.toList());
}
```

### 2. Create stream from Array

Create a stream from an int array and calculate sum.

```java
public int sum(int[] numbers) {
  return Arrays.stream(numbers).sum();
}
```

### 3. Create stream using Stream.of()

Create a stream from fixed values using Stream.of().

```java
public List<String> fixedNames() {
  return Stream.of("Asha", "Ravi", "Sana")
      .collect(Collectors.toList());
}
```

### 4. Create infinite stream using iterate()

Generate the first n even numbers using Stream.iterate().

```java
public List<Integer> firstEvenNumbers(int count) {
  return Stream.iterate(0, number -> number + 2)
      .limit(count)
      .collect(Collectors.toList());
}
```

### 5. Create infinite stream using generate()

Generate random numbers with Stream.generate().

```java
public List<Integer> randomNumbers(int count) {
  SecureRandom random = new SecureRandom();
  return Stream.generate(() -> random.nextInt(100))
      .limit(count)
      .collect(Collectors.toList());
}
```

## Topic 4: Filtering

### 1. Find all even numbers

Return all even numbers from a list.

```java
public List<Integer> evenNumbers(List<Integer> numbers) {
  return numbers.stream()
      .filter(number -> number % 2 == 0)
      .collect(Collectors.toList());
}
```

### 2. Find all odd numbers

Return all odd numbers from a list.

```java
public List<Integer> oddNumbers(List<Integer> numbers) {
  return numbers.stream()
      .filter(number -> number % 2 != 0)
      .collect(Collectors.toList());
}
```

### 3. Find employees with salary > 50000

Return employees whose salary is greater than 50000.

```java
public List<Employee> highSalaryEmployees(List<Employee> employees) {
  return employees.stream()
      .filter(employee -> employee.getSalary() > 50_000)
      .collect(Collectors.toList());
}
```

### 4. Find active users

Return only active users.

```java
public List<User> activeUsers(List<User> users) {
  return users.stream()
      .filter(User::isActive)
      .collect(Collectors.toList());
}
```

### 5. Find products belonging to Electronics category

Return products whose category is Electronics.

```java
public List<Product> electronicsProducts(List<Product> products) {
  return products.stream()
      .filter(product -> "Electronics".equalsIgnoreCase(product.getCategory()))
      .collect(Collectors.toList());
}
```

### 6. Find strings starting with A

Return strings that start with uppercase A.

```java
public List<String> startingWithA(List<String> values) {
  return values.stream()
      .filter(value -> value.startsWith("A"))
      .collect(Collectors.toList());
}
```

### 7. Find strings ending with n

Return strings that end with lowercase n.

```java
public List<String> endingWithN(List<String> values) {
  return values.stream()
      .filter(value -> value.endsWith("n"))
      .collect(Collectors.toList());
}
```

## Topic 5: Mapping

### 1. Convert all names to uppercase

Return names converted to uppercase.

```java
public List<String> toUppercase(List<String> names) {
  return names.stream()
      .map(String::toUpperCase)
      .collect(Collectors.toList());
}
```

### 2. Convert all names to lowercase

Return names converted to lowercase.

```java
public List<String> toLowercase(List<String> names) {
  return names.stream()
      .map(String::toLowerCase)
      .collect(Collectors.toList());
}
```

### 3. Extract employee names from Employee list

Return only employee names.

```java
public List<String> employeeNames(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getName)
      .collect(Collectors.toList());
}
```

### 4. Extract employee salaries

Return employee salaries.

```java
public List<Double> employeeSalaries(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getSalary)
      .collect(Collectors.toList());
}
```

### 5. Convert List<String> to List<Integer>

Parse all numeric strings into integers.

```java
public List<Integer> parseNumbers(List<String> values) {
  return values.stream()
      .map(Integer::parseInt)
      .collect(Collectors.toList());
}
```

### 6. Get length of each string

Return the length of every string.

```java
public List<Integer> lengths(List<String> values) {
  return values.stream()
      .map(String::length)
      .collect(Collectors.toList());
}
```

## Topic 6: Distinct and Sorting

### 1. Remove duplicates

Return values without duplicates while preserving encounter order.

```java
public List<Integer> removeDuplicates(List<Integer> numbers) {
  return numbers.stream()
      .distinct()
      .collect(Collectors.toList());
}
```

### 2. Sort ascending

Sort numbers in ascending order.

```java
public List<Integer> sortAscending(List<Integer> numbers) {
  return numbers.stream()
      .sorted()
      .collect(Collectors.toList());
}
```

### 3. Sort descending

Sort numbers in descending order.

```java
public List<Integer> sortDescending(List<Integer> numbers) {
  return numbers.stream()
      .sorted(Comparator.reverseOrder())
      .collect(Collectors.toList());
}
```

### 4. Sort strings alphabetically

Sort strings in lexicographic order.

```java
public List<String> sortAlphabetically(List<String> values) {
  return values.stream()
      .sorted()
      .collect(Collectors.toList());
}
```

### 5. Sort strings by length

Sort strings by length, then alphabetically.

```java
public List<String> sortByLengthThenName(List<String> values) {
  return values.stream()
      .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
      .collect(Collectors.toList());
}
```

### 6. Sort employees by salary

Sort employees by salary in descending order.

```java
public List<Employee> sortEmployeesBySalaryDesc(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
      .collect(Collectors.toList());
}
```

### 7. Sort employees by age

Sort employees by age in ascending order.

```java
public List<Employee> sortEmployeesByAge(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparingInt(Employee::getAge))
      .collect(Collectors.toList());
}
```

## Topic 7: Collectors

### 1. Convert stream to List

Collect a stream into a List.

```java
public List<String> collectToList(Stream<String> stream) {
  return stream.collect(Collectors.toList());
}
```

### 2. Convert stream to Set

Collect a stream into a Set.

```java
public Set<String> collectToSet(Stream<String> stream) {
  return stream.collect(Collectors.toSet());
}
```

### 3. Convert stream to Map

Convert employees into a map by id.

```java
public Map<Integer, Employee> employeeById(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.toMap(
          Employee::getId,
          Function.identity(),
          (existing, replacement) -> existing));
}
```

### 4. Join strings with comma

Join names with comma separator.

```java
public String joinWithComma(List<String> names) {
  return names.stream()
      .collect(Collectors.joining(", "));
}
```

### 5. Count total elements

Count stream elements.

```java
public long countNames(List<String> names) {
  return names.stream()
      .collect(Collectors.counting());
}
```

### 6. Calculate average salary

Calculate average employee salary.

```java
public double averageSalary(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.averagingDouble(Employee::getSalary));
}
```

### 7. Calculate total salary

Calculate total employee salary.

```java
public double totalSalary(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.summingDouble(Employee::getSalary));
}
```

### 8. Find maximum salary

Find maximum employee salary.

```java
public double maximumSalary(List<Employee> employees) {
  return employees.stream()
      .mapToDouble(Employee::getSalary)
      .max()
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 9. Find minimum salary

Find minimum employee salary.

```java
public double minimumSalary(List<Employee> employees) {
  return employees.stream()
      .mapToDouble(Employee::getSalary)
      .min()
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

## Topic 8: GroupingBy

### 1. Group employees by department

Return employees grouped by department.

```java
public Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment));
}
```

### 2. Count employees per department

Return department wise employee count.

```java
public Map<String, Long> countByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
}
```

### 3. Find highest paid employee per department

Return the highest paid employee for every department.

```java
public Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.collectingAndThen(
              Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
              employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
}
```

### 4. Find average salary per department

Return average salary for each department.

```java
public Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.averagingDouble(Employee::getSalary)));
}
```

### 5. Group employees by age

Return employees grouped by age.

```java
public Map<Integer, List<Employee>> groupByAge(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getAge));
}
```

### 6. Find employees grouped by salary range

Group employees into LOW, MID, and HIGH salary ranges.

```java
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
```

### 7. Multi-level grouping: Department then Age

Group employees first by department and then by age.

```java
public Map<String, Map<Integer, List<Employee>>> groupByDepartmentThenAge(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.groupingBy(Employee::getAge)));
}
```

## Topic 9: PartitioningBy

### 1. Partition employees by salary > 50000

Partition employees into salary greater than 50000 and not greater than 50000.

```java
public Map<Boolean, List<Employee>> partitionByHighSalary(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50_000));
}
```

### 2. Partition numbers into even and odd

Partition numbers into even and odd groups.

```java
public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.partitioningBy(number -> number % 2 == 0));
}
```

### 3. Partition students into pass/fail

Partition students by marks greater than or equal to pass marks.

```java
public Map<Boolean, List<Student>> partitionPassFail(List<Student> students, int passMarks) {
  return students.stream()
      .collect(Collectors.partitioningBy(student -> student.getMarks() >= passMarks));
}
```

## Topic 10: Reduction

### 1. Sum of all numbers

Use reduce() to calculate the sum of all numbers.

```java
public int sum(List<Integer> numbers) {
  return numbers.stream()
      .reduce(0, Integer::sum);
}
```

### 2. Product of all numbers

Use reduce() to calculate product of all numbers.

```java
public int product(List<Integer> numbers) {
  return numbers.stream()
      .reduce(1, (left, right) -> left * right);
}
```

### 3. Maximum number

Use reduce() to find the maximum number.

```java
public int maximum(List<Integer> numbers) {
  return numbers.stream()
      .reduce(Integer::max)
      .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
}
```

### 4. Minimum number

Use reduce() to find the minimum number.

```java
public int minimum(List<Integer> numbers) {
  return numbers.stream()
      .reduce(Integer::min)
      .orElseThrow(() -> new NoSuchElementException("numbers is empty"));
}
```

### 5. Total salary of employees

Use reduce() to add employee salaries.

```java
public double totalSalary(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getSalary)
      .reduce(0.0, Double::sum);
}
```

### 6. Concatenate strings

Use reduce() to concatenate strings with spaces.

```java
public String concatenate(List<String> words) {
  return words.stream()
      .reduce("", (left, right) -> left.isEmpty() ? right : left + " " + right);
}
```

## Topic 11: Optional

### 1. Find first employee

Return the first employee as Optional.

```java
public Optional<Employee> firstEmployee(List<Employee> employees) {
  return employees.stream().findFirst();
}
```

### 2. Find highest salary employee

Return the employee with the highest salary.

```java
public Optional<Employee> highestSalaryEmployee(List<Employee> employees) {
  return employees.stream()
      .max(Comparator.comparingDouble(Employee::getSalary));
}
```

### 3. Handle null safely

Trim a nullable name and return Unknown when missing.

```java
public String cleanName(String name) {
  return Optional.ofNullable(name)
      .map(String::trim)
      .filter(value -> !value.isEmpty())
      .orElse("Unknown");
}
```

### 4. Use orElse()

Return default employee name when Optional is empty.

```java
public String employeeNameOrDefault(Optional<Employee> employee) {
  return employee.map(Employee::getName)
      .orElse("No employee");
}
```

### 5. Use orElseGet()

Return generated fallback text only when Optional is empty.

```java
public String employeeNameOrGeneratedDefault(Optional<Employee> employee) {
  return employee.map(Employee::getName)
      .orElseGet(() -> "Employee-" + UUID.randomUUID());
}
```

### 6. Use orElseThrow()

Return employee or throw a custom exception when absent.

```java
public Employee requiredEmployee(Optional<Employee> employee) {
  return employee.orElseThrow(() -> new NoSuchElementException("employee not found"));
}
```

## Topic 12: FlatMap

### 1. Convert List<List<Integer>> to List<Integer>

Flatten nested integer lists into one list.

```java
public List<Integer> flattenNumbers(List<List<Integer>> nestedNumbers) {
  return nestedNumbers.stream()
      .flatMap(List::stream)
      .collect(Collectors.toList());
}
```

### 2. Flatten list of employee skills

Return all skills from all employees.

```java
public List<String> allSkills(List<Employee> employees) {
  return employees.stream()
      .flatMap(employee -> employee.getSkills().stream())
      .collect(Collectors.toList());
}
```

### 3. Flatten nested lists

Flatten nested lists of strings.

```java
public List<String> flattenStrings(List<List<String>> nestedValues) {
  return nestedValues.stream()
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
}
```

### 4. Get unique skills across all employees

Return unique employee skills sorted alphabetically.

```java
public List<String> uniqueSkills(List<Employee> employees) {
  return employees.stream()
      .flatMap(employee -> employee.getSkills().stream())
      .distinct()
      .sorted()
      .collect(Collectors.toList());
}
```

## Topic 13: Stream Terminal Operations

### 1. anyMatch()

Check whether any number is even.

```java
public boolean hasEvenNumber(List<Integer> numbers) {
  return numbers.stream()
      .anyMatch(number -> number % 2 == 0);
}
```

### 2. allMatch()

Check whether all numbers are positive.

```java
public boolean allPositive(List<Integer> numbers) {
  return numbers.stream()
      .allMatch(number -> number > 0);
}
```

### 3. noneMatch()

Check whether no string is blank.

```java
public boolean hasNoBlankValues(List<String> values) {
  return values.stream()
      .noneMatch(value -> value == null || value.trim().isEmpty());
}
```

### 4. findFirst()

Return the first name starting with S.

```java
public Optional<String> firstNameStartingWithS(List<String> names) {
  return names.stream()
      .filter(name -> name.startsWith("S"))
      .findFirst();
}
```

### 5. findAny()

Return any employee from Engineering.

```java
public Optional<Employee> anyEngineeringEmployee(List<Employee> employees) {
  return employees.parallelStream()
      .filter(employee -> "Engineering".equals(employee.getDepartment()))
      .findAny();
}
```

### 6. count()

Count active employees.

```java
public long activeEmployeeCount(List<Employee> employees) {
  return employees.stream()
      .filter(Employee::isActive)
      .count();
}
```

### 7. max()

Find max number.

```java
public Optional<Integer> maxNumber(List<Integer> numbers) {
  return numbers.stream()
      .max(Integer::compareTo);
}
```

### 8. min()

Find min number.

```java
public Optional<Integer> minNumber(List<Integer> numbers) {
  return numbers.stream()
      .min(Integer::compareTo);
}
```

### 9. Is any employee earning > 1 lakh?

Check whether any employee earns more than 100000.

```java
public boolean hasEmployeeAboveOneLakh(List<Employee> employees) {
  return employees.stream()
      .anyMatch(employee -> employee.getSalary() > 100_000);
}
```

### 10. Are all employees active?

Check whether every employee is active.

```java
public boolean areAllEmployeesActive(List<Employee> employees) {
  return employees.stream()
      .allMatch(Employee::isActive);
}
```

### 11. Is any department empty?

Given all departments and employees, check whether any department has no employee.

```java
public boolean hasEmptyDepartment(List<String> departments, List<Employee> employees) {
  Set<String> departmentsWithEmployees = employees.stream()
      .map(Employee::getDepartment)
      .collect(Collectors.toSet());

  return departments.stream()
      .anyMatch(department -> !departmentsWithEmployees.contains(department));
}
```

## Topic 14: Advanced Employee Dataset Questions

### 1. Find second highest salary

Return the second highest distinct salary.

```java
public double secondHighestSalary(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getSalary)
      .distinct()
      .sorted(Comparator.reverseOrder())
      .skip(1)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("second salary not found"));
}
```

### 2. Find third highest salary

Return the third highest distinct salary.

```java
public double thirdHighestSalary(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getSalary)
      .distinct()
      .sorted(Comparator.reverseOrder())
      .skip(2)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("third salary not found"));
}
```

### 3. Find top 3 highest paid employees

Return the top 3 employees by salary.

```java
public List<Employee> topThreeHighestPaidEmployees(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
      .limit(3)
      .collect(Collectors.toList());
}
```

### 4. Find duplicate employee names

Return employee names that occur more than once.

```java
public Set<String> duplicateEmployeeNames(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
      .entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
}
```

### 5. Count employees in each department

Return employee count per department.

```java
public Map<String, Long> countEmployeesByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
}
```

### 6. Department with highest average salary

Return the department whose average salary is highest.

```java
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
```

### 7. Youngest employee

Return the employee with minimum age.

```java
public Employee youngestEmployee(List<Employee> employees) {
  return employees.stream()
      .min(Comparator.comparingInt(Employee::getAge))
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 8. Oldest employee

Return the employee with maximum age.

```java
public Employee oldestEmployee(List<Employee> employees) {
  return employees.stream()
      .max(Comparator.comparingInt(Employee::getAge))
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 9. Employee with longest name

Return employee whose name has maximum length.

```java
public Employee employeeWithLongestName(List<Employee> employees) {
  return employees.stream()
      .max(Comparator.comparingInt(employee -> employee.getName().length()))
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 10. Average salary by gender

Return average salary grouped by gender.

```java
public Map<String, Double> averageSalaryByGender(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getGender,
          Collectors.averagingDouble(Employee::getSalary)));
}
```

### 11. Highest paid employee in each department

Return the highest paid employee per department.

```java
public Map<String, Employee> highestPaidEmployeeByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.collectingAndThen(
              Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
              employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
}
```

### 12. Department having maximum employees

Return the department with the highest number of employees.

```java
public String departmentHavingMaximumEmployees(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
      .entrySet()
      .stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 13. Sort employees by department then salary

Sort employees by department ascending and salary descending.

```java
public List<Employee> sortByDepartmentThenSalaryDesc(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparing(Employee::getDepartment)
          .thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()))
      .collect(Collectors.toList());
}
```

### 14. Find employees whose names start with S

Return employees with names starting with S.

```java
public List<Employee> employeesWhoseNamesStartWithS(List<Employee> employees) {
  return employees.stream()
      .filter(employee -> employee.getName().startsWith("S"))
      .collect(Collectors.toList());
}
```

### 15. Find salary statistics

Return count, sum, min, average, and max salary.

```java
public DoubleSummaryStatistics salaryStatistics(List<Employee> employees) {
  return employees.stream()
      .mapToDouble(Employee::getSalary)
      .summaryStatistics();
}
```

## Topic 15: String Stream Problems

### 1. Count vowels

Count vowels in a string.

```java
public long countVowels(String text) {
  return text.chars()
      .filter(ch -> "aeiouAEIOU".indexOf(ch) >= 0)
      .count();
}
```

### 2. Count characters frequency

Return frequency of every character preserving first-seen order.

```java
public Map<Character, Long> characterFrequency(String text) {
  return text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(
          Function.identity(),
          LinkedHashMap::new,
          Collectors.counting()));
}
```

### 3. Find duplicate characters

Return characters appearing more than once.

```java
public Set<Character> duplicateCharacters(String text) {
  return characterFrequency(text).entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey)
      .collect(Collectors.toCollection(LinkedHashSet::new));
}
```

### 4. Find first non-repeating character

Return the first character that appears once.

```java
public Optional<Character> firstNonRepeatingCharacter(String text) {
  Map<Character, Long> frequency = text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

  return frequency.entrySet()
      .stream()
      .filter(entry -> entry.getValue() == 1)
      .map(Map.Entry::getKey)
      .findFirst();
}
```

### 5. Find first repeating character

Return the first character whose total frequency is greater than one.

```java
public Optional<Character> firstRepeatingCharacter(String text) {
  Map<Character, Long> frequency = text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

  return frequency.entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey)
      .findFirst();
}
```

### 6. Reverse each word

Reverse every word while keeping word order.

```java
public String reverseEachWord(String sentence) {
  return Arrays.stream(sentence.split("\\s+"))
      .map(word -> new StringBuilder(word).reverse().toString())
      .collect(Collectors.joining(" "));
}
```

### 7. Reverse sentence using streams

Reverse word order in a sentence using streams.

```java
public String reverseSentence(String sentence) {
  List<String> words = Arrays.asList(sentence.trim().split("\\s+"));
  return IntStream.range(0, words.size())
      .mapToObj(index -> words.get(words.size() - 1 - index))
      .collect(Collectors.joining(" "));
}
```

### 8. Sort characters alphabetically

Return characters sorted alphabetically.

```java
public String sortCharacters(String text) {
  return text.chars()
      .sorted()
      .mapToObj(ch -> String.valueOf((char) ch))
      .collect(Collectors.joining());
}
```

## Topic 16: Number Stream Problems

### 1. Find duplicates

Return duplicate numbers from a list.

```java
public Set<Integer> duplicateNumbers(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
}
```

### 2. Find missing number

Given numbers from 1 to n with one missing, return the missing number.

```java
public int missingNumber(int[] numbers, int n) {
  int expected = n * (n + 1) / 2;
  int actual = Arrays.stream(numbers).sum();
  return expected - actual;
}
```

### 3. Find second largest

Return the second largest distinct number.

```java
public int secondLargest(List<Integer> numbers) {
  return numbers.stream()
      .distinct()
      .sorted(Comparator.reverseOrder())
      .skip(1)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("second largest not found"));
}
```

### 4. Find second smallest

Return the second smallest distinct number.

```java
public int secondSmallest(List<Integer> numbers) {
  return numbers.stream()
      .distinct()
      .sorted()
      .skip(1)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("second smallest not found"));
}
```

### 5. Find frequency of numbers

Return frequency of every number.

```java
public Map<Integer, Long> frequency(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
}
```

### 6. Separate even and odd

Partition numbers into even and odd groups.

```java
public Map<Boolean, List<Integer>> separateEvenOdd(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.partitioningBy(number -> number % 2 == 0));
}
```

### 7. Find common elements in two arrays

Return unique numbers present in both arrays.

```java
public List<Integer> commonElements(int[] first, int[] second) {
  Set<Integer> secondValues = Arrays.stream(second)
      .boxed()
      .collect(Collectors.toSet());

  return Arrays.stream(first)
      .boxed()
      .filter(secondValues::contains)
      .distinct()
      .collect(Collectors.toList());
}
```

### 8. Find intersection

Return intersection preserving duplicate counts.

```java
public List<Integer> intersectionWithCounts(int[] first, int[] second) {
  Map<Integer, Long> counts = Arrays.stream(second)
      .boxed()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

  return Arrays.stream(first)
      .boxed()
      .filter(value -> {
        long count = counts.getOrDefault(value, 0L);
        if (count == 0) {
          return false;
        }
        counts.put(value, count - 1);
        return true;
      })
      .collect(Collectors.toList());
}
```

### 9. Find union

Return union of two arrays without duplicates.

```java
public List<Integer> union(int[] first, int[] second) {
  return Stream.concat(Arrays.stream(first).boxed(), Arrays.stream(second).boxed())
      .distinct()
      .collect(Collectors.toList());
}
```

### 10. Find top K largest numbers

Return top k largest distinct numbers.

```java
public List<Integer> topKLargest(List<Integer> numbers, int k) {
  return numbers.stream()
      .distinct()
      .sorted(Comparator.reverseOrder())
      .limit(k)
      .collect(Collectors.toList());
}
```

## Topic 17: Parallel Stream

### 1. Difference between Stream and ParallelStream

Show sequential and parallel sum methods for the same input.

```java
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
```

### 2. Calculate sum using parallel stream

Calculate sum using parallel stream.

```java
public long sumUsingParallelStream(List<Integer> numbers) {
  return numbers.parallelStream()
      .mapToLong(Integer::longValue)
      .sum();
}
```

### 3. Find performance difference

Measure sequential and parallel sum duration.

```java
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
```

### 4. Thread safety issues in parallel stream

Count words safely in a parallel stream.

```java
public Map<String, Long> safeWordFrequency(List<String> words) {
  return words.parallelStream()
      .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));
}
```

## Topic 18: Top 25 Interview Questions

### 1. Second highest salary employee

Find an employee whose salary is the second highest distinct salary.

```java
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
```

### 2. Highest salary per department

Find highest salary employee for every department.

```java
public Map<String, Employee> highestSalaryPerDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(
          Employee::getDepartment,
          Collectors.collectingAndThen(
              Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
              employee -> employee.orElseThrow(() -> new NoSuchElementException("empty department")))));
}
```

### 3. Group employees by department

Group employees by department.

```java
public Map<String, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment));
}
```

### 4. Count employees by department

Count employees per department.

```java
public Map<String, Long> countEmployeesByDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
}
```

### 5. Duplicate elements in list

Return duplicate values from a list.

```java
public Set<Integer> duplicateElements(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet()
      .stream()
      .filter(entry -> entry.getValue() > 1)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
}
```

### 6. First non-repeating character

Return first non-repeating character from a string.

```java
public Optional<Character> firstNonRepeatingCharacter(String text) {
  Map<Character, Long> frequency = text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

  return frequency.entrySet().stream()
      .filter(entry -> entry.getValue() == 1)
      .map(Map.Entry::getKey)
      .findFirst();
}
```

### 7. Frequency of characters

Return character frequency map.

```java
public Map<Character, Long> frequencyOfCharacters(String text) {
  return text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
}
```

### 8. Frequency of words

Return word frequency map.

```java
public Map<String, Long> frequencyOfWords(String sentence) {
  return Arrays.stream(sentence.trim().split("\\s+"))
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
}
```

### 9. Partition even/odd

Partition numbers into even and odd.

```java
public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
  return numbers.stream()
      .collect(Collectors.partitioningBy(number -> number % 2 == 0));
}
```

### 10. Top 3 salaries

Return top three distinct salaries.

```java
public List<Double> topThreeSalaries(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getSalary)
      .distinct()
      .sorted(Comparator.reverseOrder())
      .limit(3)
      .collect(Collectors.toList());
}
```

### 11. Flatten nested list

Flatten nested integer lists.

```java
public List<Integer> flattenNestedList(List<List<Integer>> nestedNumbers) {
  return nestedNumbers.stream()
      .flatMap(List::stream)
      .collect(Collectors.toList());
}
```

### 12. Remove duplicates

Remove duplicates from a list.

```java
public List<Integer> removeDuplicates(List<Integer> numbers) {
  return numbers.stream()
      .distinct()
      .collect(Collectors.toList());
}
```

### 13. Sort employee by salary

Sort employees by salary descending.

```java
public List<Employee> sortEmployeeBySalary(List<Employee> employees) {
  return employees.stream()
      .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
      .collect(Collectors.toList());
}
```

### 14. Average salary per department

Find average salary by department.

```java
public Map<String, Double> averageSalaryPerDepartment(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
}
```

### 15. Department with highest average salary

Find department with maximum average salary.

```java
public String departmentWithHighestAverageSalary(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
      .entrySet()
      .stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 16. Find duplicate names

Find repeated employee names.

```java
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
```

### 17. Join all names with comma

Join employee names with comma.

```java
public String joinAllNamesWithComma(List<Employee> employees) {
  return employees.stream()
      .map(Employee::getName)
      .collect(Collectors.joining(", "));
}
```

### 18. Convert list to map

Convert employees into map keyed by id.

```java
public Map<Integer, Employee> listToMap(List<Employee> employees) {
  return employees.stream()
      .collect(Collectors.toMap(Employee::getId, Function.identity(), (first, second) -> first));
}
```

### 19. Salary statistics

Return salary summary statistics.

```java
public DoubleSummaryStatistics salaryStatistics(List<Employee> employees) {
  return employees.stream()
      .mapToDouble(Employee::getSalary)
      .summaryStatistics();
}
```

### 20. Find youngest employee

Return employee with minimum age.

```java
public Employee youngestEmployee(List<Employee> employees) {
  return employees.stream()
      .min(Comparator.comparingInt(Employee::getAge))
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 21. Find oldest employee

Return employee with maximum age.

```java
public Employee oldestEmployee(List<Employee> employees) {
  return employees.stream()
      .max(Comparator.comparingInt(Employee::getAge))
      .orElseThrow(() -> new NoSuchElementException("employees is empty"));
}
```

### 22. Find common elements in arrays

Return unique common values from two arrays.

```java
public List<Integer> commonElements(int[] first, int[] second) {
  Set<Integer> secondSet = Arrays.stream(second).boxed().collect(Collectors.toSet());
  return Arrays.stream(first)
      .boxed()
      .filter(secondSet::contains)
      .distinct()
      .collect(Collectors.toList());
}
```

### 23. Second largest number

Return second largest distinct number.

```java
public int secondLargestNumber(List<Integer> numbers) {
  return numbers.stream()
      .distinct()
      .sorted(Comparator.reverseOrder())
      .skip(1)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("second largest not found"));
}
```

### 24. Missing number

Find missing number from 1 to n.

```java
public int missingNumber(int[] numbers, int n) {
  return n * (n + 1) / 2 - Arrays.stream(numbers).sum();
}
```

### 25. String character count

Count every character in a string.

```java
public Map<Character, Long> stringCharacterCount(String text) {
  return text.chars()
      .mapToObj(ch -> (char) ch)
      .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
}
```
