package com.learn.chapter19;

public interface FutureServce<IN, OUT> {

  Future<?> submit(Runnable runnable);

  Future<OUT> submit(Task<IN, OUT> task, IN input);

  static <T, R> FutureServce<T, R> newServce() {
    return new FutureServiceImpl<>();
  }

}
