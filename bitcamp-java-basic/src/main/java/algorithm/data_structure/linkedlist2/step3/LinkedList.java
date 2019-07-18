// Linked List :목록으로 다루는 값을 특정 타입으로 제한하기 위해 제네릭 적용하기 . 
package algorithm.data_structure.linkedlist2.step3;

import java.lang.reflect.Array;

public class LinkedList<T> {
  Node<T> head;
  Node<T> tail;
  int size = 0;

  public LinkedList() {}

  public boolean add(T value) {
    Node<T> temp = new Node<>(value);
    if (head == null)
      head = temp;

    if (tail != null)
      tail.next = temp;

    tail = temp;
    size++;
    return true;
  }

  // public boolean add(Object value,int index) {
  // if(index >size|| index<0)
  // throw new IndexOutOfBoundsException("인덱스 범위초과");
  // Node temp = new Node(value);
  // if(index==0) {
  //
  //
  //
  // }
  //
  // }
  public T get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  public T set(int index, T value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node <T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    T oldval = node.value;
    node.value = value;
    return oldval;

  }

  public T remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node<T> deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {

      Node<T> node = head;
      for (int i = 0; i < index - 1; i++) {
        node = node.next;
      }
      //
      deletedNode = node.next;// 삭제될노드를 임시보관한다.
      node.next = node.next.next;
      if (deletedNode == tail) { // 삭제할 노드가 마지막 노드라면
        tail = node; // tail 노드를 변경한다 .
      }
    }
    T oldVal = deletedNode.value;
    deletedNode.value = null;
    deletedNode.next = null;

    size--;

    return oldVal;
  }

  public int size() {
    return size;
  }

  public void clear() {
    // 노드를 따라가면서 삭제하기
    while (head != null) {
      Node<T> deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;
    }
    tail = null;
    size = 0;

  }

  public Object[] toArray() {
    // LinkedList에있는 데이터를 저장할 배열을 준비한다.
    Object[] obarr = new Object[size];
    // LinkedList의 head에서 tail까지 반복하면서 value를 복사한다.
    Node<T> copyNode = head;
    for (int i = 0; i < size; i++) {
      obarr[i] = copyNode.value;
      copyNode = copyNode.next;
    }
    return obarr;
  }

  public Object[] toArray2() {
    Object[] obarr = new Object[size];
    Node<T>copyNode = head;
    int i = 0;
    while (i != size) {
      obarr[i++] = copyNode.value;
      copyNode = copyNode.next;

    }
    return obarr;
  }
  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨받은 배열의 크기가 저장된 데이터의 개수보다 작다면
      // 이메서드에서 새배열을 만든다
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
      // 세번째 파리미터로 지정된 타입의 배열이 생성된다 .
                                                           // (지정 x 시 오브젝트로만듬 )
    }
   //새로 배열을 생성하는것이아니라 복사하는것이기때문에 주소값도 같다 . 
    Node<T> copyNode = head;
    for (int i = 0; i < size; i++) {
      a[i] = copyNode.value;
      copyNode = copyNode.next;
    }
    if (a.length > size)
      a[size] = null;
    return a;
  }
// Node 객체 에 보관하는 데이터의 클래스 이름을  "타입 파라미터" t에 받는다 . 
  static class Node<T> {
    T value;
    Node<T> next;

    public Node() {}

    public Node(T value) {
      this.value = value;
    }

  }

}
