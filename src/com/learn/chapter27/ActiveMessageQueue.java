package com.learn.chapter27;

import java.util.LinkedList;

public class ActiveMessageQueue {

  private final LinkedList<MethodMessage> messages = new LinkedList<>();

  private final LinkedList<ActiveMessage> activeMessages = new LinkedList<>();

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

  public void offerActive(ActiveMessage activeMessage) {
    synchronized (this) {
      activeMessages.addLast(activeMessage);
      this.notify();
    }
  }

  public ActiveMessage takeActive() {
    synchronized (this) {
      while (activeMessages.isEmpty()) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return activeMessages.removeFirst();
    }
  }

}
