package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {
  private Lesson[] list;
  private int size = 0;
  final static int INITIAL_CAPACITY =100;
  LessonList() {
    list = new Lesson[INITIAL_CAPACITY];
    
  }

  LessonList(int initialSize) {
    if(initialSize<50||initialSize>10000) 
     list = new Lesson[INITIAL_CAPACITY];
    else    
    list = new Lesson[initialSize];
  }

  public void add(Lesson lesson) {
    if(this.size == list.length) {
    throw new RuntimeException("꽉찼습니다");
    }
    this.list[this.size++] = lesson;
  }

  public Lesson[] toArray() {
    Lesson[] arr = new Lesson[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;

  }
}
