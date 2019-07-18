// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util;

public class Stack<E> extends LinkedList<E> implements Cloneable {


  @Override
  public Stack<E> clone() throws CloneNotSupportedException{
    // 현재 stack 객체의 node 를 그대로 유지하기 위해 "deep copy "를 실행한다.
    // => 새스택을 만들고 ,
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      // 현재 스택에서 값을 꺼내 새스택에 새노드에 넣는다
      // 즉 새스택 은 값을 넣을때 마다 새노드를 생성하기때문에
      // 현재 스택의 노드와는 상관없다 . 
      temp.push(get(i));
    }
    return temp;
  }

  public void push(E value) {
    add(value);
  }

  public E pop() {
    return remove(size() - 1);
  }

  public boolean empty() {
    return size() == 0;
  }
}
