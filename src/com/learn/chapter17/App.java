package com.learn.chapter17;

public class App {

  private static String s = "wqerqwerwqasdasfrwreqewrwqrzsds";

  public static void main(String[] args) {
    final SharedData sharedData = new SharedData(50);
    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        for (int j = 0; j < s.length(); j++) {
          try {
            char c = s.charAt(j);
            sharedData.write(c);
            System.out.println(Thread.currentThread() + " write " + c);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          try {
            System.out.println(Thread.currentThread() + " read " + new String(sharedData.read()));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }

}
