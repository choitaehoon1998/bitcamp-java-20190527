// // 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyScan;
  static Lesson[] lessons = new Lesson[100];
  static int lessonssize = 0;
  static Member[] members = new Member[100];
  static int memberssize = 0;
  static Board[] boards = new Board[100];
  static int boardssize = 0;
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    while (true) {
      String command = prompt();
      if (command.equals("quit")) {
        break;
      } else if (command.equals("/lesson/add")) {
        addLesson();// addLesson() 메서드 블록에 묶어놓은 코드를 실행한다
      } else if (command.equals("/lesson/list")) {
        listLesson();
      } else if (command.equals("/member/add")) {
        addMember();
      } else if (command.equals("/member/list")) {
        listMember();
      } else if (command.equals("/board/add")) {
        addBoard();
      } else if (command.equals("/board/list")) {
        listBoard();
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

  static void addLesson() {
    // 수업 데이터를 저장할 메모리는 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();

    // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.no = getIntValue("번호?");
    lesson.title = getStringValue("수업명? ");
    lesson.contents = getStringValue("설명? ");
    lesson.startDate = getDateValue("시작일? ");
    lesson.endDate = getDateValue("종료일? ");
    lesson.totalHours = getIntValue("총수업시간? ");
    lesson.dayHours = getIntValue("일수업시간? ");

    // 수업 데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다.
    lessons[lessonssize++] = lesson;

    System.out.println("저장하였습니다.");
  }

  static void listLesson() {
    for (int i = 0; i < lessonssize; i++) {
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      Lesson lesson = lessons[i];
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%d, %s, %s ~ %s, %d\n", lesson.no, lesson.title, lesson.startDate,
          lesson.endDate, lesson.totalHours);
    }
  }

  static int addMember() {
    Member member = new Member();

    member.no = getIntValue("번호는");
    member.name = getStringValue("이름은");
    member.email = getStringValue("이메일은");
    member.password = getStringValue("비밀번호는");
    member.photo = getStringValue("사진은");
    member.phoneNumber = getStringValue("전화번호는");
    member.registerDate = new Date(System.currentTimeMillis());

    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
    return memberssize;
  }

  static void listMember() {
    for (int i = 0; i < memberssize; i++) {
      // 레퍼런스 배열에서 한 개의 인스턴스 주소를 꺼낸다.
      Member member = members[i];
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", member.no, member.name, member.email,
          member.password, member.photo, member.phoneNumber, member.registerDate);
    }
  }

  static void addBoard() {
    Board board = new Board();
    board.no = getStringValue("번호는");
    board.contents = getStringValue("내용은");
    board.createdDate = getDateValue("작성일은");
    board.viewCount = getIntValue("조회수는");

    boards[boardssize++] = board;
    System.out.println("저장하였습니다.");
  }

  static void listBoard() {
    for (int i = 0; i < boardssize; i++) {
      Board board = new Board();
      board = boards[boardssize];

      System.out.printf("%s ,%s,%s,%s \n", board.no, board.contents, board.createdDate,
          board.viewCount);
    }
  }

  private static int getIntValue(String message) {
    int value = 0;
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }

}
