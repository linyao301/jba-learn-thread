package com.learn.chapter16;

public class Tableware {

  private final String toolName;

  public Tableware(String toolName) {
    this.toolName = toolName;
  }

  public String getToolName() {
    return toolName;
  }

  @Override
  public String toString() {
    return "Tableware{" +
        "toolName='" + toolName + '\'' +
        '}';
  }
}
