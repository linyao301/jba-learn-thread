package com.learn.chapter8;

@FunctionalInterface
public interface DenyPolicy {

  void reject(Runnable runnable, ThreadPool threadPool);

  class DiscardDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {
      //将任务丢弃
    }
  }

  class AbortDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {
      throw new RunnableDenyException("拒绝策略");
    }
  }

  class RunnerDenyPolicy implements DenyPolicy {

    @Override
    public void reject(Runnable runnable, ThreadPool threadPool) {
      if (!threadPool.isShutDown()) {
        runnable.run();
      }
    }
  }

}
