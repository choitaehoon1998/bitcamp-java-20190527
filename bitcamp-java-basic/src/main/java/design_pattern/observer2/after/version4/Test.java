// v4(유관순, 8월15일에 추가함 ): mainClassName 값을 알아내기  .
package design_pattern.observer2.after.version4;

import java.io.FileReader;

public class Test {
  public static void main(String[] args) {
    try (FileReader in = new FileReader("build.gradle")) {
      TextAnalyzer analyzer = new TextAnalyzer(in);

      // 분석기로부터 보고를 받을 리스너(=observer)를 추가한다
      CharacterCounterListener l1 = new CharacterCounterListener();
      analyzer.addCharacterListener(l1);
      LineCountListener l2 = new LineCountListener();
      analyzer.addCharacterListener(l2);
      ListCommentListener l3 = new ListCommentListener();
      analyzer.addCharacterListener(l3);
      MainClassNameListener l4 = new MainClassNameListener();
      analyzer.addCharacterListener(l4);
      // 분석기를 실행한다
      // -> 분석기는 문자를 한개 읽을때마다 분석기에 등록된 모든 리스너에게 보고할것이다
      // -> 각각의 리스너는 보고를 받으면 자신의 일을한다
      analyzer.execute();

      // 분석작업이 끝났으면 각 리스너에게 결과를 출력하라고 말한다
      l1.displayResult();
      l2.displayResult();
      l3.displayResult();
      l4.displayResult();
      
    } catch (Exception e) {
      System.out.println("실행중오류발생 ");
    }
  }
}
