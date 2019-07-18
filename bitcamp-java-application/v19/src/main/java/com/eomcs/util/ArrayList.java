package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  private static final int DEFAULT_CAPACITY = 100;

  private Object[] list;
  private int size = 0;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 50 || initialCapacity > 10000)
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[initialCapacity];
  }

  public void add(E obj) {
    if (size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }
    list[size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(list, size); // new Object [this.size]// 새로 배열을생성하기 때문에 원래것과 비교하면 주소값이 다르다
  }


  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨받은 배열의 크기가 저장된 데이터의 개수보다 작다면
      // 이메서드에서 새배열을 만든다
      return (E[]) Arrays.copyOf(list, size, a.getClass());// 세번째 파리미터로 지정된 타입의 배열이 생성된다 .
                                                           // (지정 x 시 오브젝트로만듬 )
    }
    System.arraycopy(list, 0, a, 0, size);// 새로 배열을 생성하는것이아니라 복사하는것이기때문에 주소값도 같다 .
    if (a.length > size)
      a[size] = null;
    return a;
  }

  public int size() {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("인덱스 =%s", index));

    return (E) list[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E obj) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("인덱스 =%s", index));

    E old = (E) list[index];
    list[index] = obj;

    return old;
  }

  public E remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("인덱스 =%s", index));

    @SuppressWarnings("unchecked")
    E old = (E) list[index];
    // 방법 1 : 직접 반복문을 이용하여 삭제 처리하기.
    /*
     * for (int i = index + 1; i < size; i++) { list[i - 1] = list[i]; }
     * 
     * list[--size] = null;
     */
    // 방법2
    System.arraycopy(list, index + 1, list, index, size - (index + 1));
    // 삭제한후 기존의 맨끝값이 들어있던 방을 null로 설정한다 .
    // -> 래퍼런스가 남아있지 않게 하여 가비지가 정상적으로 이뤄지도록한다 .
    list[--size] = null;
    return old;
  }

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();

    list.add("000");
    list.add("111");
    list.add("222");
    list.add("333");
    list.add("444");
    list.add("555");
    String old = list.remove(30);
    // System.out.println(old);
    for (int i = 0; i < list.size; i++) {
      System.out.println(list.get(i));
    }
  }

}


