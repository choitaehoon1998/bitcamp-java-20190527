package algorithm.data_structure.linkedlist2.step1;

public class LinkedList {
  Node head;
  Node tail;
  int size = 0;

  public LinkedList() {}

  public boolean add(Object value) {
    Node temp = new Node(value);
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
  public Object get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  public Object set(int index, Object value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    Object oldval = node.value;
    node.value = value;
    return oldval;

  }

  public Object remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스 범위초과");
    Node deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {

      Node node = head;
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
    Object oldVal = deletedNode.value;
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
      Node deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;
    }
    tail = null;
    size = 0;

  }
  public Object[] toArray() {
    //LinkedList에있는 데이터를 저장할 배열을 준비한다.
    Object [] obarr = new Object[size];
    //LinkedList의 head에서 tail까지 반복하면서 value를 복사한다.
    Node copyNode = head;
    for(int i =0; i<size; i++) {
      obarr[i] = copyNode.value;
      copyNode=copyNode.next;
    }
  return obarr;
  }
  public Object[] toArray2() {
    Object [] obarr = new Object[size];
    Node copyNode = head;
    int i =0;
    while(i!=size) {
      obarr[i++]= copyNode.value;
      copyNode =copyNode.next;
      
    }
    return obarr;
      }
}
