package com.learn.chapter5;

import java.util.LinkedList;

public class EventQueue {

  private LinkedList<Event> eventQueue = new LinkedList<Event>();

  private int MAX_DEFAULT_SIZE = 1;

  private int max;

  public EventQueue() {
    this.max = MAX_DEFAULT_SIZE;
  }

  public EventQueue(int max) {
    this.max = max;
  }

  public void offer(Event event) {
    synchronized (eventQueue) {
      while (eventQueue.size() >= max) {
        try {
          System.out.println("offer阻塞:" + eventQueue.size());
          eventQueue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("the new event is submitted");
      eventQueue.addLast(event);
      eventQueue.notify();
    }
  }

  public Event take() {
    Event event = null;
    synchronized (eventQueue) {
      while (eventQueue.isEmpty()) {
        try {
          eventQueue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      event = eventQueue.removeFirst();
      System.out.println("the first event" + event + " is handled");
      eventQueue.notify();
      System.out.println("唤醒了阻塞");
    }
    return event;
  }
}