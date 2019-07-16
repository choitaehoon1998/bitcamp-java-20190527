package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  private static final int DEFAULT_CAPACITY = 100;

  private Object[] list;
  private int size = 0;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 50 || initialCapacity > 10000)
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[initialCapacity];
  }

  public void add(E obj) {
    if (size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(this.list, newCapacity);
    }
    list[size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(list, size); // new Object [this.size]
  }

  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨받은 배열의 크기가 저장된 데이터의 개수보다 작다면
      // 이메서드에서 새배열을 만든다
      return (E[]) Arrays.copyOf(list, size, a.getClass());// 세번째 파리미터로 지정된 타입의 배열이 생성된다 .
                                                           // (지정 x 시 오브젝트로만듬 )
    }
    System.arraycopy(list, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }
  public int size() {
    return this.size;
  }
}


