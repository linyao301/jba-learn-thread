package com.learn.chapter22;

import java.util.concurrent.TimeUnit;

public class AutoSavedThread extends Thread {

  private Document document;

  public AutoSavedThread(Document document) {
    super("DocumentAutoSaveThread");
    this.document = document;
  }

  @Override
  public void run() {
    while (true) {
      try {
        document.save();
        TimeUnit.SECONDS.sleep(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
