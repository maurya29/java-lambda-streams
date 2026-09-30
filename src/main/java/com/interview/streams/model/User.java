package com.interview.streams.model;

import java.util.*;

public class User {
  private final String name;
  private final boolean active;

  public User(String name, boolean active) {
    this.name = name;
    this.active = active;
  }

  public String getName() { return name; }
  public boolean isActive() { return active; }
}
