package com.interview.streams.model;

import java.util.*;

public class Student {
  private final String name;
  private final int marks;

  public Student(String name, int marks) {
    this.name = name;
    this.marks = marks;
  }

  public String getName() { return name; }
  public int getMarks() { return marks; }
}
