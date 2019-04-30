package com.learn.chapter27;

import com.learn.chapter19.Future;

public class App {

  public static void main(String[] args) throws InterruptedException {
    //OrderService orderService = OrderServiceFactory.toActivrObject(new OrderServiceImpl());
    OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
    Future<String> future = orderService.findOrderDetails(1341241);
    orderService.order("hello", 43451);
    System.out.println("Return immediately");
    System.out.println(future.get());
    Thread.currentThread().join();
  }

}
