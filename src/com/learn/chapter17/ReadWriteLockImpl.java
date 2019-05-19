package com.learn.chapter17;

class ReadWriteLockImpl implements ReadWriteLock {

  private final Object MUTEX = new Object();

  private int readingReaders = 0;

  private int writingWriters = 0;

  private int waitingWriters = 0;

  //默认情况下的偏好设置
  private boolean perferWriter;


  public ReadWriteLockImpl() {
    this(true);
  }

  public ReadWriteLockImpl(boolean perferWriter) {
    this.perferWriter = perferWriter;
  }

  @Override
  public Lock readLock() {
    return new ReadLock(this);
  }

  @Override
  public Lock writeLock() {
    return new WriteLock(this);
  }

  @Override
  public int getReadingReaders() {
    return this.readingReaders;
  }

  @Override
  public int getWritingWriters() {
    return this.writingWriters;
  }

  @Override
  public int getWaitingWriters() {
    return this.waitingWriters;
  }

  void incrementReadingReader() {
    this.readingReaders++;
  }

  void incrementWritingWriter() {
    this.writingWriters++;
  }

  void incrementWaitingWriter() {
    this.waitingWriters++;
  }

  void decrementReadingReader() {
    this.readingReaders--;
  }

  void decrementWritingWriter() {
    this.writingWriters--;
  }

  void decrementWaitingWriter() {
    this.waitingWriters--;
  }

  Object getMUTEX() {
    return this.MUTEX;
  }

  boolean isPerferWriter() {
    return perferWriter;
  }

  void setPerferWriter(boolean perferWriter) {
    this.perferWriter = perferWriter;
  }
}
