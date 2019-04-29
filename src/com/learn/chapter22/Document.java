package com.learn.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {

  private boolean changed = false;
  private List<String> content = new ArrayList<>();
  private final FileWriter fileWriter;
  private static AutoSavedThread autoSavedThread;

  private Document(String documentPath, String documentName) throws IOException {
    this.fileWriter = new FileWriter(new File(documentPath, documentName));
  }

  public static Document create(String documentPath, String documentName) {
    Document document = null;
    try {
      document = new Document(documentPath, documentName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    autoSavedThread = new AutoSavedThread(document);
    autoSavedThread.start();
    return document;
  }

  public void edit(String content) {
    synchronized (this) {
      this.content.add(content);
      this.changed = true;
    }
  }

  public void save() throws IOException {
    synchronized (this) {
      if (!changed) {
        return;
      }
      System.out.println(Thread.currentThread() + " execute the save action");
      for (String cacheLine : content) {
        this.fileWriter.write(cacheLine);
        this.fileWriter.write("\r\n");
      }
      this.fileWriter.flush();
      this.changed = false;
      this.content.clear();
    }
  }

  public void close() {
    autoSavedThread.interrupt();
    try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
