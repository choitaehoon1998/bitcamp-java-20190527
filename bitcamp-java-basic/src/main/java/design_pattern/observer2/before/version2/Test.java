// v2(임꺽정, 3월23일에 추가함 ): 텍스트 분석기 Reader 연결 . 
package design_pattern.observer2.before.version2;

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
