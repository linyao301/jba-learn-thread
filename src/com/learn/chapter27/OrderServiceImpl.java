package com.learn.chapter27;

import com.learn.chapter19.Future;
import com.learn.chapter19.FutureServce;
import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

  @Override
  public Future<String> findOrderDetails(long orderId) {
    Future<String> future = FutureServce.<Long, String>newServce().submit(input -> {
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "The Order Details Information";
    }, orderId);
    return future;
  }

  @ActiveMethod
  @Override
  public void order(String account, long orderId) {
    try {
      TimeUnit.SECONDS.sleep(10);
      System.out.println("process the order for account " + account + ",orderId " + orderId);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
