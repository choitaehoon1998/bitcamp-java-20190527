package com.eomcs.util;

public class StackTest {
  public static void main(String[] args) {
    Queue<String> stack = new Queue<>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");

    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
  }
}
