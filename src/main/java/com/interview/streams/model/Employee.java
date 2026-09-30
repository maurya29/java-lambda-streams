package com.interview.streams.model;

import java.util.*;

public class Employee {
  private final int id;
  private final String name;
  private final String department;
  private final double salary;
  private final int age;
  private final String gender;
  private final boolean active;
  private final List<String> skills;

  public Employee(int id, String name, String department, double salary, int age, String gender, boolean active, List<String> skills) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.salary = salary;
    this.age = age;
    this.gender = gender;
    this.active = active;
    this.skills = new ArrayList<>(skills);
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public String getDepartment() { return department; }
  public double getSalary() { return salary; }
  public int getAge() { return age; }
  public String getGender() { return gender; }
  public boolean isActive() { return active; }
  public List<String> getSkills() { return Collections.unmodifiableList(skills); }
}
