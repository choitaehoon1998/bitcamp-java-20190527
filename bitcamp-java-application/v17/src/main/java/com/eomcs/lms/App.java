// // 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Input;

public class App {
  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    Input input = new Input(keyScan);
    LessonHandler lessonHandler = new LessonHandler(input);
    MemberHandler memberHandler = new MemberHandler(input);
    BoardHandler boardHandler = new BoardHandler(input);
    BoardHandler boardHandler2 = new BoardHandler(input);

    while (true) {
      String command = prompt();
      if (command.equals("quit")) {
        break;
      } else if (command.equals("/lesson/add")) {
        lessonHandler.addLesson();// addLesson() 메서드 블록에 묶어놓은 코드를 실행한다
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();
      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();
      } else {
        System.out.println("해당 명령을 지원하지않습니다.!");
      }
      System.out.println();
    }

  }

  static String prompt() {
    System.out.println("명령>");
    return keyScan.nextLine();
  }

}
