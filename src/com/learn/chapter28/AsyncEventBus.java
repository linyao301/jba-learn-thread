package com.learn.chapter28;

import java.util.concurrent.ThreadPoolExecutor;

public class AsyncEventBus extends EventBus {

  public AsyncEventBus(ThreadPoolExecutor executor) {
    super("default-async", null, executor);
  }

  public AsyncEventBus(String busName, EventExceptionHandler exceptionHandler,
      ThreadPoolExecutor executor) {
    super(busName, exceptionHandler, executor);
  }

  public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
    super(busName, null, executor);
  }

  public AsyncEventBus(EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
    super("default-async", exceptionHandler, executor);
  }
}
