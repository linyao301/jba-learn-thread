package com.learn.chapter16;

public class App {

  public static void main(String[] args) {
    Tableware left = new Tableware("叉子");
    Tableware right = new Tableware("刀子");
    EatNoodleThread a = new EatNoodleThread("张三", left, right);
    EatNoodleThread b = new EatNoodleThread("王五", left, right);
    a.start();
    b.start();
  }

}
