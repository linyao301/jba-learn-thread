package com.learn.chapter5;

import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) {
    EventQueue eventQueue = new EventQueue();
    Thread product1 = new Thread(() -> {
      while (true){
        Event event = new Event();
        eventQueue.offer(event);
      }
    }, "product");
    Thread product2 = new Thread(() -> {
      while (true){
        Event event = new Event();
        eventQueue.offer(event);
      }
    }, "product");
    Thread consumer = new Thread(() -> {
      while (true){
        eventQueue.take();
        try {
          TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "consumer");
    product1.start();
    product2.start();
    consumer.start();
  }

}
