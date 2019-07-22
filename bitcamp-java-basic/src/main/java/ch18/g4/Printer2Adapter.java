package ch18.g4;

import ch18.g2.Printer;
import ch18.g3.Printer2;

// Adapter pattern:기존에 작성한 클래스를 새규격에 맞춰 재 활용할 수 있게 만드는 설계 기법!
public class Printer2Adapter implements Printer2 {
  // 이클래스는 중간에서 Pritner2 에 꼽을수있도록 중계해주는
  // 역활만하기 때문에 이클래스가 직접 Printer 기능을 구현해서는 안된다.
  // 대신 Printer 기능은 기존 클래스에게 맡겨야한다.
  Printer originalPrinter;

  public Printer2Adapter(Printer originalPrinter) {
    this.originalPrinter = originalPrinter;
  }

  public void print(String text) {
    // 아답터는 Printer2 의 규격을 따르기는 하지만,
    // 직접 Printer 의 역활을 하는것이 아니기때문에
    // 아답터에게 Print()라는 명령을 내리면,
    // 생성자에게서 받은 기존 프린터 객체를 실행시킨다.
    originalPrinter.print(text);
  }

  public void watermark(String title) {
    // 단 프린터2 추가된 워터마킹 기능은 아답터에서 실행된다 .
    System.out.println(title);
  }
}
