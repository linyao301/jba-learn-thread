package com.learn.chapter8;

import com.learn.chapter8.DenyPolicy.AbortDenyPolicy;
import com.learn.chapter8.DenyPolicy.DiscardDenyPolicy;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool {

  private final int initSize;

  private final int maxSize;

  private final int coreSize;

  private int acticeCount;

  private final ThreadFactory threadFactory;

  private final RunnableQueue runnableQueue;

  private volatile boolean isShutdown = false;

  private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

  private final static DenyPolicy DEFAULT_DENY_POLICY = new AbortDenyPolicy();

  private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

  private final long keepAliveTime;

  private final TimeUnit timeUnit;

  public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
    this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10,
        TimeUnit.SECONDS);
  }

  public BasicThreadPool(int initSize, int maxSize,
      int coreSize, ThreadFactory threadFactory, int queueSize, DenyPolicy denyPolicy,
      long keepAliveTime, TimeUnit timeUnit) {
    this.initSize = initSize;
    this.maxSize = maxSize;
    this.coreSize = coreSize;
    this.threadFactory = threadFactory;
    this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
    this.keepAliveTime = keepAliveTime;
    this.timeUnit = timeUnit;
    this.init();
  }

  private void init() {
    start();
    for (int i = 0; i < initSize; i++) {
      newThread();
    }
  }

  @Override
  public void run() {
    while (!isShutdown && !isInterrupted()) {
      try {
        timeUnit.sleep(keepAliveTime);
      } catch (InterruptedException e) {
        isShutdown = true;
        break;
      }
      synchronized (this) {
        if (isShutdown) {
          break;
        }
        if (runnableQueue.size() > 0 && acticeCount < coreSize) {
          for (int i = initSize; i < coreSize; i++) {
            newThread();
          }
          continue;
        }
        if (runnableQueue.size() > 0 && acticeCount < maxSize) {
          for (int i = coreSize; i < maxSize; i++) {
            newThread();
          }
        }
        if (runnableQueue.size() == 0 && acticeCount > coreSize) {
          for (int i = coreSize; i < acticeCount; i++) {
            removeThread();
          }
        }
      }
    }
  }

  private void newThread() {
    InternalTask internalTask = new InternalTask(runnableQueue);
    Thread thread = this.threadFactory.createThread(internalTask);
    ThreadTask threadTask = new ThreadTask(internalTask, thread);
    threadQueue.offer(threadTask);
    this.acticeCount++;
    thread.start();
  }

  private void removeThread() {
    ThreadTask threadTask = threadQueue.remove();
    threadTask.internalTask.stop();
    threadTask.thread.interrupt();
    this.acticeCount--;
  }

  @Override
  public void execute(Runnable runnable) {
    if (this.isShutdown) {
      throw new IllegalStateException("线程池已经销毁");
    }
    runnableQueue.offer(runnable);
  }

  @Override
  public void shutdown() {
    synchronized (this) {
      if (isShutdown) {
        return;
      }
      isShutdown = true;
      threadQueue.forEach(x -> {
        x.internalTask.stop();
        x.thread.interrupt();
      });
      this.interrupt();
    }
  }

  @Override
  public int getInitSize() {
    if (isShutdown) {
      throw new IllegalStateException("线程池已经停止");
    }
    return this.initSize;
  }

  @Override
  public int getMaxSize() {
    if (isShutdown) {
      throw new IllegalStateException("线程池已经停止");
    }
    return this.maxSize;
  }

  @Override
  public int getCoreSize() {
    if (isShutdown) {
      throw new IllegalStateException("线程池已经停止");
    }
    return this.coreSize;
  }

  @Override
  public int getQueueSize() {
    if (isShutdown) {
      throw new IllegalStateException("线程池已经停止");
    }
    return runnableQueue.size();
  }

  @Override
  public int getActiveCount() {
    if (isShutdown) {
      throw new IllegalStateException("线程池已经停止");
    }
    synchronized (this) {
      return this.acticeCount;
    }
  }

  @Override
  public boolean isShutDown() {
    return this.isShutdown;
  }

  class ThreadTask {

    private InternalTask internalTask;
    private Thread thread;

    public ThreadTask(InternalTask internalTask, Thread thread) {
      this.internalTask = internalTask;
      this.thread = thread;
    }

    public InternalTask getInternalTask() {
      return internalTask;
    }

    public void setInternalTask(InternalTask internalTask) {
      this.internalTask = internalTask;
    }

    public Thread getThread() {
      return thread;
    }

    public void setThread(Thread thread) {
      this.thread = thread;
    }
  }

  static class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
    private static final ThreadGroup group = new ThreadGroup(
        "MyThreadPool-" + GROUP_COUNTER.getAndIncrement());
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
      return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndIncrement());
    }
  }

}
