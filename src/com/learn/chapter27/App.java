package com.learn.chapter27;

public class App {

  public static void main(String[] args) throws InterruptedException {
    OrderService orderService = OrderServiceFactory.toActivrObject(new OrderServiceImpl());
    orderService.order("hello", 43451);
    System.out.println("Return immediately");
    Thread.currentThread().join();
  }

}
