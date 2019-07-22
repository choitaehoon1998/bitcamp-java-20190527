// 신규 프로젝트를 위해 기존 규격에 새로 규칙 (Watermakr())를 추가하였고
// 이클래스는 그규칙을 구현하였다     
package ch18.g5;

import ch18.g2.Printer;

//새규칙을 추가히기위해 새규격(INTERFACE)를 정의할필요가없다
// 디폴트 매서드 라는 문법을 사용하면 , 기존 클래스에 영향을 주지않고 
// 새규칙을 추가할수있다. 
public class WaterMarkPrinter implements Printer {
  // Printer 인터페이스에 선언된 규칙을 구현한다 . 
  @Override
  public void print(String text) {
    System.out.println("WaterMarkPrinter :"+ text);
  }
  
  // 신규 프로젝트에 추가한 규칙을 추가한다 . 
  public void watermark(String title) {
    System.out.println("**"+ title + "**");
  }
}
