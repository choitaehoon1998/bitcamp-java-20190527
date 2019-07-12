package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  private Member[] list;
  private int size = 0;
  final static int INITIAL_CAPACITY =100;

  MemberList() {
    list = new Member[100];
  }

  MemberList(int initialSize) {
    if(initialSize<50||initialSize>10000) {
      list = new Member[INITIAL_CAPACITY];
    }
    else
    list = new Member[initialSize];
  }

  public void add(Member member) {
    if(this.size == list.length) {
      throw new RuntimeException("꽉찼습니다");
    }
    this.list[this.size++] = member;
  }

  public Member[] toArray() {
    Member[] arr = new Member[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;

  }
}
