package ch17.d;

// generalization 기법으로 생성한 수퍼클래스를 추상클래스로 생성하기 .

public class Test01 {


  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    // => sorter클래스를 추상클래스로 선언하면 다음과같이 직접 사용할수없다 .
    // display(new Sorter(), values);

    // 이렇게 sorter 클래스처럼 서브클래스에게 공통필드나 기능을 상속해주는 용도로 만든 클래스인경우
    // 직접 사용하지 못하게 abstract 클래스로 선언하면
    // 다른 개발자가 잘못 사용하는 것을 막을수있다 .
  }

  static void display(Sorter sorter, int[] values) {
    sorter.sort(values);
    for (int value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}


