/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.eomcs.lms;

import java.util.Scanner;

public class App {
  public static void main(String[]args) {
    java.io.InputStream keyboard = System.in;
    Scanner keyscan = new Scanner(keyboard);
    
    System.out.print("번호?");
    String no = keyscan.nextLine();
    
    System.out.print("수업명?");
    String lectureName = keyscan.nextLine();
    
    System.out.print("설명?");
    String description = keyscan.nextLine();
    
    System.out.print("시작일?");
    String startDate = keyscan.nextLine();
    
    System.out.print("종료일?");
    String endDate = keyscan.nextLine();
    
    System.out.print("총수업시간?");
    String totalHours = keyscan.nextLine();
    
    System.out.print("일수업시간?");
    String dayHours = keyscan.nextLine();
    
    System.out.println();
    
    System.out.println("번호 : "+no);
    System.out.println("수업명 : "+lectureName);
    System.out.println("설명 : "+description);
    System.out.println("시작일 : "+startDate);
    System.out.println("종료일 : "+endDate);
    System.out.println("총수업시간 : "+totalHours);
    System.out.println("일수업시간 : "+dayHours);
  }
}
