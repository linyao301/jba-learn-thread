package com.learn.chapter28;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryTargetMonitor {

  private WatchService watchService;

  private final EventBus eventBus;

  private final Path path;

  private volatile boolean start = false;

  public DirectoryTargetMonitor(EventBus eventBus, String targetPath) {
    this(eventBus, targetPath, "");
  }

  public DirectoryTargetMonitor(EventBus eventBus, String targetPath, final String... morePaths) {
    this.eventBus = eventBus;
    this.path = Paths.get(targetPath, morePaths);
  }

  public void startMonitor() throws Exception {
    this.watchService = FileSystems.getDefault().newWatchService();
    this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
        StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
    System.out.printf("正在监控[%s]目录中....\n", path);
    this.start = true;
    while (start) {
      WatchKey watchKey = null;
      try {
        watchKey = watchService.take();
        watchKey.pollEvents().forEach(event -> {
          WatchEvent.Kind<?> kind = event.kind();
          Path path = (Path) event.context();
          Path child = DirectoryTargetMonitor.this.path.resolve(path);
          eventBus.post(new FileChangeEvent(child, kind));
        });
      } catch (Exception e) {
        this.start = false;
      } finally {
        if (watchKey != null) {
          watchKey.reset();
        }
      }
    }
  }

  public void stopMonitor() throws Exception {
    System.out.printf("目录[%s]监控正在终止....", path);
    Thread.currentThread().interrupt();
    this.start = false;
    this.watchService.close();
    System.out.printf("目录[%s]监控已经终止....", path);
  }

}
