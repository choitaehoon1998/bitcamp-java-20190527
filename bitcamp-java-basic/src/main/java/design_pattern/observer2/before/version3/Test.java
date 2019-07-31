// v3(임꺽정, 4월5일에 추가함 ): 텍스트 분석기에 기능추가 - 줄 수 세기. 
package design_pattern.observer2.before.version3;

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
