// 상속 문법을 이용하여 스택 만들기
package algorithm.data_structure.queue.step3;

import algorithm.data_structure.linkedlist2.step3.LinkedList;

public class Queue<E> extends LinkedList implements Cloneable {


  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    // 현재 큐 객체의 node 를 그대로 유지하기 위해 "deep copy "를 실행한다.
    // => 새스택을 만들고 ,
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      // 현재 큐에서 값을 꺼내 새큐에 새노드에 넣는다
      // 즉 새 큐는 값을 넣을때 마다 새노드를 생성하기때문에
      // 현재 큐의 노드와는 상관없다 .
      temp.offer((E) get(i));
    }
    return temp;
  }

  public void offer(E value) {
    add(value);
  }

  public E poll() {
    return (E) remove(0);
  }

  public boolean empty() {
    return size() == 0;
  }
}
