package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner keyScan;
  
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    
    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    String[] pw = new String[100];
    String[] picture = new String[100];
    String[] phoneNumber = new String[100];
    Date[] registeredDate = new Date[100];
    
    int i = 0;
    for(; i < no.length; i++) {
      no[i] = getIntValue("번호?");
      name[i] = getStringValue("이름? ");
      email[i] = getStringValue("이메일? ");
      pw[i] = getStringValue("암호? ");
      picture[i] = getStringValue("사진? ");
      phoneNumber[i] = getStringValue("전화? ");
      registeredDate[i] = new Date(System.currentTimeMillis()); 
          
      System.out.println();
      System.out.printf("계속 입력하시겠습니까?(Y/n)");
      String response = keyScan.nextLine();
      System.out.println();
      
      if (response.equals("n"))
          break;
    }
    
    System.out.println();
    
    int i2 = 0;
    for(; i2 <= i; i2++) {
      System.out.printf("%d, %-5s , %-20s , %-15s , %s\n", no[i2], name[i2], email[i2], phoneNumber[i2], registeredDate[i2]);
    }
  }
  
  public static int getIntValue(String message) {
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
  
  public static String getStringValue(String message) {
    System.out.printf(message);
    return keyScan.nextLine();
  }
  
//  public static java.sql.Date getDateValue(String message){
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