package design_pattern.observer2.before.version5;

import java.io.Reader;

public class TextAnalyzer {
  Reader in;

  public TextAnalyzer(Reader in) {
    this.in = in;
  }

  public void execute() {
    try {
      int ch;
      int count = 0;
      int totalLine = 0;
      int totlaLineComment = 0;
      boolean startLineComment = false;
      int countSlash = 0;
      boolean isEmpty = true;
      StringBuffer line = new StringBuffer();
      String mainClassName = null;
      while ((ch = in.read()) != -1) {
        count++;
        if (ch == '\n') {
          totalLine++;
          isEmpty = true;
        } else {
          isEmpty = false;
        }

        if (!startLineComment) {
          if (ch == '/') {
            if (countSlash == 0) {
              countSlash++;
            } else {
              totlaLineComment++;
              startLineComment = true;
            }
          } else {
            countSlash = 0;
          }
        } else if (ch == '\n') {
          startLineComment = false;
        }
        // mainClassName 의 값 알아내기
        if (ch == '\n') {
          if (line.indexOf("mainClassName") != -1) {
            int i = line.indexOf("\"");
            if (i != -1) {
              mainClassName = line.substring(i + 1, line.indexOf("\"", i + 1));
            }
            i = line.indexOf("'");
            if (i != -1) {
              mainClassName = line.substring(i + 1, line.indexOf("\'", i + 1));
            }
          }
          line.setLength(0);
        } else {
          line.append((char)ch);
        }
      }

      if (!isEmpty) {
        totalLine++;
      }
      System.out.printf("총 읽은 문자수 : %d\n", count);
      System.out.printf("총 줄수  : %d\n", totalLine);
      System.out.printf("총 한 줄 주석수 : %d\n", totlaLineComment);
      System.out.printf("mainClassName : %s\n", mainClassName);

    } catch (Exception e) {
      System.out.println("분석 중 오류 발생!");
    } finally {
      // 주의
      // => 자원 해제는 그자원을 관리하는 객체가 책임져야한다
      // => TextAnalyzer는 단지 Reader 자원을 생성자에서 받아서
      // execute()에서 사용할뿐이다
      // => 따라서 다음 과같이 사용이 끝났다고해서
      // 여기서 자원을 해제해서는 안된다
      // => 이객체에 자원을 넘겨준 놈이 자원 해제의 책임을 져야한다
      // try{in.close();}catch(Exception e) {}
    }
  }
}