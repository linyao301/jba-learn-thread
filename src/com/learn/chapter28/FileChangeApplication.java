package com.learn.chapter28;

public class FileChangeApplication {

  public static void main(String[] args) throws Exception {
    EventBus eventBus = new EventBus();
    FileChangeListener fileChangeListener = new FileChangeListener();
    eventBus.register(fileChangeListener);
    DirectoryTargetMonitor directoryTargetMonitor = new DirectoryTargetMonitor(eventBus, "E:\\");
    directoryTargetMonitor.startMonitor();
  }

}
