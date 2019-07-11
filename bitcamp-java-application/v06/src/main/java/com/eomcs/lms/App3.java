package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard= System.in;
    keyScan = new Scanner(keyboard);
    
    int[] no = new int[100];
    String[] content = new String[100];
    Date[] createDate = new Date[100];
    int count = 0;
    
    int i = 0;
    for(; i < no.length; i++) {
      no[i] = getIntValue("번호? ");
      content[i] = getStringValue("내용? ");
      createDate[i] = new Date(System.currentTimeMillis());
      
      System.out.println();
      System.out.printf("계속 입력하시겠습니까?(Y/n)");
      String response = keyScan.nextLine();
      System.out.println();
      
      if (response.equals("n"))
          break;
      
    }
    
    keyScan.close();
    
    System.out.println();
    
    int i2 = 0;
    for(; i2 <= i; i2++) {
      System.out.printf("%d, %-20s , %-10s, %d\n", no[i2], content[i2], createDate[i2], count);
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
    System.out.printf(message);
    return keyScan.nextLine();
  }
  
//  private static Date getDateValue(String message) {
//    while (true) {
//      try {
//        System.out.print(message);
//        return java.sql.Date.valueOf(keyScan.nextLine());
//      } catch (IllegalArgumentException e) {
//        System.out.println("2019-07-04 형태로 입력하세요.");
//      }
//    }
//  }

}