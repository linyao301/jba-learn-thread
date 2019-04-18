package com.learn.chapter15;

import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) {
    ObservableThread observableThread = new ObservableThread(new Task() {
      @Override
      public Object call() {
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "hello world";
      }
    });
    observableThread.setName("test");
    observableThread.start();
  }

}
