// 추상 메서드의 효용성 - 서브 클래스에서 구현하도록 강제하는 효과가 있다.
package ch17.e;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    // sorter 클래스의 sort()메소드가 추상 메서드이기때문에
    // sorter 클래스를 상속받은 서브클래스가 무엇이든 간에 
    // sort()메서드가 반드시 구현되어있을것이다 . 
    //따라서 display 메서드에서는 파리미터로 넘어오는 Sorter 가
    // BubbleSort 인지 QuickSort 인지 MergeSort 인지 따질필요없이, 
    // 그냥 서브클래스가 구현한 sort()를 호출하면 딘다 
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    
    // 이제 MergeSort는 Sorter의 추상 메서드인 sort()를 구현했다.
    // 정상적으로 동작할 것이다.
    
    display(new MergeSort(), values3);
    
  }

  static void display(Sorter sorter, int[] values) {
    // 어? 강사님 Sorter 클래스의 sort() 메서드는 추상 메서드인데
    // 어떻게 호출할수있나요 다음코드는 오류가 아닌가요
    // => JVM 이 sort() 를 호출할때 래퍼런스에 있는 객체가 실제 어떤 클랫의 객체인지 판단한다
    // 그런후에 해당클랫의 메서들 호출하기 때문에 질문한것과 같은 문제 는 발생하지않는다 
    sorter.sort(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






