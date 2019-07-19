// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util.step2;

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
    /*
     * Queue<E> clonedqueue = this; // 복제된 commandQueue의 주소가 들어있다. QueueIterator<E> Iterator = new
     * QueueIterator<>(clonedqueue); return Iterator ;
     */
    return new QueueIterator();
  }



  // 스택에있는 데이터를 꺼내주는 역할을 한다 .
  // Iterator 규칙에 따라 작성하여 ,
  // 이객체를 사용하는 개발자가 일관된 방식으로 호출할수있게한다.
  // => 이클래스에서 사용할 클래스는 이클래스안에 선언하는것이 소스관리에좋다 .
  // 이렇게 클래스안에 선언된 클래스를 중첩클래스라 부른다 .
  // 중첩 클래스 중에서 static 이 붙지않은 중첩클래스를 non static nested class 라부른다.
  // non static nested class 를 inner class 라 부른다 .
  // => 중첩 클래스의 가장큰 효용성은 다른멤버(메서드)들처럼 다른멤버를 그냥 사용할수있다는것이다 .
  //
  private class QueueIterator implements Iterator<E> {
    @Override
    public boolean hasNext() {
      // 중첩 클래스 안에서는 자신을 생성한 바깥 클래스의 인스턴스 주소를
      // 바깥 클래스명.this 변수에 저장하고있다. 
      // 그래서 생성자에서 바깥 클래스의 인스턴스 주소를 받을 필요가없이 
      // 바로 바깥 클래스의 인스턴스를 사용할수있다
      return size() > 0;
    
    
      // 만약 사용하려면  필드나 메서드가 중첩클래스에 있는 필드나 메서드가 아니라면
      // 바깥 클래스의 인스턴스 주소를 생략할수있다. 
    }

    @Override
    public E Next() {
     // return  Queue.this.poll();
    return poll();// 바깥 클래스의 인스턴스 주소를 생략할수있다 . 
    }

  }
}
