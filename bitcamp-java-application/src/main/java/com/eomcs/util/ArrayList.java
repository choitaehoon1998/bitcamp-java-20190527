package com.eomcs.util;

import java.util.Arrays;

public class ArrayList {

  final static private int INITAL_SIZE = 100;
  private Object[] list;
  private int size = 0;

  ArrayList(int les) {
    if (les < 50 || les > 10000) {
      list = new Object[INITAL_SIZE];
    } else {
      list = new Object[les];
    }
  }

  public ArrayList() {
    list = new Object[INITAL_SIZE];
  }

  public void add(Object obj) {
    if (list.length == size) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(this.list, newCapacity);
    }
    list[size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
}
