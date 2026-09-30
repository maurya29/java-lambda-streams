# Java Lambda and Streams Interview Practice

Java 8 compatible Maven project containing all 18 modules and 149 solutions from the supplied workbook.

Each module is one class; numbered comments preserve the question, explanation, trigger point, and complexity. Solution methods are public instance methods. Shared models are in `com.interview.streams.model`.

## Build and run

Requires a JDK and Maven. From this directory:

```sh
mvn clean package
java -jar target/java-lambda-streams-1.0.0.jar
```

Open `pom.xml` in IntelliJ IDEA, Eclipse, or VS Code to import the project.

## Modules

| # | Module class | Questions |
|---|---|---|
| 1 | [LambdaBasics](src/main/java/com/interview/streams/modules/LambdaBasics.java) | 8 |
| 2 | [FunctionalInterfaces](src/main/java/com/interview/streams/modules/FunctionalInterfaces.java) | 8 |
| 3 | [StreamCreation](src/main/java/com/interview/streams/modules/StreamCreation.java) | 5 |
| 4 | [Filtering](src/main/java/com/interview/streams/modules/Filtering.java) | 7 |
| 5 | [Mapping](src/main/java/com/interview/streams/modules/Mapping.java) | 6 |
| 6 | [DistinctAndSorting](src/main/java/com/interview/streams/modules/DistinctAndSorting.java) | 7 |
| 7 | [CollectorsModule](src/main/java/com/interview/streams/modules/CollectorsModule.java) | 9 |
| 8 | [GroupingBy](src/main/java/com/interview/streams/modules/GroupingBy.java) | 7 |
| 9 | [PartitioningBy](src/main/java/com/interview/streams/modules/PartitioningBy.java) | 3 |
| 10 | [Reduction](src/main/java/com/interview/streams/modules/Reduction.java) | 6 |
| 11 | [OptionalModule](src/main/java/com/interview/streams/modules/OptionalModule.java) | 6 |
| 12 | [FlatMap](src/main/java/com/interview/streams/modules/FlatMap.java) | 4 |
| 13 | [StreamTerminalOperations](src/main/java/com/interview/streams/modules/StreamTerminalOperations.java) | 11 |
| 14 | [AdvancedEmployeeDatasetQuestions](src/main/java/com/interview/streams/modules/AdvancedEmployeeDatasetQuestions.java) | 15 |
| 15 | [StringStreamProblems](src/main/java/com/interview/streams/modules/StringStreamProblems.java) | 8 |
| 16 | [NumberStreamProblems](src/main/java/com/interview/streams/modules/NumberStreamProblems.java) | 10 |
| 17 | [ParallelStream](src/main/java/com/interview/streams/modules/ParallelStream.java) | 4 |
| 18 | [Top25InterviewQuestions](src/main/java/com/interview/streams/modules/Top25InterviewQuestions.java) | 25 |

## Usage

```java
LambdaBasics module = new LambdaBasics();
List<Integer> sorted = module.sortIntegers(Arrays.asList(3, 1, 2));
```

Import module classes from `com.interview.streams.modules` and models from `com.interview.streams.model`. `CollectorsModule` and `OptionalModule` avoid collisions with JDK types.

Solutions retain the workbook conventions: inputs are generally non-null; ranking methods require enough distinct values; missing-number inputs contain distinct values from 1 through n with exactly one missing. Character exercises operate on UTF-16 chars. The parallel timing example is illustrative, not a benchmark. Integer arithmetic has normal Java overflow behavior.
