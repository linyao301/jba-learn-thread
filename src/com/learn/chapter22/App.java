package com.learn.chapter22;

public class App {

  public static void main(String[] args) {
    new DocumentEditThread("E:\\my-workspace\\thread-learn\\src\\com\\learn\\chapter22", "balking.txt").start();
  }

}
