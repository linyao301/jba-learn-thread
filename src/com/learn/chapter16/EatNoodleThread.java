package com.learn.chapter16;

import java.util.concurrent.TimeUnit;

public class EatNoodleThread extends Thread {

  private final String name;

  private final Tableware left;

  private final Tableware right;

  public EatNoodleThread(String name, Tableware left, Tableware right) {
    this.name = name;
    this.left = left;
    this.right = right;
  }

  @Override
  public void run() {
    while (true) {
      this.eat();
    }
  }

  private void eat() {
    synchronized (left) {
      System.out.println(name + "拿起了" + left.getToolName());
      synchronized (right) {
        System.out.println(name + "拿起了" + right.getToolName());
        try {
          TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(name + "放下了" + right.getToolName());
      }
      System.out.println(name + "放下了" + left.getToolName());
    }
  }
}
