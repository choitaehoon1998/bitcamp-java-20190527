// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util.step4;

import com.eomcs.util.Iterator;
import com.eomcs.util.LinkedList;

public class Queue<E> extends LinkedList<E> implements Cloneable {


  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    // 현재 큐 객체의 node 를 그대로 유지하기 위해 "deep copy "를 실행한다.
    // => 새스택을 만들고 ,
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      // 현재 큐에서 값을 꺼내 새큐에 새노드에 넣는다
      // 즉 새 큐는 값을 넣을때 마다 새노드를 생성하기때문에
      // 현재 큐의 노드와는 상관없다 .
      temp.offer(get(i));
    }
    return temp;
  }

  public void offer(E value) {
    add(value);
  }

  public E poll() {
    return remove(0);
  }

  public boolean empty() {
    return size() == 0;
  }

  // 큐의 데이터를 꺼내줄 이터레이터를 제공한다
  public Iterator<E> createIterator() {
  //중첩 클래스를 정의한후 , 인스턴스를 딱한개 생성하는 용도로 사용한다면 
  //굳이 클래스이름을 가질필요가없다 . 
  //클래스를 정의하자마자 바로 인스턴스를 만들어 사용하면 편하다 
  // 이렇게 정의하는 중첩 클래스를 anonymous class 라 부른다 
  return new Iterator<E>() {
      public boolean hasNext() {
        return size() > 0;
      }

      @Override
      public E Next() {
        return poll();
      }

    };
  }



}
