package com.learn.chapter27;

public class ActiveDeamonThread extends Thread {

  private final ActiveMessageQueue queue;

  public ActiveDeamonThread(ActiveMessageQueue queue) {
    super("ActiveDeamonThread");
    this.queue = queue;
    setDaemon(true);
  }

  @Override
  public void run() {
    for (; ; ) {
      MethodMessage methodMessage = this.queue.take();
      methodMessage.execute();
    }
  }
}
