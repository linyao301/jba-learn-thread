package com.learn.chapter19;

public class FutureServiceImpl<IN, OUT> implements FutureServce<IN, OUT> {

  @Override
  public Future<?> submit(Runnable runnable) {
    FutureTask<OUT> future = new FutureTask<>();
    new Thread(() -> {
      runnable.run();
      future.finish(null);
    }).start();
    return future;
  }

  @Override
  public Future<OUT> submit(Task<IN, OUT> task, IN input) {
    FutureTask<OUT> future = new FutureTask<>();
    new Thread(() -> {
      OUT result = task.get(input);
      future.finish(result);
    }).start();
    return future;
  }
}
