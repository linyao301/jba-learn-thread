package com.learn.chapter15;

public class ObservableThread<T> extends Thread implements Observable {

  private TaskLifecycle taskLifecycle;

  private Task<T> task;

  private Cycle cycle;

  public ObservableThread(Task task) {
    this.taskLifecycle = new EmptyTaskLifecycle();
    this.task = task;
  }

  public ObservableThread(TaskLifecycle taskLifecycle, Task task) {
    this.taskLifecycle = taskLifecycle;
    this.task = task;
  }

  @Override
  public Cycle getCycle() {
    return this.cycle;
  }

  @Override
  public void start() {
    this.run();
  }


  @Override
  public final void run() {
    Thread.currentThread();
    this.update(Cycle.STARTED, null, null);
    try {
      this.update(Cycle.RUNNING, null, null);
      T result = this.task.call();
      this.update(Cycle.DONE, result, null);
    } catch (Exception e) {
      this.update(Cycle.ERROR, null, e);
    }
  }

  private void update(Cycle cycle, T result, Exception e) {
    this.cycle = cycle;
    if (taskLifecycle == null) {
      return;
    }
    try {
      switch (cycle) {
        case STARTED:
          this.taskLifecycle.onStart(this);
          break;
        case RUNNING:
          this.taskLifecycle.onRunning(this);
          break;
        case DONE:
          this.taskLifecycle.onFinish(this, result);
          break;
        case ERROR:
          taskLifecycle.onError(this, e);
          break;
      }
    } catch (Exception ex) {
      if (cycle.equals(Cycle.ERROR)) {
        throw ex;
      }
    }
  }

  @Override
  public void interrupt() {

  }
}
