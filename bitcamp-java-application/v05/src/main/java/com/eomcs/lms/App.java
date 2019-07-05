/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.eomcs.lms;

import java.util.Date;
import java.util.Scanner;

public class App {
  static Scanner keyscan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyscan = new Scanner(keyboard);
    int no = 0;
    int totalHours = 0;
    int dayHours = 0;
    
    no = getIntValue("번호");
    String lectureName = getStringValue("수업명");
    String description = getStringValue("설명");
    java.sql.Date startDate = getDateValue("시작일?");
    java.sql.Date endDate = getDateValue("종료일?");
    totalHours = getIntValue("총수업시간");
    dayHours = getIntValue("일수업시간");


    System.out.println();

    System.out.printf("번호 : %1d\n", no);
    System.out.printf("수업명 : %1s\n", lectureName);
    System.out.printf("설명 : %1s\n", description);
    System.out.printf("시작일 : %1s\n", startDate);
    System.out.printf("종료일 : %1s\n", endDate);
    System.out.printf("총수업시간 : %1d\n", totalHours);
    System.out.printf("일수업시간 : %1d\n", dayHours);
  }

  private static int getIntValue(String message) {
    java.io.InputStream keyboard = System.in;
    while (true) {
      try {
        System.out.print(message + "?");
        return Integer.parseInt(keyscan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자가 아닌값을 입력했습니다.숫자를 입력하세요 .");

      }
    }
  }

  private static java.sql.Date getDateValue(String message) {
    java.io.InputStream keyboard = System.in;
    while (true) {
      try {
        System.out.print(message);
        return java.sql.Date.valueOf(keyscan.nextLine());
      } catch (java.lang.IllegalArgumentException e) {
        System.out.println("2019-07-05형식으로 입력하세요 . ");

      }
    }
  }

  private static String getStringValue(String message) {
    java.io.InputStream keyboard = System.in;
    System.out.println(message);
    return keyscan.nextLine();
  }
}
