package com.learn.chapter27;

import java.util.LinkedList;

public class ActiveMessageQueue {

  private final LinkedList<MethodMessage> messages = new LinkedList<>();

  public ActiveMessageQueue() {
    new ActiveDeamonThread(this).start();
  }

  public void offer(MethodMessage methodMessage) {
    synchronized (this) {
      messages.addLast(methodMessage);
      this.notify();
    }
  }

  public MethodMessage take() {
    synchronized (this) {
      while (messages.isEmpty()) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return messages.removeFirst();
    }
  }

}
