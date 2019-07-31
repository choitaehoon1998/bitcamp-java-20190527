// v5(유관순, 8월15일에 추가함 ): 텍스트 분석기에 기능추가 - mainClassName 값 출력하기 
package design_pattern.observer2.before.version5;

import java.io.FileReader;

public class Test {
  public static void main(String[] args) {
    try(FileReader in = new FileReader("build.gradle")){
      TextAnalyzer analyzer = new TextAnalyzer(in);
      analyzer.execute();
    }catch(Exception e) {
      System.out.println("실행중오류발생 ");
    }
  }
}
