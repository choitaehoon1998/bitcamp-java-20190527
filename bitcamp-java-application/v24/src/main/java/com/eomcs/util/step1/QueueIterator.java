package com.eomcs.util.step1;

// 스택에있는 데이터를 꺼내주는 역할을 한다 .
// Iterator 규칙에 따라 작성하여 ,
// 이객체를 사용하는 개발자가 일관된 방식으로 호출할수있게한다.
import com.eomcs.util.Iterator;

public class QueueIterator<E> implements Iterator<E> {

  Queue<E> Queue;

  public QueueIterator(Queue<E> Queue) {
    this.Queue = Queue;
  }

  @Override
  public boolean hasNext() {
    return Queue.size() > 0;
  }

  @Override
  public E Next() {
    return Queue.poll();
  }

}
