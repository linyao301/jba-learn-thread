package com.learn.chapter28;

public class SimpleSubscribe1 {

  @Subscribe
  public void method1(String message) {
    System.out.println("SimpleSubscribe1-Method1:" + message);
  }

  @Subscribe(topic = "test")
  public void method2(String message) {
    System.out.println("SimpleSubscribe1-Method2:" + message);
  }

  public static void main(String[] args) {
    Bus bus = new EventBus("TestBus", new EventExceptionHandler() {
      @Override
      public void handle(Throwable cause, EventContext context) {
        cause.printStackTrace();
      }
    });
    bus.register(new SimpleSubscribe1());
    bus.post("hello");
    System.out.println("---------------");
    bus.post("hello", "test1");
  }

}
